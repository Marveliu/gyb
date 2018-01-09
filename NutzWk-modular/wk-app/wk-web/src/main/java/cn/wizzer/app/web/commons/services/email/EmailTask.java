package cn.wizzer.app.web.commons.services.email;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * runable
 *
 * @Author Marveliu
 * @Create 2018/1/8 0008.
 */

@IocBean
public class EmailTask implements Runnable {

    private EmailService emailService;

    private String to ="";
    private String subject = "";
    private String html  = "";

    public EmailTask(String to, String subject, String html,EmailService emailService) {
        this.to = to;
        this.subject = subject;
        this.html = html;
    }

    @Override
    public void run(){
        emailService.send(this.to,this.subject,this.html);
    }
}
