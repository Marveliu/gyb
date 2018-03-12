package cn.wizzer.app.web.commons.services.email;

import cn.wizzer.framework.util.Code128Util;
import org.apache.commons.mail.HtmlEmail;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Created by Wizzer on 2016/7/31.
 */
public interface EmailService {

    public boolean send(String to, String subject, String html) ;

}
