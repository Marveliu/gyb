package cn.wizzer.app.web.modules.controllers.front;

import cn.wizzer.app.gy.modules.services.GyAuthService;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.*;
import cn.wizzer.app.web.commons.services.email.EmailService;
import cn.wizzer.app.web.commons.slog.SLogService;
import cn.wizzer.app.web.modules.controllers.open.api.email.ApiEmailController;
import cn.wizzer.app.web.modules.controllers.open.email.EmailController;
import cn.wizzer.framework.base.Result;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.meta.Email;
import org.nutz.lang.util.Context;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * Created by 89792 on 2017/11/10 0010.
 */


// 前台界面控制器

@IocBean
@At("/public")
public class HomeController {
    private static final Log log = Logs.get();
    @Inject
    private SysMenuService menuService;
    @Inject
    private SysUserService userService;
    @Inject
    private SLogService sLogService;
    @Inject
    private GyInfService gyInfService;
    @Inject
    private GyAuthService gyAuthService;
    @Inject
    private Dao dao;
    @Inject
    private SysRoleService roleService;
    @Inject
    private EmailService emailService;
    @Inject
    private EmailController emailController;

    @Inject
    private ApiEmailController apiEmailController;

    private String loginname = "public";        //
    private String rolecode = "gy1";            //注册雇员绑定角色类型


    @At("")
    @Ok("beetl:/public/home.html")
    public Context home() {
        Context ctx = Lang.context();
        Sys_user user = userService.fetch(Cnd.where("loginname", "=", loginname));
        userService.fillMenu(user);
        ctx.set("user", user);
        user.getSecondMenus();
        return ctx;
    }

    /**
     * @function: 登陆界面
     * @param:
     * @return:
     * @note: 直接进入后台的系统登陆界面
     */
    @At("/redirect")
    @Ok("beetl:/public/redirect.html")
    public void redirect(HttpServletRequest request) {

    }

    /**
     * @function: 登陆界面
     * @param:
     * @return:
     * @note: 直接进入后台的系统登陆界面
     */
    @At("/login")
    @Ok("beetl:/platform/sys/login.html")
    public void login() {
    }

    /**
     * @function: 注册界面
     * @param:
     * @return:
     * @note:
     */
    @At("/reg")
    @Ok("beetl:/public/reg.html")
    public void reg() {
    }

    /**
     * @function: 找回密码界面
     * @param:
     * @return:
     * @note:
     */
    @At("/password")
    @Ok("beetl:/public/password.html")
    public void password() {
    }

    /**
     * @function: 找回密码
     * @param:
     * @return:
     * @note:
     */
    @At("/dopassword")
    @Ok("json")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object doPassword(
            @Param("email") String email,
            HttpServletRequest req
    ) {
        // 邮箱
        Sys_user user =  userService.fetch(Cnd.where("email","=",email));
        if(user == null) return Result.error("邮箱不存在!,请联系管理员");
        return apiEmailController.lostPassword(user.getId(),req);
    }



    @At("/doreg")
    @Ok("json")
    //@SLog(tag = "新雇员注册", msg = "用户名:${username}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object doReg(
            @Param("email") String email,
            @Param("username") String username,
            @Param("password") String password,
            HttpServletRequest req
    ) {

        // 验证用户名，邮箱是否被注册
        if (null != userService.fetch(Cnd.where("email", "=", email).and("emailChecked","=",true))) {
            return Result.error("邮箱存在！");
        }

        // 验证账号
        if (null != userService.fetch(Cnd.where("username", "=", username).and("disabled", "=", false))) {
            return Result.error("账号存在！");
        }

        //初始化用户注册信息
        Sys_user user = new Sys_user();

        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();
        user.setPassword(hashedPasswordBase64);
        user.setSalt(salt);

        user.setLoginname(username);
        user.setUsername(username);
        user.setLoginPjax(true);
        user.setLoginCount(0);
        user.setLoginAt(0);
        user.setEmail(email);
        user.setDisabled(false);
        user.setUnitid("fd02b028caed459590b68dde6d47f115");
        user.setEmailChecked(false);//邮箱未验证

        // 注册用户并发送激活邮件
        try {
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    Sys_user result = userService.insert(user);
                    dao.insert("sys_user_role", org.nutz.dao.Chain.make("userId", result.getId()).add("roleId", roleService.fetch(Cnd.where("code", "=", rolecode)).getId()));
                    if (null != result) {
                        apiEmailController.activeMail(user.getId(),req);
                    }
                }
            });
            return Result.success("注册成功，请前往邮箱进行账号激活！");
        } catch (Exception e) {
            return Result.error("system.error");
        }

    }

}


