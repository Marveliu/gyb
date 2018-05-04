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
import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmBillService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmBillService.class)
public class XmBillServiceImpl extends BaseServiceImpl<xm_bill> implements XmBillService {

    private static final int XM_BILL_INIT = 0;
    private static final int XM_BILL_CHECKING = 1;
    private static final int XM_BILL_PAYING = 2;
    private static final int XM_BILL_PAYED = 3;
    private static final int XM_BILL_ERROR = 4;

    public XmBillServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 建立账单
     * after accept xmInf
     *
     * @param xmInf
     * @param uid
     * @return
     */
    @Override
    public xm_bill initXmbill(xm_inf xmInf,String uid) {
        try {
            // 账单信息
            xm_bill bill = new xm_bill();
            bill.setXminfid(xmInf.getId());
            bill.setOpBy(uid);
            bill.setOpAt(Times.getTS());
            bill.setPaysum(Float.valueOf(xmInf.getAward()));
            return this.insert(bill);
        }catch (Exception e){

        }
        return null;
    }
}