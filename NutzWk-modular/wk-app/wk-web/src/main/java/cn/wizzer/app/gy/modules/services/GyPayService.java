package cn.wizzer.app.gy.modules.services;

import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.app.gy.modules.models.gy_pay;

public interface GyPayService extends BaseService<gy_pay>{

    public gy_pay getFirstPay(String gyid);
}
