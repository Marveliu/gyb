package cn.wizzer.app.web.commons.services.gy;

import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.sys.modules.services.SysRoleService;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Logs;
import sun.rmi.runtime.Log;

/**
 * 雇员模块
 *
 * @Author Marveliu
 * @Create 2018/1/5 0005.
 */

@IocBean
public class GyService {

    private static final org.nutz.log.Log log = Logs.get();

    @Inject
    private SysRoleService sysRoleService;
    @Inject
    private GyInfService gyInfService;
    @Inject
    private SysUserService sysUserService;
    @Inject
    private Dao dao;

    /**
     * 通过用户id修改雇员角色，雇员尤其仅有一个角色
     *
     * @param userId
     * @param rolecode
     * @return
     */
    public boolean updateGyRole(String userId, String rolecode){
        try {
            String roleId =  sysRoleService.getRoleFormCode(rolecode).getId();
            dao.update("sys_user_role", org.nutz.dao.Chain.make("roleId",roleId),Cnd.where("userId","= ",userId));
            return true;
        }catch (Exception e){
            log.debug(e);
        }
        return false;
    }


    /**
     *  检查用户是否注册了雇员信息
     * @param userId
     * @return
     */
    public boolean checkGyRegByUsrid(String userId){

            if(null != gyInfService.getGyByUserId(userId))
            {
                return true;
            }
            return false;
    }


}
