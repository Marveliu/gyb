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
import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmFeedbackService;
import com.marveliu.framework.util.statusUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import sun.tools.jconsole.inspector.XMBean;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmFeedbackService.class)
public class XmFeedbackServiceImpl extends BaseServiceImpl<xm_feedback> implements XmFeedbackService {


    private final static Log log = Logs.getLog(XmFeedbackService.class);

    public XmFeedbackServiceImpl(Dao dao) {
        super(dao);
    }


    /**
     * 获得项目反馈总数
     *
     * @param xminfid
     * @return
     */
    @Override
    public int getXmfeedbackCount(String xminfid) {
        Cnd cnd = Cnd.NEW();
        cnd.and("xminfid", "=", xminfid);
        int count = this.dao().count(xm_feedback.class, cnd);
        return count;
    }

    /**
     * 是否运行进行反馈
     *
     * @param xminfid
     * @return
     */
    @Override
    public boolean isXmeedbackAllowed(String xminfid) {

        xm_feedback xmFeedback = this.getLatestXmfeedback(xminfid);
        // 没有反馈过
        if (Lang.isEmpty(xmFeedback)) {
            return true;
        } else {
            // 上次的反馈完成
            if (xmFeedback.getStatus() == statusUtil.XM_FEEDBACK_FINISH) {
                return true;
            }
        }
        return false;
    }

    /**
     * 雇员添加项目反馈
     *
     * @param xmFeedback
     * @return
     */
    @Override
    public xm_feedback addXmfeedback(xm_feedback xmFeedback) {

        if (!isXmeedbackAllowed(xmFeedback.getXminfid())) return null;
        try {
            xm_feedback parent = getLatestXmfeedback(xmFeedback.getXminfid());
            xmFeedback.setParentid(0);
            xmFeedback.setCode("fk_"+xmFeedback.getXminfid().split("_")[1]+"_0");
            if(!Lang.isEmpty(parent)){
                xmFeedback.setParentid(parent.getId());
                int count = Integer.valueOf(parent.getCode().split("_")[2])+1;
                xmFeedback.setCode("fk_"+xmFeedback.getXminfid().split("_")[1]+"_"+count);
            }
            xmFeedback.setFileurl(Strings.sBlank(xmFeedback.getFileurl(),""));
            xmFeedback.setStatus(statusUtil.XM_FEEDBACK_INIT);
            xmFeedback.setAt(Times.getTS());

            return this.insert(xmFeedback);
        } catch (Exception e) {
            log.error("雇员添加项目反馈失败", e);
        }
        return null;
    }

    /**
     * 雇员提交项目反馈
     *
     * @param xmfeedbackid
     * @return
     */
    @Override
    public boolean commitXmfeedback(long xmfeedbackid) {
        if(getXmfeedbackStatus(xmfeedbackid) != statusUtil.XM_FEEDBACK_INIT) return false;
        Chain chain = Chain.make("status", statusUtil.XM_FEEDBACK_COMMIT);
        Cnd cnd = Cnd.where("id", "=", xmfeedbackid);
        // todo:邮件通知对应项目经理
        return this.update(chain, cnd) != 0;
    }

    /**
     * 项目经理审批项目反馈
     *
     * @param xmFeedback
     * @return
     */
    @Override
    public boolean checkXmfeedback(xm_feedback xmFeedback) {
        if(getXmfeedbackStatus(xmFeedback.getId()) != statusUtil.XM_FEEDBACK_COMMIT) return false;
        try {
            xmFeedback.setStatus(statusUtil.XM_FEEDBACK_CHECKING);
            // deadline 添加定时任务
            // updateIgnoreNull
            return this.updateIgnoreNull(xmFeedback) != 0;
        } catch (Exception e) {
            log.error("项目经理审批失败", e);
        }
        return false;
    }

    /**
     * 项目经理确认项目反馈
     *
     * @param xmfeedbackid
     * @param flag
     * @return
     */
    @Override
    public boolean confirmXmfeedback(long xmfeedbackid, Boolean flag) {
        if(getXmfeedbackStatus(xmfeedbackid) != statusUtil.XM_FEEDBACK_CHECKING) return false;
        Chain chain = null;
        if(flag){
            chain = Chain.make("status",statusUtil.XM_FEEDBACK_FINAL);
            // 设置xm_inf状态
            this.dao().update(xm_inf.class,Chain.make("status",statusUtil.XM_INF_DONE),Cnd.where("id","=",this.fetch(xmfeedbackid).getXminfid()));
            // todo: 通知进行项目结算流程
        }else {
            chain = Chain.make("status",statusUtil.XM_FEEDBACK_FINISH);
        }
        Cnd cnd = Cnd.where("id","=",xmfeedbackid);
        // todo:邮件通知对应项目经理
        return this.update(chain,cnd)!=0;
    }


    /**
     * 获得项目最新一次的反馈
     * @param xminfid
     * @return
     */
    public xm_feedback getLatestXmfeedback(String xminfid) {
        try {
            return this.query(Cnd.where("xminfid", "=", xminfid).desc("at")).get(0);
        } catch (Exception e) {
            log.error("getLatestXmfeedback fail", e);
        }
        return null;
    }

    /**
     * 根据项目反馈编号获得任务书
     *
     * @param xmfeedbackid
     * @return
     */
    @Override
    public xm_task getXmtaskByXmfeedbackid(String xmfeedbackid) {
        return this.dao().fetch(xm_task.class,Cnd.where("id","=",this.fetch(xmfeedbackid)));
    }

    public int getXmfeedbackStatus(long xmfeedbackid){
        xm_feedback xmFeedback =  this.fetch(xmfeedbackid);
        if(Lang.isEmpty(xmFeedback)) return -1;
        return xmFeedback.getStatus();
    }
}