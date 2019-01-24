package com.marveliu.app.task.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.services.task.TaskPlatformService;
import org.nutz.integration.quartz.QuartzJob;
import org.nutz.integration.quartz.QuartzManager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.JobKey;

/**
 * Created by wiz on 2018/3/19.
 */
@IocBean
@Service(interfaceClass = TaskPlatformService.class)
public class TaskPlatformServiceImpl implements TaskPlatformService {
    @Inject
    private QuartzManager quartzManager;

    /**
     * 判断任务是否存在
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @Override
    public boolean isExist(String jobName, String jobGroup) {
        return quartzManager.exist(new JobKey(jobName, jobGroup));
    }

    /**
     * 添加Cron任务
     *
     * @param jobName
     * @param jobGroup
     * @param className
     * @param cron
     * @param comment
     * @param dataMap
     */
    @Override
    public void addCron(String jobName, String jobGroup, String className, String cron, String comment, String dataMap) {
        QuartzJob qj = new QuartzJob();
        qj.setJobName(jobName);
        qj.setJobGroup(jobGroup);
        qj.setClassName(className);
        qj.setCron(cron);
        qj.setComment(comment);
        qj.setDataMap(dataMap);
        quartzManager.add(qj);
    }


    /**
     * 添加Simple任务
     *
     * @param jobName
     * @param jobGroup
     * @param className
     * @param scheduled
     * @param comment
     * @param dataMap
     */
    @Override
    public void addSimple(String jobName, String jobGroup, String className, String scheduled, String comment, String dataMap) {
        QuartzJob qj = new QuartzJob();
        qj.setJobName(jobName);
        qj.setJobGroup(jobGroup);
        qj.setClassName(className);
        qj.setScheduled(scheduled);
        qj.setComment(comment);
        qj.setDataMap(dataMap);
        quartzManager.add(qj);
    }


    /**
     * 删除任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @Override
    public boolean delete(String jobName, String jobGroup) {
        QuartzJob qj = new QuartzJob();
        qj.setJobName(jobName);
        qj.setJobGroup(jobGroup);
        return quartzManager.delete(qj);
    }

    /**
     * 清除任务
     */
    @Override
    public void clear() {
        quartzManager.clear();
    }
}
