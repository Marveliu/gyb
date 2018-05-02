package com.marveliu.framework.services.xm;
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

import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

public interface XmFacadeService {


    /**
     * 获得申请的任务信息
     * @param xmapplyid
     * @return
     */
    public xm_task getTaskByAppyid(String xmapplyid);

    /**
     * 检查雇员是否为项目的拥有者
     * @param xminfid
     * @param gyid
     * @return
     */
    public boolean isGyForXm(String xminfid,String gyid);

    /**
     * 立项
     * @param taskid
     * @param gyid
     * @param uid
     * @return
     */
    public xm_inf initXminf(String taskid, String gyid, String uid);

    /**
     * 项目结算
     * @param xminfid
     * @param uid
     * @return
     */
    public boolean initXmFinal(String xminfid,String uid);
}
