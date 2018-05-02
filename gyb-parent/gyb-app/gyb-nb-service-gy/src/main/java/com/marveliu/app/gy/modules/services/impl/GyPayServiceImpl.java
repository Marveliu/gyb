package com.marveliu.app.gy.modules.services.impl;
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
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.gy.GyPayService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GyPayService.class)
public class GyPayServiceImpl extends BaseServiceImpl<gy_pay> implements GyPayService {

    public GyPayServiceImpl(Dao dao) {
        super(dao);
    }


    /**
     * 获得默认支付方式
     *
     * @param gyid
     * @return
     */
    @Override
    public gy_pay getFirstPay(String gyid) {
        return this.fetch(Cnd.where("gyid","=",gyid).and("first","=",true));
    }

    /**
     * 获得支付方式的雇员编号
     *
     * @param payid
     * @return
     */
    @Override
    public String getGyidByPayid(String payid) {
        return this.fetch(Cnd.where("payid","=",payid)).getGyid();
    }



}
