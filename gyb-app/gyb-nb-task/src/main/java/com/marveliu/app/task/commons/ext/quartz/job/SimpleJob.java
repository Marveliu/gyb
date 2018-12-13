package com.marveliu.app.task.commons.ext.quartz.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.framework.services.sys.SysTaskService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by wiz.cn on 2015/6/27.
 */
@IocBean
public class SimpleJob implements Job {

    private static final Log log = Logs.get();


    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello world#############################!");
    }
}
