package com.marveliu.app.modules.services.impl;
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
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.gy.gy_skill;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.gy.GyInfSubService;
import com.marveliu.framework.services.gy.GySkillSubService;
import com.marveliu.framework.services.library.LibSkillService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GyInfSubService.class)
public class GyInfSubSubServiceImpl extends BaseServiceImpl<gy_inf> implements GyInfSubService {



    public GyInfSubSubServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 通过用户id获得雇员信息
     * @param userid
     * @return
     */
    public gy_inf getGyByUserId(String userid){
        return this.dao().fetch(gy_inf.class,Cnd.where("userid","=",userid));
    }

    /**
     * 获得雇员的用户编号
     *
     * @param gyid
     * @return
     */
    @Override
    public String getUserByGyid(String gyid) {
        return this.dao().fetch(gy_inf.class,Cnd.where("id","=",gyid)).getUserid();
    }

    public boolean setQq(String userid , String qq)
    {
        Chain chain = Chain.make("qq",qq);
        Cnd cnd =Cnd.where("userid","=",userid);
        if(this.dao().update(gy_inf.class,chain,cnd)!=0)
        {
            return true;
        }
        return false;
    }

}