package cn.wizzer.app.web.commons.util;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.shiro.realm.PlatformAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.cache.CacheManager;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.nutz.mvc.Mvcs.getIoc;

/**
 * Created by 89792 on 2017/11/22 0022.
 */

@IocBean
public class UserInfUtil {


    private  final static Log log = Logs.get();

    @Inject
    private SysUserService sysUserService;


    /**
     * 获得用户以及相关的角色单位信息
     *
     * @return
     */
    public  Sys_user getCurrentUser(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        return  user;
    }

    /**
     * 从数据库中获得user,并更新shiroseesion
     * @param req
     * @return
     */
    public  Sys_user getCurrentUser(HttpServletRequest req){

        // 如果从shiro里面拿，会出现和数据不一致的情况
        Subject subject = SecurityUtils.getSubject();

        // HttpSession session = req.getSession();
        // Toolkit.showHttpSessitonAttr(session);
        // String uid = session.getAttribute("uid").toString();
        // Sys_user user  = sysUserService.fetch(uid);

        AuthenticationToken token  = (AuthenticationToken) req.getSession().getAttribute("sysUserToken");
        subject.login(token);
        Sys_user user = (Sys_user) subject.getPrincipal();

        // RealmSecurityManager  securityManager  = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        // PlatformAuthorizingRealm platformRealm = (PlatformAuthorizingRealm) securityManager.getRealms().iterator().next();
        // subject.releaseRunAs();
        // String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        // subject.runAs(principals);
        // myRealm.getAuthorizationCache().remove(subject.getPrincipals());
        // subject.releaseRunAs();
        return  user;
    }


    /**
     * @function: 查询当前登陆的雇主信息
     * @param:
     * @return:
     * @note:
     */
    public static gz_inf getCurrentGz(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gz_inf gz = getIoc().get(Dao.class).fetch(gz_inf.class,Cnd.where("userid","=",user.getId()));
        return  gz;
    }


    /**
    * @function: 返回登陆的雇员
    * @param:
    * @return:
    * @note:
    */
    public static String getCurrentGzid(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();

        gz_inf gz = getIoc().get(Dao.class).fetch(gz_inf.class,Cnd.where("userid","=",user.getId()));
        return  gz.getId();
    }

    /**
     * @function: 查询当前登陆的雇员信息
     * @param:
     * @return:
     * @note:
     */
    public static gy_inf getCurrentGy(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gy_inf gy = getIoc().get(Dao.class).fetch(gy_inf.class,Cnd.where("userid","=",user.getId()));
        return gy;
    }

    public static String getCurrentGyid(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gy_inf gy = getIoc().get(Dao.class).fetch(gy_inf.class,Cnd.where("userid","=",user.getId()));
        return gy.getId();
    }

    public static String getCurrentGyRoleId(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gy_inf gy = getIoc().get(Dao.class).fetch(gy_inf.class,Cnd.where("userid","=",user.getId()));
        return gy.getId();
    }


    public static void setCndByRole(Cnd cnd){

        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();

//        List<Sys_role> roles =  Mvcs.getIoc().get(Dao.class).query(Sys_role.class,Cnd.NEW());
//        for( Sys_role role : roles){
//            currentUser.isPermitted(role.getCode());
//        }

        // 判断权限标示，获得项目信息
        if(currentUser.isPermitted("sysadmin") || currentUser.isPermitted("cpo")){
            return;
        }else if (currentUser.isPermitted("gy0") || currentUser.isPermitted("gy1") || currentUser.isPermitted("gy2")){
            cnd.and("gyid","=",getCurrentGyid());
        }else if(currentUser.isPermitted("pm")){
            cnd.and("author","=",getCurrentGzid());
        }

        //todo：异常报错处理
    }

}
