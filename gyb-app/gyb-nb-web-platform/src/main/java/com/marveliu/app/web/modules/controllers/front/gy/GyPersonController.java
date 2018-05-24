package com.marveliu.app.web.modules.controllers.front.gy;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.app.web.commons.utils.ShiroUtil;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.gy.GyAuthService;
import com.marveliu.framework.services.gy.GyFacadeService;
import com.marveliu.framework.services.gy.GyInfService;
import com.marveliu.framework.services.gy.GyPayService;
import com.marveliu.framework.services.sys.SysUserService;
import com.marveliu.framework.services.xm.XmApplyService;
import com.marveliu.framework.services.xm.XmBillService;
import com.marveliu.framework.services.xm.XmFeedbackService;
import com.marveliu.framework.services.xm.XmInfService;
import com.marveliu.framework.util.ConfigUtil;
import com.marveliu.framework.util.Toolkit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.FieldFilter;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.trans.Atom;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Marveliu
 * @since 14/05/2018
 **/

@IocBean
@At("/platform/gy/person")
public class GyPersonController {

    private static final Log log = Logs.get();


    @Inject
    @Reference
    private GyInfService gyInfService;
    @Inject
    @Reference
    private GyAuthService gyAuthService;
    @Inject
    @Reference
    private SysUserService sysuserService;
    @Inject
    @Reference
    private GyPayService gyPayService;

    @Inject
    @Reference
    private GyFacadeService gyFacadeService;

    @Inject
    @Reference
    private XmInfService xmInfService;
    @Inject
    @Reference
    private XmApplyService xmApplyService;

    @Inject
    @Reference
    private XmBillService xmBillService;

    @Inject
    @Reference
    private XmFeedbackService xmFeedbackService;

    @Inject
    @Reference
    private SysUserService sysUserService;

    @Inject
    private ShiroUtil shiroUtil;


    /**
     * 雇员登录
     *
     * @param req
     */
    @At("")
    @Ok("re:beetl:/platform/gy/person/index.html")
    @RequiresPermissions("gy.person")
    public String index(HttpServletRequest req) {
        Sys_user user = shiroUtil.getCurrentUser(req);
        req.setAttribute("obj", user);
        // 检查是否已经注册
        if (shiroUtil.hasAnyRoles("gy1")) return "beetl:/platform/gy/person/reginfo.html";
        req.setAttribute("role", user.getRoles().get(0));
        gy_inf gy = gyInfService.getGyByUserId(user.getId());
        gy_auth auth = gyAuthService.getGyAuthByGyid(gy.getId());
        req.getSession().setAttribute("gyid", gy.getId());
        req.setAttribute("gyauth", auth);
        req.setAttribute("isAuth", gyAuthService.isAuth(gy.getId()));
        req.setAttribute("gy", gy);
        return null;
    }


    @At
    @Ok("json")
    @AdaptBy(type = WhaleAdaptor.class)
    @SLog(type = "gy", tag = "雇员信息注册", msg = "雇员信息注册:${args[0]}")
    public Object reginfo(
            @Param("userid") String userid,
            @Param("::gyinf.") gy_inf gyinf,
            @Param("::gyauth.") gy_auth gyauth,
            @Param("birthdayat") String birthday,
            @Param("regYearat") String regyear,
            HttpServletRequest req) {
        try {
            if (shiroUtil.hasAnyRoles("gy2")) return Result.error("您已经注册过雇员信息!");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            int regyearat = (int) (sdf.parse(regyear).getTime() / 1000);
            gyinf.setUserid(userid);
            gyinf.setRegYear(regyearat);
            gyinf.setBirthday(birthdayat);
            gyauth.setReAuthTime(Times.getTS());
            if (gyFacadeService.regInfo(gyinf, gyauth)) {
                shiroUtil.getCurrentUser(req);
            }
            return Result.success("system.success");
        } catch (Exception e) {
            log.error("用户信息注册失败",e);
        }
        return Result.success("system.error");
    }

    //  个人信息修改
    @At("/infedit")
    @Ok("beetl:/platform/gy/person/infedit.html")
    @RequiresPermissions("gy.person")
    public void infedit(HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        req.setAttribute("obj", shiroUtil.getSysuser());
    }

    //  提交个人信息修改
    @At("/infeditDo")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(type = "gy", tag = "雇员账号信息修改", msg = "${args[0].id}")
    public Object infeditDo(
            @Param("..") Sys_user sysUser,
            HttpServletRequest req) {
        try {
            String userid = StringUtil.getPlatformUid();
            sysUser.setId(userid);
            if(sysUser.getEmail().equals(shiroUtil.getSysuser().getEmail())){
                sysUser.setEmailChecked(shiroUtil.getSysuser().isEmailChecked());
            }
            sysuserService.updateIgnoreNull(sysUser);
            return Result.success("system.success");
        } catch (Exception e) {
            log.debug(e);
        }
        return Result.error("system.error");
    }


    // 身份信息修改
    @At("/authedit")
    @Ok("beetl:/platform/gy/person/authedit.html")
    @RequiresPermissions("gy.person")
    public void authedit(HttpServletRequest req) {
        gy_inf gyinf = gyInfService.getGyByUserId(StringUtil.getPlatformUid());
        gy_auth auth = gyAuthService.getGyAuthByGyid(shiroUtil.getCurrentGyid());
        req.setAttribute("gyauth", auth);
        req.setAttribute("gyinf", gyinf);
    }

    // 提交身份信息修改
    @At("/autheditDo")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @AdaptBy(type = WhaleAdaptor.class)
    @SLog(type = "gy", tag = "雇员身份认证信息修改", msg = "${args[0].id}")
    public Object autheditDo(
            @Param("userid") String userid,
            @Param("::gyinf.") gy_inf gyinf,
            @Param("::gyauth.") gy_auth gyauth,
            @Param("birthdayat") String birthday,
            @Param("regYearat") String regyear,
            HttpServletRequest req) {
        try {
            if (shiroUtil.hasAnyRoles("gy3")) return Result.error("您的身份信息已经认证，无法修改!");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            int regyearat = (int) (sdf.parse(regyear).getTime() / 1000);
            gyinf.setUserid(userid);
            gyinf.setRegYear(regyearat);
            gyinf.setBirthday(birthdayat);
            gyauth.setReAuthTime(Times.getTS());
            if (gyFacadeService.editInfo(gyinf, gyauth)) {
                return Result.success("system.success");
            }
        } catch (Exception e) {
            log.error("身份信息修改失败", e);
        }
        return Result.error("system.error");
    }


    //支付方式修改
    @At("/payindex")
    @Ok("beetl:/platform/gy/person/payindex.html")
    @RequiresPermissions("gy.person")
    public void payindex(HttpServletRequest req) {
    }

    // 支付方式列表
    @At("/paydata")
    @Ok("json")
    @RequiresPermissions("gy.person")
    public Object paydata(
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gy_inf gy = gyInfService.fetch(Cnd.where("userid", "=", user.getId()));
        cnd.and("gyid", "=", gy.getId());
        return gyPayService.data(length, start, draw, order, columns, cnd, null);
    }

    // 支付方式添加界面
    @At("/payadd")
    @Ok("beetl:/platform/gy/person/payadd.html")
    @RequiresPermissions("gy.person")
    public void payadd() {
    }

    // 添加支付方式
    @At("/payaddDo")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(type = "gy", tag = "添加收款方式", msg = "${args[0]}")
    public Object payaddDo(@Param("..") gy_pay gyPay, HttpServletRequest req) {
        try {
            gyPay.setGyid(gyInfService.getGyByUserId(StringUtil.getPlatformUid()).getId());
            if (!Lang.isEmpty(gyPayService.addOrUpdateGypay(gyPay))) {
                return Result.success("system.success");
            }
        } catch (Exception e) {
            log.error("添加支付方式失败", e);
        }
        return Result.error("system.error");
    }

    @At("/payedit/?")
    @Ok("beetl:/platform/gy/person/payedit.html")
    @RequiresPermissions("gy.person")
    public void payedit(String payid, HttpServletRequest req) {
        gy_inf gy = gyInfService.getGyByUserId(StringUtil.getPlatformUid());
        req.setAttribute("gy", gy);
        req.setAttribute("obj", gyPayService.fetch(payid));
    }

    @At("/payeditDo")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(type = "gy", tag = "修改雇员收款方式", msg = "${args[0].id}")
    public Object payeditDo(@Param("..") gy_pay gyPay, HttpServletRequest req) {
        try {

            if (!Lang.isEmpty(gyPayService.addOrUpdateGypay(gyPay))) {
                return Result.success("system.success");
            }
        } catch (Exception e) {
            log.error("修改雇员收款方式失败", e);
        }
        return Result.error("system.error");
    }

    @At({"/paydelete"})
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(type = "gy", tag = "删除雇员收款方式", msg = "${args[0]}")
    public Object paydelete(
            @Param("id") String id,
            HttpServletRequest req) {
        try {
            if (gyPayService.delete(id) == 1) {
                return Result.success("system.success");
            }
        } catch (Exception e) {
            log.error("删除雇员收款方式失败");
        }
        return Result.error("system.error");
    }

    @At("/paydetail/?")
    @Ok("beetl:/platform/gy/person/paydetail.html")
    @RequiresPermissions("gy.person")
    public void paydetail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", gyPayService.fetch(id));
        } else {
            req.setAttribute("obj", null);
        }
    }

    @At("/payselect")
    @Ok("beetl:/platform/gy/pay/payselect.html")
    @RequiresPermissions("gy.person")
    public void payselect() {
    }


    /**
     * 验证邮箱
     * @return
     */
    @At("/email/sendActiveMail")
    @SLog(type = "sys",tag = "发送邮箱激活邮件",param = true,result = true)
    @Ok("json")
    @RequiresPermissions("gy.person")
    public Object sendActiveMail() {
        Sys_user sysUser = shiroUtil.getSysuser();
        if (sysUser.isEmailChecked()) return Result.error("邮箱已经验证!");
        try {
            if(sysUserService.sendActiveEmail(sysUser)){
                return Result.success("已发送邮箱验证邮件!");
            }
        } catch (Throwable e) {
            log.error("发送邮箱激活邮件失败", e);
        }
        return Result.error("发送邮箱激活邮件失败");
    }



}
