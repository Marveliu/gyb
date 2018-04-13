package cn.wizzer.app.web.commons.services.email;

import org.beetl.core.Template;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by Wizzer on 2016/7/31.
 */
public interface EmailService {
    public boolean send(String to, String subject, String html) ;


    public boolean sendHtmlTemplateByTemplateName(String to, String subject,String templateName);

    public boolean sendHtmlTemplate(String to, String subject,Template t);
}
