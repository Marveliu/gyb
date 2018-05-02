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
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmTaskService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/
@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmTaskService.class)
public class XmTaskServiceImpl extends BaseServiceImpl<xm_task> implements XmTaskService {

    private static final int XM_TASK_INIT = 0;
    private static final int XM_TASK_PUBLISH = 1;
    private static final int XM_TASK_APPLYING = 2;
    private static final int XM_TASK_DOING = 3;
    private static final int XM_TASK_FINISH = 4;


    public XmTaskServiceImpl(Dao dao) {
        super(dao);
    }


    /**
     * 启用或者禁用任务书
     * @param xmtaskid
     * @param flag true 启用 false 禁用
     * @return
     */
    @Override
    public Boolean setXmTaskStatus(String xmtaskid, Boolean flag) {
        Cnd cnd = Cnd.where("id","=",xmtaskid);
        Chain chain = Chain.make("disabled",!flag);
        if(this.update(chain,cnd)!=0)
        {
            return true;
        }
        return false;
    }
}
