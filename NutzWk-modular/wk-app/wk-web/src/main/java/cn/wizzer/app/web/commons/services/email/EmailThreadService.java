package cn.wizzer.app.web.commons.services.email;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.meta.Email;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 多线程邮件服务
 *
 * @Author Marveliu
 * @Create 2018/1/8 0008.
 */

@IocBean
public class EmailThreadService implements  Runnable{

    private final static Log log = Logs.get();
    private EmailService emailService;

    @Override
    public void run(){
        while(true)
        {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug(Thread.currentThread().getName()+"  " + new Date());
        }
    }


    public boolean run(EmailService emailService,String method){
        while(true)
        {
            try {

                Class clazz = emailService.getClass();

                try {
                    Method m1 = clazz.getDeclaredMethod(method);
                    m1.invoke(emailService);
                }catch (Exception e){
                    log.debug(e);
                }

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug(Thread.currentThread().getName()+"  " + new Date());
        }
    }


}
