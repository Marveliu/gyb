package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseService;

public interface XmBillService extends BaseService<xm_bill> {


    /**
     * 建立账单
     *
     * @param xminfid
     * @param award
     * @param uid
     * @return
     */
    public xm_bill initXmbill(String xminfid,float award, String uid);


    /**
     * 雇员确认项目支付方式
     * @param xmbillid
     * @param payid
     * @return
     */
    public boolean checkXmbillByGy(String xmbillid,String payid,String gyid);


    /**
     * 财务提交最终项目账单，支付完成
     * @param xmbillid
     * @param sysuserinfid     管理员内部账号
     * @return
     */
    public boolean commitXmbill(String xmbillid,String sysuserinfid);



}
