package cn.wizzer.app.web.modules.controllers.open.api.email;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.web.modules.controllers.open.email.EmailController;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/4 0004.
 */
public class EmailControllerTest extends TestBase {

    @Test
    public void activeMail() throws Exception {
        ioc.get(EmailController.class).activeMail("6bb32317af9343a484390698ec18778f");
    }

    @Test
    public void activeMailCallback() throws Exception {
        ioc.get(EmailController.class).activeMailCallback("df552ea5092edffe59b276d1760f08275a362b9d10abd6510ad661ddc8039372","6bb32317af9343a484390698ec18778f");
    }

}