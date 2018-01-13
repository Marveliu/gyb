package cn.wizzer.app.web.commons.services.xm;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.xm.modules.models.*;
import cn.wizzer.app.xm.modules.services.*;
import cn.wizzer.framework.util.StringUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

/**
 * 面向功能封装项目模块的服务
 *
 * @Author Marveliu
 * @Create 2018/1/12 0012.
 */

@IocBean
public class XmService {
    private static final org.nutz.log.Log log = Logs.get();

    @Inject
    private Dao dao;
    @Inject
    private XmApplyService xmApplyService;
    @Inject
    private XmTaskService xmTaskService;
    @Inject
    private XmInfService xmInfService;
    @Inject
    private XmBillService xmBillService;
    @Inject
    private XmEvaluationService xmEvaluationService;

    /**
     * 任务书立项
     * @param taskid
     * @param gyid
     * @return
     */
    public xm_inf regXminf(String taskid,String gyid){

        String sysuserid = StringUtil.getSysuserid();
        xm_task task = xmTaskService.fetch(taskid);
        int opAt = (int) (System.currentTimeMillis() / 1000);

        try{
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    // 拒绝所有的请求
                    dao.execute(Sqls.create("update xm_apply set status = @status,opBy = @opBy,opAt = @opAt where xmtaskid = @xmtaskid")
                            .setParam("status",2)
                            .setParam("opBy",sysuserid)
                            .setParam("opAt",opAt)
                            .setParam("xmtaskid",taskid)
                    );
                    // 受理用户
                    dao.update(xm_apply.class,Chain.make("status",1),Cnd.where("xmtaskid","=",taskid).and("gyid","=",gyid));
                    dao.update(xm_task.class,Chain.make("status",2),Cnd.where("xmtaskid","=",taskid).and("gyid","=",gyid));
            // 项目
            xm_inf xf = new  xm_inf();
            xf.setGyid(gyid);
            xf.setXmtaskid(task.getId());
            xf.setAt(opAt);
            xf.setOpBy(sysuserid);
            xf.setStatus(0);
            xf = xmInfService.insert(xf);

            // 账单
            xm_bill bill = new xm_bill();
            bill.setXminfid(xf.getId());
            bill.setOpBy(sysuserid);
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
     * @param id
     * @return
     */
    public boolean initXmFinal(String id){
        String sysuserid = StringUtil.getSysuserid();
        int at =  (int) (System.currentTimeMillis() / 1000);

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

    /**
     * 检查雇员是否为项目的拥有者
     * @param xminfid
     * @param gyid
     * @return
     */
    public boolean checkGyForXm(String xminfid,String gyid){
        if(gyid.equals(xmInfService.fetch(xminfid).getGyid())){
            return true;
        }else {
            return false;
        }
    }


}
