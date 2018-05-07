package com.marveliu.app.xm.modules.services.impl;

import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.services.xm.*;
import com.marveliu.framework.util.statusUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import static org.junit.Assert.*;

@IocBean(create = "init")
@RunWith(NbJUnit4Runner.class)
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

    private final static  String XMTASK_ID = "rw_test0";
    private final static  String XMINF_ID = "xm_test0";
    private final static  String GY_ID = "gy18041";             //liushangnan
    private final static  String UID = "405a28c9389d4a8581a29c283dc9f5b9";             //liushangnan
    private volatile static int errorcount = 0;

    private static String xmapplyid = new String();

    // 初始化一个任务书
    public void init(){

    }

    @Test
    public void isGyForXm() {
        assertEquals(true,xmFacadeService.isGyForXm(XMINF_ID,GY_ID));
        assertEquals(false,xmFacadeService.isGyForXm(XMINF_ID,"gy18044"));
    }


    // 初始化项目
    @Test
    public void acceptXmapply() {
        // init
        // 清空之间的任务信息
        xmApplyService.clear(Cnd.where("xmtaskid","=",XMTASK_ID));
        xmInfService.clear(Cnd.where("xmtaskid","=",XMTASK_ID));
        xmBillService.clear(Cnd.where("xminfid","=",XMINF_ID));
        xmApplyService.addXmApply(XMTASK_ID,GY_ID,false);
        xmapplyid = xmApplyService.fetch(Cnd.where("xmtaskid","=",XMTASK_ID).and("gyid","=",GY_ID)).getId();
        log.info("create xmapply:"+xmapplyid);
        xm_inf xmInf = xmFacadeService.acceptXmapply(xmapplyid,UID);
        assertEquals(GY_ID,xmInf.getGyid());
    }

    // 项目完结
    @Test
    public void initXmFinal() {
        xm_feedback xmFeedback = new xm_feedback();
        xmFeedback.setGyid(GY_ID);
        xmFeedback.setNote("test gy reply");
        xmFeedback.setXminfid(XMINF_ID);
        xmFeedback = xmFeedbackService.addXmfeedback(xmFeedback);
        assertEquals(statusUtil.XM_FEEDBACK_INIT, xmFeedback.getStatus());
        assertEquals(true, xmFeedbackService.commitXmfeedback(xmFeedback.getId()));
        xmFeedback.setReply("done well");
        xmFeedback.setNextcommit((int) Times.getTS());
        assertEquals(true,xmFeedbackService.checkXmfeedback(xmFeedback));
        assertEquals(true,xmFeedbackService.confirmXmfeedback(xmFeedback.getId(),true));

        // xmApplyService.setXmApplyStatus();
        assertEquals(true,xmFacadeService.initXmFinal(XMINF_ID,100,10000,"nice eva","nice bill",UID));

    }
}