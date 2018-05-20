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
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.model.sys.Sys_role;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.tmsg.XmbillCheckTMsg;
import com.marveliu.framework.services.msg.tmsg.XmbillTMsg;
import com.marveliu.framework.services.msg.tmsg.XmfdbRelpyTMsg;
import com.marveliu.framework.services.sys.SysMsgService;
import com.marveliu.framework.services.sys.SysRoleService;
import com.marveliu.framework.services.sys.SysUserService;
import com.marveliu.framework.util.ConfigUtil;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmBillService;
import com.marveliu.framework.util.DateUtil;
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

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = XmBillService.class)
public class XmBillServiceImpl extends BaseServiceImpl<xm_bill> implements XmBillService {

    private final static Log log = Logs.getLog(XmBillService.class);

    @Inject
    @Reference
    private SysUserService sysUserService;


    @Inject
    @Reference
    private SysRoleService  sysRoleService;


    @Inject
    @Reference
    private SysMsgService sysMsgService;



    public XmBillServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 建立账单
     *
     * @param xminfid
     * @param award
     * @param uid
     * @return
     */
    @Override
    public xm_bill initXmbill(String xminfid,float award, String uid) {
        if(!Lang.isEmpty(this.fetch(Cnd.where("xminfid","=",xminfid)))){
            log.error("重复创建项目账单："+xminfid);
            return null;
        }
        try {
            // 账单信息
            xm_bill bill = new xm_bill();
            bill.setXminfid(xminfid);
            bill.setStatus(ConfigUtil.XM_BILL_INIT);
            bill.setOpBy(uid);
            bill.setAt(Times.getTS());
            return this.insert(bill);
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 更新项目支付方式
     *
     * @param xmbillid
     * @param payid
     * @param gyid
     * @return
     */
    @Override
    public boolean checkXmbillByGy(String xmbillid, String payid, String gyid) {
        try {
            xm_bill xmBill = this.fetchLinks(this.fetch(xmbillid),"");
            // 检查 账单存在，账单状态
            if(!Lang.isEmpty(xmBill) && xmBill.getStatus() == ConfigUtil.XM_BILL_CHECKING){
                Cnd cnd = Cnd.where("id","=",xmbillid);
                Chain chain = Chain.make("gypayid",payid).add("status",ConfigUtil.XM_BILL_PAYING).add("opAt",Times.getTS());
                if(this.update(chain,cnd)!=0){
                    Lang.runInAnThread(new Runnable() {
                        Sys_role sysRole = sysRoleService.fetchLinks(sysRoleService.getRoleFromCode("sys.fn"),"");
                        @Override
                        public void run() {
                            TMsg tMsg = new XmbillCheckTMsg(
                                    sysRole.getUsers().get(0).getUsername(),
                                    xmbillid,
                                    xmBill.getXminfid(),
                                    String.valueOf(xmBill.getPaysum()),
                                    xmBill.getGypay().getPayid(),
                                    xmBill.getGypay().getPayname(),
                                    xmBill.getGypay().getTypename()
                            );
                            Sys_msg sysMsg = new Sys_msg();
                            sysMsg.setRevid(sysRole.getUsers().get(0).getId());
                            sysMsg.setRevaccount(sysRole.getUsers().get(0).getEmail());
                            sysMsg.setMsg(Json.toJson(tMsg));
                            sysMsg.setType(ConfigUtil.SYS_MSG_TYPE_EMAIL);
                            sysMsg.setTag(ConfigUtil.SYS_MSG_TAG_XM);
                            sysMsg.setTmsgclass(tMsg.getTMsgClass());
                            sysMsgService.pushMsg(sysMsg);
                        }
                    });
                }
            }
        }catch (Exception e){
            log.error("雇员修改项目支付方式失败",e);
        }
        return false;
    }

    /**
     * 财务提交最终项目账单,支付完成
     *
     * @param xmbillid
     * @param sysuserinfid
     * @return
     */
    @Override
    public boolean commitXmbill(String xmbillid, String sysuserinfid) {
        try {
            xm_bill xmBill = this.fetchLinks(this.fetch(xmbillid),"");
            if(!Lang.isEmpty(xmBill) && xmBill.getStatus() == ConfigUtil.XM_BILL_PAYING){
                Cnd cnd = Cnd.where("id","=",xmbillid);
                Chain chain = Chain.make("realgypayid",xmBill.getGypayid()).add("status",ConfigUtil.XM_BILL_PAYED).add("payby",sysuserinfid).add("opAt",Times.getTS());
                if(this.update(chain,cnd)!=0){
                    // 财务结算给雇员
                    Thread t = new Thread(new Runnable(){
                        public void run(){
                            gy_inf gyInf =  dao().fetch(gy_inf.class, xmBill.getXmInf().getGyid());
                            TMsg tMsg = new XmbillTMsg(
                                    gyInf.getRealname(),
                                    xmbillid,
                                    xmBill.getXmInf().getId(),
                                    String.valueOf(xmBill.getPaysum()),
                                    xmBill.getGypay().getPayname(),
                                    xmBill.getGypay().getTypename()
                            );
                            Sys_msg sysMsg = new Sys_msg();
                            sysMsg.setRevid(gyInf.getUserid());
                            sysMsg.setRevaccount(gyInf.getEmail());
                            sysMsg.setMsg(Json.toJson(tMsg));
                            sysMsg.setType(ConfigUtil.SYS_MSG_TYPE_EMAIL);
                            sysMsg.setTag(ConfigUtil.SYS_MSG_TAG_XM);
                            sysMsg.setTmsgclass(tMsg.getTMsgClass());
                            sysMsgService.pushMsg(sysMsg);
                        }});
                    t.start();
                    return true;
                }
            }
        }catch (Exception e){
            log.error("财务支付失败",e);
        }
        return false;
    }


}