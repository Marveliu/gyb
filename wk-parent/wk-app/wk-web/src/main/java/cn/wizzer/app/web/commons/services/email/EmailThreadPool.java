package cn.wizzer.app.web.commons.services.email;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 多线程邮件服务
 *
 * @Author Marveliu
 * @Create 2018/1/8 0008.
 */

@IocBean
public class EmailThreadPool{

    BlockingQueue<Runnable> workQueue;//任务队列
    ExecutorService es;//线程池的接口

    public EmailThreadPool() {
        workQueue = new LinkedBlockingQueue<>();//构造无界的任务队列，资源足够，理论可以支持无线个任务
        es = new ThreadPoolExecutor(2, 4, //2 core； 4 max
                30, TimeUnit.SECONDS, workQueue, //30s keep alive
                new ThreadPoolExecutor.CallerRunsPolicy()); //任务失败重试
    }

    public void send(Runnable task) {
        System.out.println("PoolSend start sending mail...");
        es.execute(task);//将任务放入线程池
    }

    public void close() {// 关闭
        es.shutdown();
    }
}
