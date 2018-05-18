package com.marveliu.app.xm.modules.services.impl;

import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.services.xm.XmFeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(NbJUnit4Runner.class)
public class XmFeedbackServiceImplTest {

    private final static String xminfid ="xm_ts201805071";
    private final static String note ="雇员填写-流程测试";
    private final static String gyid ="gy18041";

    @Inject
    private XmFeedbackService xmFeedbackService;


    // 插入反馈测试
    @Test
    public void addXmfeedback() {
        xm_feedback xmFeedback = new xm_feedback();
        xmFeedback.setGyid(gyid);
        xmFeedback.setNote(note);
        xmFeedback.setXminfid(xminfid);
        assertNotNull(xmFeedbackService.addXmfeedback(xmFeedback));
    }


    @Test
    public void commitXmfeedback() {
        assertEquals(true, xmFeedbackService.commitXmfeedback(50));
    }
}