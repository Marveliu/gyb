package cn.wizzer.app.web.modules.controllers.platform.gy;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.services.*;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 89792 on 2017/11/13 0013.
 */
@IocBean
@At("/platform/gy/person/")
public class GyPersonController {

    //todo:RequiresPermissions没有处理

    private static final Log log = Logs.get();
    @Inject
    private GyInfService gyInfService;
    @Inject
    private GyAuthService gyAuthService;
    @Inject
    private SysUserService userService;
    @Inject
    private GyPayService gyPayService;


    //个人信息修改
    @At("/inf")
    @Ok("beetl:/platform/gy/person/infedit.html")
    @RequiresPermissions("gy.person")
    public void inf(HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        String id = user.getId();
        Object test = gyInfService.fetch(cnd.and("userid","=",id));
        req.setAttribute("obj", test);
    }

    //提交个人基本信息修改
    @At("/infedit")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object editDo(
            @Param("..") gy_inf gyInf,
            @Param("birthdayat") String birthday,
            @Param("regYearat") String regyear,
            HttpServletRequest req) {
        try {
            String userid = gyInf.getUserid();
            Sys_user user = userService.fetch(userid);
            //修改邮箱
            user.setEmail(gyInf.getEmail());
            user.setOpBy(StringUtil.getUid());
            user.setOpAt((int) (System.currentTimeMillis() / 1000));
            userService.updateIgnoreNull(user);
            //修改雇员信息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            int regyearat = (int) (sdf.parse(regyear).getTime() / 1000);
            gyInf.setRegYear(regyearat);
            gyInf.setRegYear(regyearat);
            gyInf.setOpBy(StringUtil.getUid());
            gyInf.setOpAt((int) (System.currentTimeMillis() / 1000));
            gyInfService.updateIgnoreNull(gyInf);
            return Result.success("system.success");

        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    //个人验证信息修改
    @At("/auth")
    @Ok("beetl:/platform/gy/person/authedit.html")
    @RequiresPermissions("gy.person")
    public void auth(HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        String id = user.getId();
        Object test = gyAuthService.fetch(cnd.and("userid","=",id));
        req.setAttribute("obj", test);
    }


    //支付方式修改
    @At("payindex")
    @Ok("beetl:/platform/gy/person/payindex.html")
    @RequiresPermissions("gy.person")
    public void payindex(HttpServletRequest req){
    }

    @At("/paydata")
    @Ok("json")
    @RequiresPermissions("gy.person")
    public Object paydata(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {

        Cnd cnd = Cnd.NEW();
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gy_inf gy = gyInfService.fetch(Cnd.where("userid","=",user.getId()));
        cnd.and("gyid","=",gy.getGyid());
        return gyPayService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/payadd")
    @Ok("beetl:/platform/gy/person/payadd.html")
    @RequiresPermissions("gy.person")
    public void payadd() {

    }

    @At("/payaddDo")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(tag = "gy_pay", msg = "${args[0].id}")
    public Object payaddDo(@Param("..")gy_pay gyPay, HttpServletRequest req) {
        try {
            Cnd cnd = Cnd.NEW();
            Subject currentUser = SecurityUtils.getSubject();
            Sys_user user = (Sys_user) currentUser.getPrincipal();
            gy_inf gy = gyInfService.fetch(cnd.and("userid","=",user.getId()));

            //检查是否已经添加了
            if(null != gyPayService.fetch(Cnd.where("gyid","=",gy.getGyid())
                    .and("type","=",gyPay.getType())
                    .and("payid","=",gyPay.getPayid()))){
                return Result.error("支付方式已经存在，请勿重复提交");
            }


            //检查是否未首要支付方式
            if(gyPay.isFirst()){
                //取消其他首要支付
                gyPayService.update(Chain.make("first", false), Cnd.where("gyid", "=", gy.getGyid()).and("first", "=", true));
            }

            gyPay.setGyid(gy.getGyid());
            gyPayService.insert(gyPay);


            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/payedit/?")
    @Ok("beetl:/platform/gy/person/payedit.html")
    @RequiresPermissions("gy.person")
    public void payedit(String id,HttpServletRequest req) {
        req.setAttribute("obj", gyPayService.fetch(id));
    }

    @At("/payeditDo")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(tag = "gy_pay", msg = "${args[0].id}")
    public Object payeditDo(@Param("..")gy_pay gyPay, HttpServletRequest req) {
        try {
            //检查是否未首要支付方式
            if(gyPay.isFirst()){
                gyPayService.update(Chain.make("first", false), Cnd.where("gyid", "=", gyPay.getGyid()).and("first", "=", true));
            }

            gyPay.setOpBy(StringUtil.getUid());
            gyPay.setOpAt((int) (System.currentTimeMillis() / 1000));
            gyPayService.updateIgnoreNull(gyPay);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At({"/paydelete/?", "/paydelete"})
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(tag = "gy_pay", msg = "${req.getAttribute('id')}")
    public Object paydelete(@Param("id")String id, @Param("ids")  String[] ids, HttpServletRequest req) {
        try {
            if(ids!=null&&ids.length>0){
                gyPayService.delete(ids);
                req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
            }else{
                gyPayService.delete(id);
                req.setAttribute("id", id);
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/paydetail/?")
    @Ok("beetl:/platform/gy/person/paydetail.html")
    @RequiresPermissions("gy.person")
    public void paydetail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", gyPayService.fetch(id));
        }else{
            req.setAttribute("obj", null);
        }
    }
}
