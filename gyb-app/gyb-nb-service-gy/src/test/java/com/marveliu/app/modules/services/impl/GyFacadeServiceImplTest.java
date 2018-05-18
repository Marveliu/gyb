package com.marveliu.app.modules.services.impl;

import com.marveliu.framework.services.gy.GyFacadeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import static org.junit.Assert.*;


@IocBean(create = "init")
@RunWith(NbJUnit4Runner.class)
public class GyFacadeServiceImplTest {


    @Inject
    private GyFacadeService gyFacadeService;



    @Test
    public void updateGyRole() {
    }

    @Test
    public void sendMsgByGyid() {
    }

    @Test
    public void changeEmail() {
    }

    @Test
    public void getGyByPayid() {
    }

    @Test
    public void getPaysByGyid() {
    }

    @Test
    public void ifEmailChecked() {
    }

    @Test
    public void setQq() {
    }
}