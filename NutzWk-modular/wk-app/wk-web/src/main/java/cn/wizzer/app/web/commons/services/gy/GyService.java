package cn.wizzer.app.web.commons.services.gy;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.gy.modules.services.GySkillService;
import cn.wizzer.app.library.modules.models.lib_skill;
import cn.wizzer.app.library.modules.services.LibSkillService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysRoleService;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.services.websocket.WsService;
import cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Logs;
import sun.rmi.runtime.Log;

import java.util.List;

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
    private GySkillService gySkillService;
    @Inject
    private SysUserService sysUserService;
    @Inject
    private LibSkillService libSkillService;
    @Inject
    private WsService wsService;
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

        gy_inf gy = gyInfService.getGyByUserId(userId);
        return !Lang.isEmpty(gy);
    }


    /**
     * 初始化雇员技能信息
     * @param gyid
     * @return
     */
    public boolean initGySkill(String gyid){

        // TODO: 2018/1/6 0006 技能认证懒加载，用的时候再进行初始化
        // 检查雇员技能库现有信息
        List<gy_skill> skills = gyInfService.getSkillsByGyid(gyid);
        List<lib_skill> libSkills = libSkillService.query();
        return  true;
    }

    /**
     * 通过websocket给雇员发送消息
     * @param gyid
     * @param msg
     * @return
     */
    public boolean sendMsgByGyid(String gyid,String msg){
        Sys_user user = gyInfService.getUserByGyid(gyid);
        String wsid = user.getWsid();
        return  wsService.sendMsgByWsid(wsid,msg);
    }


}
