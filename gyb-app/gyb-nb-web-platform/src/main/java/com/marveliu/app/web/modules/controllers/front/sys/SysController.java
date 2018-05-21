package com.marveliu.app.web.modules.controllers.front.sys;
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
import com.marveliu.app.web.commons.slog.SLogService;
import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.gy.GyAuthService;
import com.marveliu.framework.services.gy.GyInfService;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.tmsg.PasswordTMsg;
import com.marveliu.framework.services.sys.SysMenuService;
import com.marveliu.framework.services.sys.SysMsgService;
import com.marveliu.framework.services.sys.SysRoleService;
import com.marveliu.framework.services.sys.SysUserService;
import com.marveliu.framework.util.ConfigUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.random.R;
import org.nutz.lang.util.Context;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marveliu
 * @since 21/05/2018
 **/

@IocBean
@At("/public")
public class SysController {
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private SysMenuService menuService;
    @Inject
    @Reference
    private SysUserService sysUserService;
    @Inject
    @Reference
    private SysMsgService sysMsgService;
    @Inject
    @Reference
    private SLogService sLogService;
    @Inject
    @Reference
    private GyInfService gyInfService;
    @Inject
    @Reference
    private GyAuthService gyAuthService;
    @Inject
    private SysRoleService roleService;


    private String loginname = "public";
    private String rolecode = "gy1";            //注册雇员绑定角色类型


    @At("")
    @Ok("beetl:/public/home.html")
    public Context home() {
        Context ctx = Lang.context();
        Sys_user user = sysUserService.fetch(Cnd.where("loginname", "=", loginname));
        sysUserService.fillMenu(user);
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
    @SLog(type = "sys", tag = "密码找回", msg = "用户名:${args[0]},用户邮箱:${args[1]}")
    public Object doPassword(
            @Param("loginname") String loginname,
            @Param("email") String email,
            HttpServletRequest req) {
        try {
            Sys_user sysUser = sysUserService.fetch(Cnd.where("loginname", "=", loginname));
            if (Lang.isEmpty(sysUser)) return Result.error("用户未注册!");
            if (!sysUser.getEmail().equals(email)) return Result.error("用户邮箱不匹配!");

            RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            String salt = rng.nextBytes().toBase64();
            String pwd = R.captchaNumber(6);
            String hashedPasswordBase64 = new Sha256Hash(pwd, salt, 1024).toBase64();
            sysUser.setPassword(hashedPasswordBase64);
            sysUser.setSalt(salt);
            sysUserService.updateIgnoreNull(sysUser);
            Lang.runInAnThread(new Runnable() {
                @Override
                public void run() {
                    TMsg tMsg = new PasswordTMsg(loginname, pwd);
                    Sys_msg sysMsg = new Sys_msg();
                    sysMsg.setRevid(sysUser.getId());
                    sysMsg.setRevaccount(sysUser.getEmail());
                    sysMsg.setMsg(Json.toJson(tMsg));
                    sysMsg.setType(ConfigUtil.SYS_MSG_TYPE_EMAIL);
                    sysMsg.setTag(ConfigUtil.SYS_MSG_TAG_SYS);
                    sysMsg.setTmsgclass(tMsg.getTMsgClass());
                    sysMsgService.pushMsg(sysMsg);
                }
            });
            return Result.success(loginname+"密码已经重置，请到邮箱查看:"+email);
        } catch (Exception e) {
            log.error("找回密码失败", e);
        }
        return Result.error("system.error");
    }


    @At("/doreg")
    @Ok("json")
    @AdaptBy(type = WhaleAdaptor.class)
    @SLog(type = "sys", tag = "新用户注册", msg = "用户名:${args[0]},用户邮箱:${args[1]}")
    public Object doReg(
            @Param("username") String username,
            @Param("email") String email,
            @Param("password") String password,
            HttpServletRequest req
    ) {
        try {
            // 验证用户名，邮箱是否被注册
            if (null != sysUserService.fetch(Cnd.where("email", "=", email).and("emailChecked", "=", true))) {
                return Result.error("邮箱已经注册！");
            }
            // 验证账号
            if (null != sysUserService.fetch(Cnd.where("username", "=", username).and("disabled", "=", false))) {
                return Result.error("账号已存在！");
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
            user.setLoginIp(Lang.getIP(req));
            user.setEmail(email);
            user.setDisabled(false);
            user.setUnitid("fd02b028caed459590b68dde6d47f115");
            user.setEmailChecked(false);//邮箱未验证
            if (!Lang.isEmpty(sysUserService.regUser(user))) {
                return Result.success("注册成功，请前往邮箱进行账号激活！");
            }
        } catch (Exception e) {
            log.error("用户注册失败!");
        }
        return Result.error("system.error");
    }

}