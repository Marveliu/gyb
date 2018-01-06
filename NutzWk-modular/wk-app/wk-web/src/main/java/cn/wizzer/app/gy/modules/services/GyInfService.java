package cn.wizzer.app.gy.modules.services;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.app.gy.modules.models.gy_inf;

import java.util.List;

public interface GyInfService extends BaseService<gy_inf>{

    //  获得用户id
    public gy_inf getGyByUserId(String userid);


    // 根据雇员id获得技能信息
    public List<gy_skill> getSkillsByGyid(String gyid);

    // 根据雇员id获得支付
    public List<gy_pay> getPaysByGyid(String gyid);
}
