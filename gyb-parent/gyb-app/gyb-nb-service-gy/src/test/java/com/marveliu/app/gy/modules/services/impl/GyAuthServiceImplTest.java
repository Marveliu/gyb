package com.marveliu.app.gy.modules.services.impl;

import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.services.gy.GyAuthSubService;
import com.marveliu.framework.services.gy.GyInfSubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;


@IocBean
@RunWith(NbJUnit4Runner.class)
public class GyAuthSubServiceImplTest {

    @Inject
    private GyAuthSubService gyAuthSubService;

    // 测试主键
    @Test
    public void test() {
        gy_auth gy_auth = new gy_auth();
        gy_auth.setGyid("gyTest");
        gy_auth result = gyAuthSubService.insert(gy_auth);
        assertNotNull(result);
        assertEquals("AugyTest",result.getId());
        gyAuthSubService.setStatus(result.getGyid(),true,"测试认证成功");
        assertEquals(true,gyAuthSubService.isAuth(result.getGyid()));
        assertEquals("测试认证成功",gyAuthSubService.fetch(
                Cnd.where("gyid","=","gyTest")).getNote());
        gyAuthSubService.delete(result.getId());

    }

}