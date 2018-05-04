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
import com.marveliu.framework.model.sys.Sys_log;
import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.sys.SysLogService;
import com.marveliu.framework.services.xm.*;
import org.jboss.netty.util.internal.StringUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
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
     * 受理请求
     * @param xmapplyid
     * @param uid
     * @return
     */
    public xm_inf acceptXmapply(String xmapplyid,String uid){
        xm_inf xmInf = null;
        xm_bill xmBill = null;
        xmInf =  xmInfService.initXminf(xmApplyService.fetch(xmapplyid),uid);
        // 建立项目
        if(!Lang.isEmpty(xmInf)){
            xmBill =  xmBillService.initXmbill(xmInf,uid);
        }
        // 建立表单
        if(!Lang.isEmpty(xmBill)){
            // 项目建立日志
            Sys_log sysLog = new Sys_log();
            sysLog.setType("xm");
            sysLog.setTag("项目建立");
            sysLog.setSrc(this.getClass().getName() + "#acceptXmapply");
            sysLog.setMsg("任务："+xmapplyid+"->项目："+xmInf.getId());;
            sysLog.setOpBy(uid);
            sysLog.setOpAt(Times.getTS());
            sysLog.setUsername(uid);
            sysLogService.insert(sysLog);
            // todo：邮件类信息通知
            return xmInf;
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
