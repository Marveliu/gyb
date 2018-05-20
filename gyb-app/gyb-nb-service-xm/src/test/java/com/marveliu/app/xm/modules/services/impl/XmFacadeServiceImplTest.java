package com.marveliu.app.xm.modules.services.impl;

import com.marveliu.app.xm.commons.core.DubboRpcXmMainLauncher;
import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.xm.*;
import com.marveliu.framework.util.ConfigUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.nutz.boot.AppContext;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import static org.junit.Assert.*;

@IocBean(create = "init")
@RunWith(NbJUnit4Runner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class XmFacadeServiceImplTest {

    private static final Log log = Logs.get();

    @Inject
    private XmFacadeService xmFacadeService;

    @Inject
    private XmApplyService xmApplyService;

    @Inject
    private XmBillService xmBillService;

    @Inject
    private XmInfService xmInfService;

    @Inject
    private XmFeedbackService xmFeedbackService;

    private final static String XMTASK_ID = "rws_test0";
    private final static String XMINF_ID = "rw_test0";
    private final static String GY_ID = "gy18011";                                    //liushangnan
    private final static String UID = "405a28c9389d4a8581a29c283dc9f5b9";             //liushangnan
    private final static String NOTE = "雇员填写-流程测试";

    private volatile static int errorcount = 0;

    private static String xmapplyid = new String();


    // 初始化一个任务书
    public void init() {
        // 情况之前的测试任务
        Ioc ioc = AppContext.getDefault().getIoc();
        Dao dao = ioc.get(Dao.class);
        // 重置任务书为申请阶段
        dao.execute(Sqls.create("update xm_task set status = 2 where id = @xmtaskid").setParam("xmtaskid",XMTASK_ID));
        dao.execute(Sqls.create("delete from xm_apply where xmtaskid = @xmtaskid").setParam("xmtaskid",XMTASK_ID));
        dao.execute(Sqls.create("delete from xm_limit where xmtaskid = @xmtaskid").setParam("xmtaskid",XMTASK_ID));
        dao.execute(Sqls.create("delete from xm_inf where id = @xminfid").setParam("xminfid",XMINF_ID));
        dao.execute(Sqls.create("delete from xm_bill where xminfid = @xminfid").setParam("xminfid",XMINF_ID));
        dao.execute(Sqls.create("delete from xm_feedback where xminfid = @xminfid").setParam("xminfid",XMINF_ID));
        dao.execute(Sqls.create("delete from xm_evaluation where xminfid = @xminfid").setParam("xminfid",XMINF_ID));
    }

    @Test
    public void test(){
        acceptXmapply();
        feedback();
        initXmFinal();
    }

    // 受理项目申请
    public void acceptXmapply() {
        xmApplyService.addXmApply(XMTASK_ID, GY_ID, false);
        xmapplyid = xmApplyService.fetch(Cnd.where("xmtaskid", "=", XMTASK_ID).and("gyid", "=", GY_ID)).getId();
        log.info("create xmapply:" + xmapplyid);
        xm_inf xmInf = xmFacadeService.acceptXmapply(xmapplyid, UID);
        assertEquals(GY_ID, xmInf.getGyid());
    }


    public void feedback() {

        Ioc ioc = AppContext.getDefault().getIoc();
        Dao dao = ioc.get(Dao.class);
        dao.execute(Sqls.create("delete from xm_feedback where xminfid = @xminfid").setParam("xminfid",XMINF_ID));

        // 第一次反馈
        xm_feedback xmFeedback = new xm_feedback();
        xmFeedback.setGyid(GY_ID);
        xmFeedback.setNote(NOTE);
        xmFeedback.setXminfid(XMINF_ID);
        xmFeedback = xmFeedbackService.addXmfeedback(xmFeedback);
        assertNotNull(xmFeedback);
        assertNull(xmFeedbackService.addXmfeedback(xmFeedback));
        assertEquals(true,xmFeedbackService.commitXmfeedback(xmFeedback.getId()));
        assertEquals(true,xmFeedbackService.checkXmfeedback(xmFeedback.getId(), ((int) (Times.getTS())),"测试反馈",UID));
        assertEquals(true,xmFeedbackService.confirmXmfeedback(xmFeedback.getId(),false));

        // 第二次反馈
        xm_feedback xmFeedback2 = new xm_feedback();
        xmFeedback2.setGyid(GY_ID);
        xmFeedback2.setNote(NOTE);
        xmFeedback2.setXminfid(XMINF_ID);
        xmFeedback2 = xmFeedbackService.addXmfeedback(xmFeedback);
        assertNotNull(xmFeedback2);
        assertEquals(true,xmFeedbackService.commitXmfeedback(xmFeedback2.getId()));
        assertEquals(true,xmFeedbackService.checkXmfeedback(xmFeedback2.getId(), ((int) (Times.getTS())),"测试反馈",UID));
        assertEquals(true,xmFeedbackService.confirmXmfeedback(xmFeedback2.getId(),true));


    }

    // 项目完结
    public void initXmFinal() {
        assertEquals(true, xmFacadeService.initXmFinal(XMINF_ID, 100, 10000, "nice eva", "nice bill", UID));
    }

    // 项目完

    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(DubboRpcXmMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("com.marveliu");
        return nb;
    }


}