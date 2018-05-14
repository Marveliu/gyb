package com.marveliu.app.gy.modules.services.impl;

import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.services.gy.GyAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;


@IocBean
@RunWith(NbJUnit4Runner.class)
public class GyAuthServiceImplTest {

    @Inject
    private GyAuthService gyAuthService;

    // 测试主键
    @Test
    public void test() {
        gy_auth gy_auth = new gy_auth();
        gy_auth.setGyid("gyTest");
        gy_auth result = gyAuthService.insert(gy_auth);
        assertNotNull(result);
        assertEquals("AugyTest",result.getId());
        gyAuthService.setStatus(result.getGyid(),true,"测试认证成功");
        assertEquals(true, gyAuthService.isAuth(result.getGyid()));
        assertEquals("测试认证成功", gyAuthService.fetch(
                Cnd.where("gyid","=","gyTest")).getNote());
        gyAuthService.delete(result.getId());
    }

    // public static NbApp createNbApp() {
    //     NbApp nb = new NbApp().setMainClass(TaskMainLauncher.class).setPrintProcDoc(false);
    //     nb.getAppContext().setMainPackage("com.marveliu");
    //     return nb;
    // }

}