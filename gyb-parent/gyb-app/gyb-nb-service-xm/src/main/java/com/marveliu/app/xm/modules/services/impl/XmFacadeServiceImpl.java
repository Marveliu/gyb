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
import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.xm.*;
import org.jboss.netty.util.internal.StringUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
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


    /**
     * 检查雇员是否为项目的拥有者
     * @param xminfid
     * @param gyid
     * @return
     */
    public boolean isGyForXm(String xminfid,String gyid){
        if(gyid.equals(xmInfService.fetch(xminfid).getGyid())){
            return true;
        }else {
            return false;
        }
    }

    public xm_task getTaskByAppyid(String xmapplyid){
        String xmtaskid = xmApplyService.fetch(xmapplyid).getXmtaskid();
        if(Lang.isEmpty(xmtaskid)) return null;
        return xmTaskService.fetch(xmtaskid);
    }


    /**
     * 立项
     * @param taskid
     * @param gyid
     * @param uid
     * @return
     */
    public xm_inf initXminf(String taskid, String gyid,String uid){

        xm_task task = xmTaskService.fetch(taskid);
        long opAt = (int) (System.currentTimeMillis() / 1000);

        try{
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    // 之前所有的申请拒绝
                    dao.execute(Sqls.create("update xm_apply set status = @status,opBy = @opBy,opAt = @opAt where xmtaskid = @xmtaskid")
                            .setParam("status",2)
                            .setParam("opBy",uid)
                            .setParam("opAt",opAt)
                            .setParam("xmtaskid",taskid)
                    );
                    // 受理用户
                    dao.update(xm_apply.class,Chain.make("status",1),Cnd.where("xmtaskid","=",taskid).and("gyid","=",gyid));
                    //进入任务跟踪
                    dao.update(xm_task.class,Chain.make("status",3),Cnd.where("id","=",taskid));

                    // 项目
                    xm_inf xf = new  xm_inf();
                    xf.setGyid(gyid);
                    xf.setXmtaskid(task.getId());
                    xf.setAt(opAt);
                    xf.setOpBy(uid);
                    xf.setStatus(0);
                    xf = xmInfService.insert(xf);

                    // 账单
                    xm_bill bill = new xm_bill();
                    bill.setXminfid(xf.getId());
                    bill.setOpBy(uid);
                    bill.setOpAt(opAt);
                    bill.setPaysum(task.getAward());
                    xmBillService.insert(bill);
                }
            });

            // TODO: 2018/1/12 0012 项目日志与通知
        }catch (Exception e){
            log.debug("项目建立出错："+e);
        }
        return null;
    }


    /**
     * 项目结算
     * @param xminfid
     * @param uid
     * @return
     */
    public boolean initXmFinal(String xminfid,String uid){
        Long at =  (Long) (System.currentTimeMillis() / 1000);

        // xm_inf xmInf = xmInfService.fetch(id);
        //
        //
        // Trans.exec(new Atom() {
        //     @Override
        //     public void run() {
        //         //评价
        //         xm_evaluation eva = new xm_evaluation();
        //         eva.setOpBy(sysuserid);
        //         eva.setOpAt(at);
        //         eva.setXminfid(id);
        //         eva.setGrade(xmInf.);
        //         eva.setNote(evanote);
        //         xmEvaluationService.insert(eva);
        //
        //         //账单
        //         gy_pay pay = gyPayService.getFirstPay(xmf.getGyid());
        //         xmBillService.update(org.nutz.dao.Chain.make("status",1).add("gypayid",pay.getId()).add("note",billnote).add("at",at),Cnd.where("xminfid","=",id));
        //
        //         //项目状态
        //         xmInfService.update(org.nutz.dao.Chain.make("status",2),Cnd.where("id","=",id));
        //     }
        // });

        return false;
    }



}
