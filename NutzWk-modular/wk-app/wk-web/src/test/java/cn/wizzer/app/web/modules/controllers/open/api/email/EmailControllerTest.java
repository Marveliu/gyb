package cn.wizzer.app.web.modules.controllers.open.api.email;

import cn.wizzer.app.TestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/4 0004.
 */
public class EmailControllerTest extends TestBase {


    @Test
    public void activeMail() throws Exception {

        ioc.get(EmailController.class).activeMail("28e8a294dd73436583f6d5ab0c8bceaf");
    }

    @Test
    public void activeMailCallback() throws Exception {
        ioc.get(EmailController.class).activeMailCallback("5a7af6059df07a5f4981f19df9a6fc94acfd6b69fba5af760fd07669a5c08693","28e8a294dd73436583f6d5ab0c8bceaf");
    }

}