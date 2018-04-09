package cn.wizzer.app.gy.modules.services;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.app.gy.modules.models.gy_inf;

import java.util.List;

public interface GyInfService extends BaseService<gy_inf>{

    /**
     * 获得雇员信息
     * @param userid
     * @return
     */
    public gy_inf getGyByUserId(String userid);

    /**
     * 获得雇员账号信息
     * @param gyid
     * @return
     */
    public Sys_user getUserByGyid(String gyid);


    /**
     * 获得雇员技能信息
     * @param gyid
     * @return
     */
    public List<gy_skill> getSkillsByGyid(String gyid);

    /**
     * 获得雇员支付信息
     * @param gyid
     * @return
     */
    public List<gy_pay> getPaysByGyid(String gyid);

    /**
     * 雇员邮箱是否验证
     * @param gyid
     * @return
     */
    public boolean ifEmailChecked(String gyid);

    /**
     * 修改qq
     * @param gyid
     * @param qq
     * @return
     */
    public boolean setQq(String gyid , String qq);


}
