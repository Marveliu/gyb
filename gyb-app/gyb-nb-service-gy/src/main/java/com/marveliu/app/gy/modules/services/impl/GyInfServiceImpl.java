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
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.gy.GyInfService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GyInfService.class)
public class GyInfServiceImpl extends BaseServiceImpl<gy_inf> implements GyInfService {


    public GyInfServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 通过用户id获得雇员信息
     *
     * @param userid
     * @return
     */
    @Override
    public gy_inf getGyByUserId(String userid) {
        return this.dao().fetch(gy_inf.class, Cnd.where("userid", "=", userid));
    }

    /**
     * 获得雇员的用户编号
     *
     * @param gyid
     * @return
     */
    @Override
    public String getUidByGyid(String gyid) {
        return this.fetch(Cnd.where("id", "=", gyid)).getUserid();
    }

    /**
     * 启用或者禁用雇员
     *
     * @param gyid
     * @param flag true 启用 false 禁用
     * @return
     */
    @Override
    public Boolean setGyStatus(String gyid, Boolean flag) {
        Cnd cnd = Cnd.where("id", "=", gyid);
        Chain chain = Chain.make("disabled", !flag);
        if (this.dao().update(gy_inf.class, chain, cnd) != 0) {
            return true;
        }
        return false;
    }

    public boolean setQq(String userid, String qq) {
        Chain chain = Chain.make("qq", qq);
        Cnd cnd = Cnd.where("userid", "=", userid);
        if (this.dao().update(gy_inf.class, chain, cnd) != 0) {
            return true;
        }
        return false;
    }


}