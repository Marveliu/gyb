package com.marveliu.app.xm.modules.services.impl;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.services.gy.GyPayService;
import com.marveliu.framework.util.statusUtil;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmBillService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmBillService.class)
public class XmBillServiceImpl extends BaseServiceImpl<xm_bill> implements XmBillService {

    private final static Log log = Logs.getLog(XmBillService.class);


    public XmBillServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 建立账单
     *
     * @param xminfid
     * @param award
     * @param uid
     * @return
     */
    @Override
    public xm_bill initXmbill(String xminfid,float award, String uid) {
        if(!Lang.isEmpty(this.fetch(Cnd.where("xminfid","=",xminfid)))){
            log.error("重复创建项目账单："+xminfid);
            return null;
        }
        try {
            // 账单信息
            xm_bill bill = new xm_bill();
            bill.setXminfid(xminfid);
            bill.setStatus(statusUtil.XM_BILL_INIT);
            bill.setOpBy(uid);
            bill.setAt(Times.getTS());
            return this.insert(bill);
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 更新项目支付方式
     *
     * @param xmbillid
     * @param payid
     * @param gyid
     * @return
     */
    @Override
    public boolean checkXmbillByGy(String xmbillid, String payid, String gyid) {
        xm_bill xmBill = this.fetch(xmbillid);
        // 检查 账单存在，账单状态
        if(!Lang.isEmpty(xmBill) && xmBill.getStatus() == statusUtil.XM_BILL_CHECKING){
            Cnd cnd = Cnd.where("id","=",xmbillid);
            Chain chain = Chain.make("gypayid",payid).add("status",statusUtil.XM_BILL_PAYING).add("opAt",Times.getTS());
            return this.update(chain,cnd)!=0;
        }
        return false;
    }

    /**
     * 财务提交最终项目账单,支付完成
     *
     * @param xmbillid
     * @param sysuserinfid
     * @return
     */
    @Override
    public boolean commitXmbill(String xmbillid, String sysuserinfid) {
        xm_bill xmBill = this.fetch(xmbillid);
        if(!Lang.isEmpty(xmBill) && xmBill.getStatus() == statusUtil.XM_BILL_PAYING){
            Cnd cnd = Cnd.where("id","=",xmbillid);
            Chain chain = Chain.make("realgypayid",xmBill.getGypayid()).add("status",statusUtil.XM_BILL_PAYED).add("payby",sysuserinfid).add("opAt",Times.getTS());
            return this.update(chain,cnd)!=0;
        }
        return false;
    }


}