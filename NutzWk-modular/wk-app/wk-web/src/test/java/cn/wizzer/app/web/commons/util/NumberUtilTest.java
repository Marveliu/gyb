package cn.wizzer.app.web.commons.util;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.web.commons.core.Module;
import net.sf.ehcache.CacheManager;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.IocLoader;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.mvc.annotation.IocBy;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */
public class NumberUtilTest extends TestBase {


    @Test
    public void gyAuthIdGeneraotr() throws Exception {
    }


    @Test
    public void gyidGeneraotr() throws Exception {
        String id = ioc.get(NumberUtil.class).GyIdGeneraotr(10,"11490",0,"0");
        log.debug("生成id:"+id);
    }

}