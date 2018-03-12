package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.gy.modules.models.v_gy;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/9 0009.
 */
public class VGyServiceImplTest extends TestBase{

    @Test
    public void VGytest() throws Exception {
        List<v_gy> list =  ioc.get(VGyServiceImpl.class).query();
        list.toArray();

    }
}