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

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.xm.xm_limit;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.library.LibSkillService;
import com.marveliu.framework.services.xm.XmTaskService;
import com.marveliu.framework.util.ConfigUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/
@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmTaskService.class)
public class XmTaskServiceImpl extends BaseServiceImpl<xm_task> implements XmTaskService {


    @Inject
    @Reference
    private LibSkillService libSkillService;

    public XmTaskServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 启用或者禁用任务书
     *
     * @param xmtaskid
     * @param flag     true 启用 false 禁用
     * @return
     */
    @Override
    public Boolean setXmTaskStatus(String xmtaskid, Boolean flag) {
        Cnd cnd = Cnd.where("id", "=", xmtaskid);
        Chain chain = Chain.make("disabled", !flag);
        if (flag) {
            chain.add("status", ConfigUtil.XM_TASK_APPLYING);
        } else {
            chain.add("status", ConfigUtil.XM_TASK_PUBLISH);
        }
        if (this.update(chain, cnd) != 0) {
            return true;
        }
        return false;
    }


    /**
     * 更新任务书
     * 删除之前的任务书技能限定，重新添加
     *
     * @param xmtask
     * @return
     */
    @Override
    public Boolean updateXmtask(xm_task xmtask) {
        try {
            //清空之前的技能限制
            xm_task oldxmtask = this.fetchLinks(this.fetch(xmtask.getId()), null);
            this.dao().deleteLinks(oldxmtask, "xmlimits");
            this.insertLinks(xmtask, "xmlimits");
            int result = this.updateIgnoreNull(xmtask);
            return result == 1;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 获得xm_task所有信息
     *
     * @param xmtaskid
     * @return
     */
    @Override
    public xm_task getXmtaskDetail(String xmtaskid) {
        try {
            xm_task xmTask = this.fetchLinks(this.fetch(xmtaskid), null);
            List<xm_limit> limits = xmTask.getXmlimits();
            List<xm_limit> xmlimits = new ArrayList<>();
            for (xm_limit limit : limits) {
                limit = libSkillService.fetchLinks(limit, "skill");
                xmlimits.add(limit);
            }
            xmTask.setXmlimits(xmlimits);
            return xmTask;
        } catch (Exception e) {

        }
        return null;
    }


    /**
     * 删除任务书
     *
     * @param xmtaskid
     * @return
     */
    @Override
    public boolean deleteXmtask(String xmtaskid) {
        try {
            this.delete(xmtaskid);
            this.dao().execute(
                    Sqls.create("delete from xm_limit where xmtaskid = @xmtaskid")
                            .setParam("xmtaskid", xmtaskid)
            );
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
