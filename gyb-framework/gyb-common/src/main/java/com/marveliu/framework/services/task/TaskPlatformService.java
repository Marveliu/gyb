package com.marveliu.framework.services.task;

/**
 * Created by wiz on 2018/3/19.
 */
public interface TaskPlatformService {
    /**
     * 判断任务是否存在
     * @param jobName
     * @param jobGroup
     * @return
     */
    boolean isExist(String jobName,String jobGroup);

    /**
     * 添加Cron 任务
     * @param jobName
     * @param jobGroup
     * @param className
     * @param cron
     * @param comment
     * @param dataMap
     */
    void addCron(String jobName,String jobGroup,String className,String cron,String comment,String dataMap);


    /**
     * 添加Simple 任务
     * @param jobName
     * @param jobGroup
     * @param className
     * @param scheduled
     * @param comment
     * @param dataMap
     */
    void addSimple(String jobName,String jobGroup,String className,String scheduled,String comment,String dataMap);




    /**
     * 删除任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    boolean delete(String jobName,String jobGroup);

    /**
     * 清除所有任务
     */
    void clear();
}
