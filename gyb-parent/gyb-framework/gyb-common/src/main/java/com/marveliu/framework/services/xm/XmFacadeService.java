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
     *
     * @param xmapplyid
     * @return
     */
    public xm_task getTaskByAppyid(String xmapplyid);

    /**
     * 检查雇员是否为项目的拥有者
     *
     * @param xminfid
     * @param gyid
     * @return
     */
    public boolean isGyForXm(String xminfid, String gyid);


    /**
     * 受理任务申请，并建立
     * 1. 项目
     * 2. 账单
     *
     * @param xmapplyid
     * @param uid
     * @return
     */
    public xm_inf acceptXmapply(String xmapplyid, String uid);

    /**
     * 进行项目结算
     *
     * @param xminfid
     * @param xmEvaluationGrade
     * @param xmEvaluationNote
     * @param xmBillNote
     * @param uid
     * @return
     */
    public boolean initXmFinal(String xminfid, float xmEvaluationGrade, String xmEvaluationNote, String xmBillNote, String uid);
}
