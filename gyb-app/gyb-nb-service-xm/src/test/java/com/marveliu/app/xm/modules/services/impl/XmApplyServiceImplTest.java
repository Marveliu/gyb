package com.marveliu.app.xm.modules.services.impl;

import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.services.xm.XmApplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@IocBean(create = "init", depose = "close")
@RunWith(NbJUnit4Runner.class)
public class XmApplyServiceImplTest {

    @Inject
    private XmApplyService xmApplyService;

    private final static String XMTASK_ID = "rw_test0";
    private final static String GY_ID = "gy18041";             //liushangnan
    private final static String UID = "405a28c9389d4a8581a29c283dc9f5b9";             //liushangnan


    private volatile static int errorcount = 0;


    public void init() {
        xmApplyService.clear(Cnd.where("xmtaskid", "=", XMTASK_ID));
        xmApplyService.addXmApply(XMTASK_ID, GY_ID, false);
    }

    public void close() {
        System.out.println("closing......");
        xmApplyService.clear(Cnd.where("xmtaskid", "=", XMTASK_ID));
    }


    // @Test
    // public void generateId() {
    //     assertEquals("apply_test01",xmApplyService.generateId(XMTASK_ID));
    // }

    @Test
    public void addXmApply() throws Exception {
        int threadCount = 10;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        // 多线程测试插入数据
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int n = 0; n < 1000; n++) {
                    if (!xmApplyService.addXmApply(XMTASK_ID, GY_ID, true)) {
                        // todo:插入失败没有返回失败
                        errorcount++;
                    }
                }
                latch.countDown();
            }
        };
        for (int n = 0; n < threadCount; n++) {
            Thread thread = new Thread(task);
            thread.start();
        }
        latch.await();
        System.out.println("*********" + errorcount);
    }


    @Test
    public void setXmApplyStatus() {
        List<xm_apply> xmApplies = xmApplyService.getXmApplyListByGyid(GY_ID);
        for (xm_apply xmApply : xmApplies) {
            xmApplyService.setXmApplyStatus(xmApply.getId(), true, UID);
            assertEquals(1, xmApplyService.fetch(xmApply.getId()).getStatus());
        }
        for (xm_apply xmApply : xmApplies) {
            xmApplyService.setXmApplyStatus(xmApply.getId(), false, UID);
            assertEquals(2, xmApplyService.fetch(xmApply.getId()).getStatus());
        }
    }

    @Test
    public void getXmTaskByAppyid() {
        assertEquals("瑞华集团官网后台及程序开发", xmApplyService.getXmTaskByAppyid("applykf022018020651").getTaskname());
    }
}