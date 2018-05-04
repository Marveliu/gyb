package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.services.base.BaseService;

public interface XmBillService extends BaseService<xm_bill> {




    /**
     * 建立账单
     * after accept apply
     * @param xmInf
     * @param uid
     * @return
     */
    public xm_bill initXmbill(xm_inf xmInf,String uid);
}
