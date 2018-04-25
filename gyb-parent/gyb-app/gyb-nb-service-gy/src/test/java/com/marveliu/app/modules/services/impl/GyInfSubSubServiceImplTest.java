package com.marveliu.app.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.gy.commons.core.DubboRpcGyMainLauncher;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.utils.IdGeneratorUtil;
import com.marveliu.framework.services.gy.GyInfSubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;

@IocBean
@RunWith(NbJUnit4Runner.class)
public class GyInfSubSubServiceImplTest {


    @Inject
    private GyInfSubService gyInfSubService;
    @Inject
    private IdGeneratorUtil idGeneratorUtil;

    @Test
    public void insert() {
        gy_inf gy_inf = new gy_inf();
        gy_inf.setUserid("405a28c9389d4a8581a29c283dc9f5b9");
        assertNotNull(gyInfSubService.insert(gy_inf));
    }

    @Test
    public void getGyByUserId() {
    }

    @Test
    public void getUserByGyid() {
    }

    @Test
    public void setQq() {
    }

    // 测试类可提供public的static的createNbApp方法,用于定制当前测试类所需要的NbApp对象.
    // 测试类带@IocBean或不带@IocBean,本规则一样生效
    // 若不提供,默认使用当前测试类作为MainLauncher.
    // 也可以自定义NbJUnit4Runner, 继承NbJUnit4Runner并覆盖其createNbApp方法
    // public static NbApp createNbApp() {
    //     NbApp nb = new NbApp().setMainClass(DubboRpcGyMainLauncher.class).setPrintProcDoc(false);
    //     nb.getAppContext().setMainPackage("com.marveliu");
    //     return nb;
    // }
}