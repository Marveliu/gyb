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
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmInfService;
import com.marveliu.framework.util.ConfigUtil;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmInfService.class)
public class XmInfServiceImpl extends BaseServiceImpl<xm_inf> implements XmInfService {
    public XmInfServiceImpl(Dao dao) {
        super(dao);
    }




    /**
     * 初始化项目信息
     * @param xmTask
     * @param gyid
     * @param uid
     * @return
     */
    @Override
    public xm_inf initXminf(xm_task xmTask, String gyid, String uid){
        try {
            // 项目信息
            xm_inf xmInf = new  xm_inf();
            xmInf.setGyid(gyid);
            xmInf.setXmtaskid(xmTask.getId());
            xmInf.setAt(Times.getTS());
            xmInf.setOpAt(Times.getTS());
            xmInf.setOpBy(uid);
            xmInf.setStatus(ConfigUtil.XM_INF_DOING);
            return this.insert(xmInf);
        }catch (Exception e){

        }
        return null;
    }
}