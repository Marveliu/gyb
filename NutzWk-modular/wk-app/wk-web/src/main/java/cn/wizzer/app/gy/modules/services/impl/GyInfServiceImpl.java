package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.services.GyInfService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean(args = {"refer:dao"})
public class GyInfServiceImpl extends BaseServiceImpl<gy_inf> implements GyInfService {

    @Inject
    private GyInfService gyInfService;

    public GyInfServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 通过用户id获得雇员信息
     * @param userid
     * @return
     */
    public gy_inf getGyByUserId(String userid){
        return this.dao().fetch(gy_inf.class,Cnd.where("userid","=",userid));
    }


    public Sys_user getUserByGyid(String gyid){
        String userid = this.dao().fetch(gy_inf.class,gyid).getUserid();
        return this.dao().fetch(Sys_user.class,Cnd.where("id","=",userid));
    }
    /**
     * 根据雇员id获取技能信息
     * @param gyid
     * @return
     */
    public List<gy_skill> getSkillsByGyid(String gyid){
        gy_inf gy = this.fetch(gyid);
        gy = this.fetchLinks(gy,"gyskills");
        return gy.getGyskills();
    }

    /**
     * 根据雇员id获取支付信息
     * @param gyid
     * @return
     */
    public List<gy_pay> getPaysByGyid(String gyid){
        gy_inf gy = this.fetch(gyid);
        gy = this.fetchLinks(gy,"gy_pays");
        return gy.getGypays();
    }

    /**
     *
     * @param gyid
     * @return
     */
    public boolean ifEmailChecked(String gyid){
        Sys_user user = this.getUserByGyid(gyid);
        return user.isEmailChecked();
    }


}
