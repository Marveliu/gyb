package com.marveliu.framework.services.gy;


import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.services.base.BaseService;

public interface GyPayService extends BaseService<gy_pay> {

    // 获得首要支付方式
    public gy_pay getFirstPay(String gyid);

    // 通过支付id获得用户本人
    public gy_inf getGyByPayid(String payid);
}
