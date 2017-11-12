package cn.wizzer.app.web.modules.controllers.front.gy;

import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.*;
import cn.wizzer.app.web.commons.slog.SLogService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 89792 on 2017/11/10 0010.
 */


// 游客前台界面控制器

@IocBean
@At("/public")
public class GyHomeController {
    private static final Log log = Logs.get();
    @Inject
    private SysMenuService menuService;
    @Inject
    private SysUserService userService;
    @Inject
    private SLogService sLogService;
    @Inject
    private Dao dao;
    @Inject
    private SysRoleService roleService;

    private  String loginname = "public";
    private  String rolecode = "gy-level1";


    @At("")
    @Ok("beetl:/public/home.html")
    public Context home() {
        Context ctx = Lang.context();
        Sys_user user = userService.fetch(Cnd.where("loginname", "=", loginname));
        userService.fillMenu(user);
        ctx.set("user",user );
        user.getSecondMenus();
        return ctx;
    }


    /**
     * @function: 登陆界面
     * @param:
     * @return:
     * @note: 直接进入后台的系统登陆界面
     */
    @At("/login")
    @Ok("beetl:/platform/sys/login.html")
    public void login(){}

    /**
     * @function: 注册界面
     * @param:
     * @return:
     * @note:
     */
    @At("/reg")
    @Ok("beetl:/public/reg.html")
    public void reg(){}

    @At("/fuck")
    @Ok("beetl:/public/reg.html")
    public void fuck(){}

    /**
    * @function: 雇员账号
    * @param:
    * @return:
    * @note: 仅仅是注册基本的账号信息
    */
    @At("/doreg")
    @Ok("json")
    @SLog(tag = "新雇员注册", msg = "用户名:${args[0].loginname}")
    public Object addDo(@Param("..") Sys_user user, HttpServletRequest req) {
        try {
            RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            String salt = rng.nextBytes().toBase64();
            String hashedPasswordBase64 = new Sha256Hash(user.getPassword(), salt, 1024).toBase64();

            // 账号
            user.setSalt(salt);
            user.setPassword(hashedPasswordBase64);
            user.setLoginPjax(true);
            user.setLoginCount(0);
            user.setLoginAt(0);

            // Begin transaction
            // 事务操作：插入用户与绑定角色
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    userService.insert(user);
                    String test =  roleService.fetch(Cnd.where("code", "=", rolecode)).getId();
                    dao.insert("sys_user_role", org.nutz.dao.Chain.make("userId", user.getId()).add("roleId", roleService.fetch(Cnd.where("code", "=", rolecode)).getId()));
                }
            });
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

}


