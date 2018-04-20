package com.marveliu.framework.services.gy;



import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.gy.gy_skill;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.base.BaseService;

import java.util.List;

public interface GyInfService extends BaseService<gy_inf> {

    //  获得用户id
    public gy_inf getGyByUserId(String userid);

    //  获得用户id
    public Sys_user getUserByGyid(String gyid);


    // 根据雇员id获得技能信息
    public List<gy_skill> getSkillsByGyid(String gyid);

    // 根据雇员id获得支付
    public List<gy_pay> getPaysByGyid(String gyid);

    //  获得用户id
    public boolean ifEmailChecked(String gyid);

    public boolean setQq(String gyid , String qq);


}
