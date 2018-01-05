package cn.wizzer.app.gy.modules.services;

import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.app.gy.modules.models.gy_inf;

public interface GyInfService extends BaseService<gy_inf>{

    //  获得用户id
    public gy_inf getGyByUserId(String userid);

}
