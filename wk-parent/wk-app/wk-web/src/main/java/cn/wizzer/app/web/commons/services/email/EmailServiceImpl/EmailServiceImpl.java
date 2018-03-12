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
public class EmailServiceImpl implements EmailService {
    private static final Log log = Logs.get();

    // private String to;
    // private String subject;
    // private String html;

    @Inject("refer:$ioc")
    protected Ioc ioc;

    // public EmailServiceImpl(String to, String subject, String html) {
    //     this.to = to;
    //     this.subject = subject;
    //     this.html = html;
    // }

    // public boolean send() {
    //     try {
    //         // HtmlEmail email = Mvcs.getIoc().get(HtmlEmail.class);
    //         HtmlEmail email = ioc.get(HtmlEmail.class);
    //         // HtmlEmail email = new HtmlEmail();
    //         email.setCharset("UTF-8");
    //         email.setSubject(this.subject);
    //         email.setHtmlMsg(this.html);
    //         email.addTo(this.to);
    //         email.buildMimeMessage();
    //         email.sendMimeMessage();
    //         return true;
    //     } catch (Throwable e) {
    //         log.info("send email fail", e);
    //         return false;
    //     }
    // }

    public boolean send(String to, String subject, String html) {
        try {
            HtmlEmail email = ioc.get(HtmlEmail.class);
            email.setSubject(subject);
            email.setCharset("UTF-8");
            email.setHtmlMsg(html);
            email.addTo(to);
            email.buildMimeMessage();
            email.sendMimeMessage();
            return true;
        } catch (Throwable e) {
            log.info("send email fail", e);
            return false;
        }
    }

}
