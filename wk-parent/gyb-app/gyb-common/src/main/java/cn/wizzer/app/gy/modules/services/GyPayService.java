package cn.wizzer.app.gy.modules.services;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.app.gy.modules.models.gy_pay;

public interface GyPayService extends BaseService<gy_pay>{

    /**
     * 获得首要支付信息
     * @param gyid
     * @return
     */
    public gy_pay getFirstPay(String gyid);

    /**
     * 获得支付信息的雇员信息
     * @param payid
     * @return
     */
    public gy_inf getGyByPayid(String payid);
}
