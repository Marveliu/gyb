package com.marveliu.app.task.commons.base;

import com.marveliu.framework.model.sys.Sys_task;
import com.marveliu.framework.services.sys.SysTaskService;
import com.marveliu.framework.services.task.TaskPlatformService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

/**
 * Created by wizzer on 2018/3/19.
 */
@IocBean
public class Globals {
    private static final Log log = Logs.get();
    @Inject
    private TaskPlatformService taskPlatformService;

    public void init(SysTaskService sysTaskService) {
        taskPlatformService.clear();
        List<Sys_task> taskList = sysTaskService.query();
        for (Sys_task sysTask : taskList) {
            try {
                if (!sysTask.isDisabled())//不存在则新建
                    taskPlatformService.add(sysTask.getId(), sysTask.getId(), sysTask.getJobClass(), sysTask.getCron()
                            , sysTask.getNote(), sysTask.getData());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
