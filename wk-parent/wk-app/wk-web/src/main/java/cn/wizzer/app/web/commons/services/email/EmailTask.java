package cn.wizzer.app.web.commons.services.email;

import org.nutz.ioc.loader.annotation.Inject;

/**
 * 邮件发送任务
 *
 * @Author Marveliu
 * @Create 2018/1/8 0008.
 */

public abstract class EmailTask implements Runnable {


    protected EmailService emailService;
    protected String to ="";
    protected String subject = "";

    public EmailTask(String to, String subject,EmailService emailService) {
        this.to = to;
        this.subject = subject;
        this.emailService = emailService;
    }

    @Override
    public void run(){
        emailService.send(this.to,this.subject,"雇佣帮测试邮件");
    }
}
