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
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.gy.GyAuthService;
import com.marveliu.framework.services.gy.GyFacadeService;
import com.marveliu.framework.services.gy.GyInfService;
import com.marveliu.framework.services.gy.GyPayService;
import com.marveliu.framework.services.sys.SysUserService;
import com.marveliu.framework.services.xm.XmApplyService;
import com.marveliu.framework.services.xm.XmBillService;
import com.marveliu.framework.services.xm.XmFeedbackService;
import com.marveliu.framework.services.xm.XmInfService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * @author Marveliu
 * @since 14/05/2018
 **/

@IocBean
@At("/platform/gy/person")
public class GyController {

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
    private ShiroUtil shiroUtil;


    /**
     * 雇员登录
     *
     * @param req
     */
    @At("")
    @Ok("re:beetl:/front/gy/index.html")
    @RequiresPermissions("gy.person")
    public String index(HttpServletRequest req) {

        Sys_user user = shiroUtil.getCurrentUser(req);
        req.setAttribute("obj", user);

        // 检查是否已经注册
        if (shiroUtil.hasAnyRoles("gy1")) return "beetl:/front/gy/reginfo.html";
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
    @SLog(type = "gy",tag = "雇员信息注册", msg = "雇员信息注册:${args[0]}")
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
            log.debug(e);
        }
        return Result.success("system.error");
    }

}
