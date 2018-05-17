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
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.model.xm.*;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.tmsg.RegTMsg;
import com.marveliu.framework.util.ConfigUtil;
import com.marveliu.framework.model.sys.Sys_log;
import com.marveliu.framework.services.sys.SysLogService;
import com.marveliu.framework.services.xm.*;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean
@Service(interfaceClass = XmFacadeService.class)
public class XmFacadeServiceImpl implements XmFacadeService {


    private static final Log log = Logs.get();

    @Inject
    private Dao dao;
    @Inject
    private XmInfService xmInfService;
    @Inject
    private XmBillService xmBillService;
    @Inject
    private XmFeedbackService xmFeedbackService;
    @Inject
    private XmEvaluationService xmEvaluationService;
    @Inject
    private XmApplyService xmApplyService;
    @Inject
    private XmTaskService xmTaskService;
    @Inject
    private XmLimitService xmLimitService;

    @Inject
    @Reference
    private SysLogService sysLogService;


    /**
     * 检查雇员是否为项目的拥有者
     *
     * @param xminfid
     * @param gyid
     * @return
     */
    public boolean isGyForXm(String xminfid, String gyid) {
        if (gyid.equals(xmInfService.fetch(xminfid).getGyid())) {
            return true;
        } else {
            return false;
        }
    }


    public xm_task getTaskByAppyid(String xmapplyid) {
        String xmtaskid = xmApplyService.fetch(xmapplyid).getXmtaskid();
        if (Lang.isEmpty(xmtaskid)) return null;
        return xmTaskService.fetch(xmtaskid);
    }


    /**
     * 受理请求
     *
     * @param xmapplyid
     * @param uid
     * @return
     */
    public xm_inf acceptXmapply(String xmapplyid, String uid) {

        xm_apply xmApply = xmApplyService.fetch(xmapplyid);

        // 检查申请信息是否存在且不可重复受理
        if(Lang.isEmpty(xmApply) || xmApply.getStatus() == ConfigUtil.XM_APPLY_FINAL || xmApply.getStatus() == ConfigUtil.XM_APPLY_PASS ) return null;

        // 更新任务书申请状态为完结，同时可以检查任务书是否存在
        if (xmApplyService.setXmApplyStatus(xmapplyid,true,uid)){
            xm_inf xmInf = null;
            xm_bill xmBill = null;
            try {
                xm_task xmTask = xmApplyService.getXmTaskByAppyid(xmapplyid);
                // 更新任务书状态
                xmTask.setStatus(ConfigUtil.XM_TASK_DOING);
                xmTaskService.updateXmtask(xmTask);
                // 建立项目
                xmInf = xmInfService.initXminf(xmTask, xmApplyService.fetch(xmapplyid).getGyid(), uid);
                // 建立表单
                if (!Lang.isEmpty(xmInf)) {
                    xmBill = xmBillService.initXmbill(xmInf.getId(), xmTask.getAward(), uid);
                }
                // 建立日志
                if (!Lang.isEmpty(xmBill)) {
                    Sys_log sysLog = new Sys_log();
                    sysLog.setType("xm");
                    sysLog.setTag("项目建立");
                    sysLog.setSrc(this.getClass().getName() + "#acceptXmapply");
                    sysLog.setMsg("任务：" + xmapplyid + "->项目：" + xmInf.getId());
                    ;
                    sysLog.setOpBy(uid);
                    sysLog.setOpAt(Times.getTS());
                    sysLog.setUsername(uid);
                    sysLogService.insert(sysLog);
                    // todo：邮件类信息通知
                    return xmInf;
                }
            }catch (Exception e){
                log.error("建立项目失败："+xmapplyid,e);
            }
        }
        return null;
    }


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
    public boolean initXmFinal(
            String xminfid,
            float xmEvaluationGrade,
            float paySum,
            String xmEvaluationNote,
            String xmBillNote,
            String uid) {

        int currentStatus = xmInfService.fetch(xminfid).getStatus();

        // 如果已经结算，返回失败
        if( currentStatus != ConfigUtil.XM_INF_DONE) return false;

        try{
            xm_evaluation xmEvaluation = new xm_evaluation();
            xmEvaluation.setXminfid(xminfid);
            xmEvaluation.setGrade(xmEvaluationGrade);
            xmEvaluation.setNote(xmEvaluationNote);
            xm_inf xmInf = xmInfService.fetch(xminfid);

            Chain xmBillChain =  Chain.make("status",ConfigUtil.XM_BILL_CHECKING).add("paysum",paySum).add("note",xmBillNote).add("opAt",Times.getTS());

            Trans.exec(new Atom() {
                @Override
                public void run() {
                    xmEvaluationService.insert(xmEvaluation);

                    // 默认pay由前端显示
                    // if (!Lang.isEmpty(pay)) {
                    //     xmBillChain.add("gypayid",pay.getId());
                    // }

                    xmFeedbackService.update(
                            Chain.make("status",ConfigUtil.XM_FEEDBACK_FINAL),
                            Cnd.where("id","=",xmFeedbackService.getLatestXmfeedback(xminfid).getId()));
                    // 任务书更新为: 完结
                    xmTaskService.update(Chain.make("status",ConfigUtil.XM_TASK_FINISH),Cnd.where("id","=",xmInf.getXmtaskid()));
                    // 项目账单更新为: 雇员审查
                    xmBillService.update(xmBillChain,Cnd.where("xminfid","=",xminfid));
                    // 项目信息更新为: 雇员审查
                    xmInfService.update(Chain.make("status",ConfigUtil.XM_INF_CHECKING),Cnd.where("id","=",xminfid));
                }
            });
            return true;
        }catch (Exception e){
            log.error("项目结算出错:"+xminfid,e);
        }
        return false;
    }

    /**
     * 雇员确认项目结算
     *
     * @param xminfid
     * @param payid
     * @return
     */
    @Override
    public boolean checkXmFinal(String xminfid, String payid) {
        try {
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    //账单转入财务，更新账单时间
                    xmBillService.update(
                            Chain.make("status", ConfigUtil.XM_BILL_PAYING).add("realgypayid", payid).add("at", Times.getTS()),
                            Cnd.where("xminfid", "=", xminfid));
                    //项目完结
                    xmInfService.update(
                            Chain.make("status", ConfigUtil.XM_INF_PAYING),
                            Cnd.where("id", "=", xminfid));
                }
                // Sys_msg sysMsg = new Sys_msg();
                // TMsg tMsg = new (gy_inf.getRealname(),url);
                // sysMsg.setRevid(gy_inf.getUserid());
                // sysMsg.setMsg(Json.toJson(tMsg));
                // sysMsg.setType(ConfigUtil.SYS_MSG_TYPE_EMAIL);
                // sysMsg.setTag(ConfigUtil.SYS_MSG_TAG_GY);
                // sysMsgService.pushMsg(sysMsg);
            });
            return true;
        }catch (Exception e){
            log.error("雇员确认项目结算失败,任务编号:"+xminfid,e);
        }
        return false;
    }
}
