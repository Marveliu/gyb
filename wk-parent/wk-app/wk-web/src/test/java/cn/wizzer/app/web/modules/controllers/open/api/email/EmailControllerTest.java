package cn.wizzer.app.web.modules.controllers.open.api.email;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.web.modules.controllers.open.email.EmailController;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/4 0004.
 */
public class EmailControllerTest extends TestBase {


    // 全都无法验证，因为你的junti无法获得gyb项目的session
    @Test
    public void activeMail() throws Exception {

        ioc.get(EmailController.class).activeMail("5d16cfb5109446ac86d19e163df6e4bd");
    }

    @Test
    public void activeMailCallback() throws Exception {
        ioc.get(EmailController.class).activeMailCallback("40c0a1f62915fe52c98f37034220498ad4d3786336d618b7dc563af3f4dee66f","5d16cfb5109446ac86d19e163df6e4bd");
    }

}