package cn.wizzer.app.web.commons.util;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.sys.modules.models.Sys_user;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/5 0005.
 */
public class UserInfUtilTest extends TestBase {


    @Test
    public void getCurrentUser() throws Exception {
        // shiro中查询但是没有
        Sys_user user = ioc.get(UserInfUtil.class).getCurrentUser();
        return;
    }

}