package cn.wizzer.app.web.commons.services.gy;

import cn.wizzer.app.TestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/5 0005.
 */
public class GyServiceTest extends TestBase{
    @Test
    public void checkGyRegByUsrid() throws Exception {
        ioc.get(GyService.class).checkGyRegByUsrid("171001");
    }

    @Test
    public void updateGyRole() throws Exception {
        assertTrue(ioc.get(GyService.class).updateGyRole("5d16cfb5109446ac86d19e163df6e4bd","gy1") == true);
    }

}