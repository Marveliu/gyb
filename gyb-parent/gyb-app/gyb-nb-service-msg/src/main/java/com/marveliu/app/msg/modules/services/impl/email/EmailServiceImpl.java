package com.marveliu.app.msg.modules.services.impl.email;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.app.msg.commons.utils.TemplateUtil;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.email.EmailService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.beetl.core.Template;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Marveliu
 * @since 10/05/2018
 **/

@IocBean(create = "init", depose = "close")
@Service(interfaceClass= EmailService.class)
public class EmailServiceImpl implements EmailService,Runnable {

    private static final Log log = Logs.get();


    ExecutorService es;

    LinkedBlockingQueue<Email> queue;

    @Inject("refer:$ioc")
    protected Ioc ioc;

    @Inject
    private TemplateUtil templateUtil;

    public void init() {
        // 创建阻塞队列
        queue = new LinkedBlockingQueue<Email>();
        // 创建线程池
        int c = Runtime.getRuntime().availableProcessors();
        es = Executors.newFixedThreadPool(c);
        for (int i = 0; i < c; i++) {
            es.submit(this);
        }
    }

    public void close() throws InterruptedException {
        queue = null; // 触发关闭
        if (es != null && !es.isShutdown()) {
            es.shutdown();
            es.awaitTermination(5, TimeUnit.SECONDS);
        }
    }


    @Override
    public void run() {
        while (true) {
            LinkedBlockingQueue<Email> queue = this.queue;
            if (queue == null)
                break;
            try {
                // 从阻塞队列中拿task
                Email email = queue.poll(1, TimeUnit.SECONDS);
                if (email != null) {
                    // 发送邮件
                    send(email);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * 发送邮件
     * @param email
     * @return
     */
    private boolean send(Email email){
        try {
            email.buildMimeMessage();
            email.sendMimeMessage();
            return true;
        }catch (Exception e){
            log.error("邮件发送失败",e);
        }
        return false;
    }

    /**
     * 加入邮件发送队列
     * @param to
     * @param subject
     * @param html
     * @return
     */
    public boolean send(String to, String subject, String html) {
        try {
            HtmlEmail email = ioc.get(HtmlEmail.class);
            email.setSubject(subject);
            email.setCharset("UTF-8");
            email.setHtmlMsg(html);
            email.addTo(to);
            LinkedBlockingQueue<Email> queue = this.queue;
            // 添加队列
            if (queue != null)
                try {
                    // 失效时间
                    boolean re = queue.offer(email, 50, TimeUnit.MILLISECONDS);
                    if (!re) {
                        log.error("email queue is full, drop it ...");
                    }
                    return re;
                } catch (InterruptedException e) {
                }
        } catch (Throwable e) {
            log.error("send email fail", e);
        }

        return false;
    }

    /**
     * 发送模板邮件
     *
     * @param to
     * @param subject
     * @param tMsg
     * @return
     */
    public boolean sendHtmlTemplateByTemplateName(String to, String subject,TMsg tMsg){
        StringBuilder path = new StringBuilder();
        try {
            Template t =  templateUtil.buildTemplate(tMsg);
            this.send(to,subject,t.render());
            return true;
        }catch (Exception e){
            log.error("template not found:"+path);
        }

        return false;
    }

}
