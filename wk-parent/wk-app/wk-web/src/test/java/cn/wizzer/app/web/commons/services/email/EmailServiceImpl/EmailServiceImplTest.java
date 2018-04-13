package cn.wizzer.app.web.commons.services.email.EmailServiceImpl;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.web.modules.controllers.open.email.EmailController;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailServiceImplTest extends TestBase {

    @Test
    public void send() {
    }

    @Test
    public void sendHtmlTemplate() {
        ioc.get(EmailServiceImpl.class).sendHtmlTemplateByTemplateName("897920245@qq.com","测试","register");
    }
}