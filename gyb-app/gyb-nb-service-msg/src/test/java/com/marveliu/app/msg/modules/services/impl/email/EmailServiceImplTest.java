package com.marveliu.app.msg.modules.services.impl.email;

import com.marveliu.app.msg.commons.core.DubboRpcMsgMainLauncher;
import com.marveliu.framework.services.msg.tmsg.PasswordTMsg;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.email.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import static org.junit.Assert.*;


@IocBean
@RunWith(NbJUnit4Runner.class)
public class EmailServiceImplTest {

    private final static Log log = Logs.get();

    private final static String TO = "897920245@qq.com";
    private final static String SUBJECT = "雇佣帮TEST";
    private final static String TEMPLATE_NAME = "password";
    private final static String CONTENT = "雇佣帮用户你好，欢迎加入哦";


    @Inject
    private EmailService emailService;

    @Test
    public void send() throws Exception {
        assertEquals(true,emailService.send(TO,SUBJECT,CONTENT));
    }


    @Test
    public void sendTemplate() throws Exception {
        TMsg msg = new PasswordTMsg("marveliu","www.baidu.com");
        assertEquals(true,emailService.sendHtmlTemplateByTemplateName(TO,msg));
    }

    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(DubboRpcMsgMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("com.marveliu");
        return nb;
    }




}