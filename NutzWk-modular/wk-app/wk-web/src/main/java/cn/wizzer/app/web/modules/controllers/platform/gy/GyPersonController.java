package cn.wizzer.app.web.modules.controllers.platform.gy;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.services.GyAuthService;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

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
    @SLog(tag = "gy_inf", msg = "${args[0].id}")
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




}
