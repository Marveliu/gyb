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
import com.marveliu.framework.util.statusUtil;
import com.marveliu.framework.model.xm.xm_bill;
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


    public XmBillServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 建立账单
     * after accept xmInf
     *
     * @param xminfid
     * @param award
     * @param uid
     * @return
     */
    @Override
    public xm_bill initXmbill(String xminfid,float award, String uid) {
        try {
            // 账单信息
            xm_bill bill = new xm_bill();
            bill.setXminfid(xminfid);
            bill.setStatus(statusUtil.XM_BILL_INIT);
            bill.setOpBy(uid);
            bill.setAt(Times.getTS());
            bill.setPaysum(award);
            return this.insert(bill);
        }catch (Exception e){

        }
        return null;
    }
}