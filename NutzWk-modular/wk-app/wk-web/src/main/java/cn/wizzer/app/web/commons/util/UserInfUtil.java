package cn.wizzer.app.web.commons.util;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

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
        return gy.getGyid();
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
