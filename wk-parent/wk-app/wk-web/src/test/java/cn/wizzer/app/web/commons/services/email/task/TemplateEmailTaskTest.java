package cn.wizzer.app.web.commons.services.email.task;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.web.commons.services.email.EmailServiceImpl.EmailServiceImpl;
import cn.wizzer.app.web.commons.services.email.EmailTask;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.WebAppResourceLoader;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemplateEmailTaskTest extends TestBase {

    @Test
    public void run() {
        try {
            WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/template/email/register.html");
            t.binding("username","marveliu");
            t.binding("url","www.guyongbang.cn");
            t.binding("email","897920245@qq.com");
            // 直接传递函数
            EmailTask task = new TemplateEmailTask("897920245@qq.com","雇佣帮邮箱激活邮件",ioc.get(EmailServiceImpl.class),t);
            task.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}