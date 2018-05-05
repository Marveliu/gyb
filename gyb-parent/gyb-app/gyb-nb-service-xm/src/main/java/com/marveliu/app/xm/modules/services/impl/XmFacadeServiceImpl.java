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
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.xm.xm_evaluation;
import com.marveliu.framework.services.gy.GyPayService;
import com.marveliu.framework.util.statusUtil;
import com.marveliu.framework.model.sys.Sys_log;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.sys.SysLogService;
import com.marveliu.framework.services.xm.*;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
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

    @Inject
    @Reference
    private GyPayService gyPayService;


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

        // 更新任务书申请状态为完结，同时可以检查任务书是否存在
        if (xmApplyService.update(
                Chain.make("status", statusUtil.XM_APPLY_FINAL),
                Cnd.where("id", "=", xmapplyid)) != 0) {
            xm_inf xmInf = null;
            xm_bill xmBill = null;
            try {
                xm_task xmTask = xmApplyService.getXmTaskByAppyid(xmapplyid);
                // 更新任务书状态
                xmTask.setStatus(statusUtil.XM_TASK_DOING);
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
            String xmEvaluationNote,
            String xmBillNote,
            String uid) {
        try{
            xm_evaluation xmEvaluation = new xm_evaluation();
            xmEvaluation.setXminfid(xminfid);
            xmEvaluation.setGrade(xmEvaluationGrade);
            xmEvaluation.setNote(xmEvaluationNote);
            xm_inf xmInf = xmInfService.fetch(xminfid);
            gy_pay pay = gyPayService.getFirstPay(xmInf.getGyid());
            Chain chain =  Chain.make("status",statusUtil.XM_BILL_CHECKING).add("note",xmBillNote).add("opAt",Times.getTS());

            Trans.exec(new Atom() {
                @Override
                public void run() {
                    xmEvaluationService.insert(xmEvaluation);
                    if (!Lang.isEmpty(pay)) {
                        chain.add("gypayid",pay.getId());
                    }
                    // 任务书更新为: 完结
                    xmTaskService.update(Chain.make("status",statusUtil.XM_TASK_FINISH),Cnd.where("id","=",xmInf.getXmtaskid()));
                    // 项目账单更新为: 雇员审查
                    xmBillService.update(chain,Cnd.where("xminfid","=",xminfid));
                    // 项目信息更新为: 雇员审查
                    xmInfService.update(Chain.make("status",statusUtil.XM_INF_CHECKING),Cnd.where("id","=",xminfid));
                }
            });
            return true;
        }catch (Exception e){
            log.error("项目结算出错:"+xminfid,e);
        }
        return false;
    }


}
