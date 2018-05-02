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
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmApplyService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmApplyService.class)
public class XmApplyServiceImpl extends BaseServiceImpl<xm_apply> implements XmApplyService {

    public XmApplyServiceImpl(Dao dao) {
        super(dao);
    }


    /**
     * 受理项目申请
     * @param xmapplyid
     * @param flag true 通过 false 不通过
     * @return
     */
    public Boolean setXmApplyStatus(String xmapplyid, Boolean flag) {
        Cnd cnd = Cnd.where("id","=",xmapplyid);
        Chain chain = Chain.make("disabled",!flag);
        if(this.dao().update(gy_inf.class,chain,cnd)!=0)
        {
            return true;
        }
        return false;
    }

}