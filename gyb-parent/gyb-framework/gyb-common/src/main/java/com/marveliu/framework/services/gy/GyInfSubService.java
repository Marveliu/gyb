package com.marveliu.framework.services.gy;



import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.gy.gy_skill;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.base.BaseService;

import java.util.List;

public interface GyInfSubService extends BaseService<gy_inf> {

    /**
     * 通过用户编号获得雇员信息
     * @param userid
     * @return
     */
    public gy_inf getGyByUserId(String userid);

    /**
     * 获得雇员的用户编号
     * @param gyid
     * @return
     */
    public String getUserByGyid(String gyid);


}
