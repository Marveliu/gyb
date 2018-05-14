package com.marveliu.app.msg.commons.utils;

import com.marveliu.app.msg.commons.core.DubboRpcMsgMainLauncher;
import com.marveliu.app.msg.commons.tmsg.passwordTMsg;
import com.marveliu.framework.services.msg.TMsg;
import org.beetl.core.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.IocBean;

import static org.junit.Assert.*;


@IocBean
@RunWith(NbJUnit4Runner.class)
public class TemplateUtilTest {

    TemplateUtil templateUtil = new TemplateUtil();

    @Test
    public void addDate() {
        TMsg tMsg = new passwordTMsg("刘尚nan","www.baidu.com");
        Template t =  templateUtil.buildTemplate(tMsg);
        System.out.println("html:"+t.render());
    }

    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(DubboRpcMsgMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("com.marveliu");
        return nb;
    }
}