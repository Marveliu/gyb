package cn.wizzer.app.web.commons.util;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.sys.modules.models.Sys_user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;

/**
 * Created by 89792 on 2017/11/22 0022.
 */

@IocBean
public class UserInfUtil {

    /**
     * @function: 查询当前登陆的雇主信息
     * @param:
     * @return:
     * @note:
     */
    public static gz_inf getCurrentGz(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gz_inf gz = Mvcs.getIoc().get(Dao.class).fetch(gz_inf.class,Cnd.where("userid","=",user.getId()));
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
        gz_inf gz = Mvcs.getIoc().get(Dao.class).fetch(gz_inf.class,Cnd.where("userid","=",user.getId()));
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
        gy_inf gy = Mvcs.getIoc().get(Dao.class).fetch(gy_inf.class,Cnd.where("userid","=",user.getId()));
        return gy;
    }

    public static String getCurrentGyid(){
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        gy_inf gy = Mvcs.getIoc().get(Dao.class).fetch(gy_inf.class,Cnd.where("userid","=",user.getId()));
        return gy.getGyid();
    }
}
