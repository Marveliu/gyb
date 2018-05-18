package com.marveliu.app.msg.commons.utils;

import com.marveliu.app.msg.commons.core.DubboRpcMsgMainLauncher;
import com.marveliu.framework.services.msg.tmsg.PasswordTMsg;
import com.marveliu.framework.services.msg.TMsg;
import org.beetl.core.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.IocBean;


@IocBean
@RunWith(NbJUnit4Runner.class)
public class TemplateUtilTest {

    TemplateUtil templateUtil = new TemplateUtil();

    @Test
    public void addDate() {
        TMsg tMsg = new PasswordTMsg("刘尚nan","www.baidu.com");
        Template t =  templateUtil.buildTemplate(tMsg);
        System.out.println("html:"+t.render());
    }

    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(DubboRpcMsgMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("com.marveliu");
        return nb;
    }
}