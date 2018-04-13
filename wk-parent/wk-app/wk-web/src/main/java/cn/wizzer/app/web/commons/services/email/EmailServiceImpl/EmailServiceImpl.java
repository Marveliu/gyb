package cn.wizzer.app.web.commons.services.email.EmailServiceImpl;

import cn.wizzer.app.web.commons.services.email.EmailService;
import cn.wizzer.framework.util.StringUtil;
import org.apache.commons.mail.HtmlEmail;
import org.beetl.core.*;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
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

    public boolean send(String to, String subject, String html) {
        try {
            HtmlEmail email = ioc.get(HtmlEmail.class);
            //HtmlEmail email = new HtmlEmail();
            email.setSubject(subject);
            email.setCharset("UTF-8");
            email.setHtmlMsg(html);
            email.addTo(to);
            email.buildMimeMessage();
            email.sendMimeMessage();
            return true;
        } catch (Throwable e) {
            log.error("send email fail", e);
            return false;
        }
    }

    /**
     * 发送模板邮件
     * @param to
     * @param subject
     * @param templateName
     * @return
     */
    public boolean sendHtmlTemplateByTemplateName(String to, String subject,String templateName){

        StringBuilder path = new StringBuilder();

        try {
            // ResourceLoader resourceLoader = new FileResourceLoader(
            //         Thread.currentThread().getContextClassLoader().getResource("").getPath(), "UTF-8") {
            // };

            // ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("");

            WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
            path.append(resourceLoader.getRoot());
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/template/email/"+templateName+".html");


            String str = t.render();
            log.debug(str);
            this.send(to,subject,str);
            return true;
        }catch (Exception e){
            log.error("template not found:"+path);
        }

        return false;
    }

    public boolean sendHtmlTemplate(String to, String subject,Template t) {
        try {
            String content = t.render();
            if(content.isEmpty()){
                log.debug(t);
            }
            return this.send(to,subject,content);
        }catch (Exception e){
            log.error("render error");
        }

        return false;
    }

}
