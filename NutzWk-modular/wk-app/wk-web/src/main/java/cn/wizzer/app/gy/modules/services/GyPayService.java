package cn.wizzer.app.gy.modules.services;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.app.gy.modules.models.gy_pay;

public interface GyPayService extends BaseService<gy_pay>{

    // 获得首要支付方式
    public gy_pay getFirstPay(String gyid);

    // 通过支付id获得用户本人
    public gy_inf getGyByPayid(String payid);
}
