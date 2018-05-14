package com.marveliu.app.task.modules.services.impl;

import com.marveliu.app.task.commons.core.TaskMainLauncher;
import com.marveliu.app.task.commons.ext.quartz.job.SimpleJob;
import com.marveliu.framework.services.task.TaskPlatformService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.AppContext;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.integration.quartz.QuartzManager;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


@IocBean
@RunWith(NbJUnit4Runner.class)
public class TaskPlatformServiceImplTest {

    @Inject
    private QuartzManager quartzManager;

    @Inject
    private TaskPlatformService taskPlatformService;

    @Test
    public void timstampTest() throws Exception{
        Calendar now=Calendar.getInstance();
        long now1 = now.getTimeInMillis();
        now.add(Calendar.SECOND,15);
        long now2 = now.getTimeInMillis();
        System.out.println("date:"+(now2-now1));
        NutMap nutMap = new NutMap();
        nutMap.addv("rate",5);
        nutMap.addv("count",5);
        nutMap.addv("delay",15);
        String scheduled =  Json.toJson(nutMap);
        taskPlatformService.addSimple("test", "test", SimpleJob.class.getName(), scheduled,"test","");
        Thread.sleep(200000);
    }

    @Test
    public void test() throws Exception {

        Calendar now=Calendar.getInstance();
        now.add(Calendar.MINUTE,15);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr=sdf.format(now.getTimeInMillis());
        long time = System.currentTimeMillis();
        System.out.println("获取当前系统时间为："+new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒").format(time));
        System.out.println("获取当前系统时间为："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
        System.out.println("三十分钟后的时间为："+dateStr);

        //创建scheduler
        // Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Ioc ioc = AppContext.getDefault().getIoc();
        Scheduler scheduler = ioc.get(Scheduler.class);

        //定义一个JobDetail
        JobDetail job = newJob(SimpleJob.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("job1", "group1") //定义name/group
                .usingJobData("name", "quartz") //定义属性
                .build();


        // 通过SimpleTrigger触发器定义调度规则：10s后启动
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(now.getTime()) // some Date
                .forJob("job1", "group1") // identify job with name, group strings
                .build();

        //加入这个调度
        scheduler.scheduleJob(job, trigger);

        //启动之
        scheduler.start();

        //运行一段时间后关闭
        Thread.sleep(200000);
        scheduler.shutdown(true);
    }


    // public static NbApp createNbApp() {
    //     NbApp nb = new NbApp().setMainClass(TaskMainLauncher.class).setPrintProcDoc(false);
    //     nb.getAppContext().setMainPackage("com.marveliu");
    //     return nb;
    // }
}