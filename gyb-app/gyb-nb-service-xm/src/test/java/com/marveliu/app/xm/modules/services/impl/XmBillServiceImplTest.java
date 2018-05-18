package com.marveliu.app.xm.modules.services.impl;

import com.marveliu.framework.services.xm.XmApplyService;
import com.marveliu.framework.services.xm.XmBillService;
import com.marveliu.framework.services.xm.XmFacadeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import static org.junit.Assert.*;


@IocBean
@RunWith(NbJUnit4Runner.class)
public class XmBillServiceImplTest {


    private static final Log log = Logs.get();

    private final static  String XMTASK_ID = "rw_test0";
    private final static  String XMBILL_ID = "bill_test0";
    private final static  String XMINF_ID = "xm_test0";
    private final static  String GY_ID = "gy18041";             //liushangnan
    private final static  String PAY_ID = "test_pay0";             //liushangnan
    private final static  String UID = "405a28c9389d4a8581a29c283dc9f5b9";             //liushangnan
    private final static  String GYBID = "gyb201800";             //liushangnan

    @Inject
    private XmBillService xmBillService;

    @Test
    public void test() {
        assertEquals(true,xmBillService.checkXmbillByGy(XMBILL_ID,PAY_ID,GY_ID));
        assertEquals(true,xmBillService.commitXmbill(XMBILL_ID,GYBID));
    }

}