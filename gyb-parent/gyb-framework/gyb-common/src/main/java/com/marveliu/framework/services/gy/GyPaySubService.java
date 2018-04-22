package com.marveliu.framework.services.gy;


import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.services.base.BaseService;

public interface GyPaySubService extends BaseService<gy_pay> {

    /**
     * 获得默认支付方式
     * @param gyid
     * @return
     */
    public gy_pay getFirstPay(String gyid);

    /**
     * 获得支付方式的雇员编号
     * @param payid
     * @return
     */
    public String getGyidByPayid(String payid);

}
