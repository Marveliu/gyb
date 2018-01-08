package cn.wizzer.app.web.commons.services.email.EmailServiceImpl;

import cn.wizzer.app.web.commons.services.email.EmailService;
import org.apache.commons.mail.HtmlEmail;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Created by Wizzer on 2016/7/31.
 */
@IocBean
public class EmailBaseServiceImpl {

    private String to = "";
    private String subject = "";
    private String html = "";


    public EmailBaseServiceImpl() {
    }

    // 多种构造器
    public EmailBaseServiceImpl(String to,String subject,String html) {
        this.html = html;
        this.subject = subject;
        this.to = to;
    }
}
