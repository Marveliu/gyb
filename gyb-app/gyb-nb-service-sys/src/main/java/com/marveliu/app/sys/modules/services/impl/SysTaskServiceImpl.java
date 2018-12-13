package com.marveliu.app.sys.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.sys.Sys_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.sys.SysTaskService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by wiz on 2016/12/22.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysTaskService.class)
public class SysTaskServiceImpl extends BaseServiceImpl<Sys_task> implements SysTaskService {
    public SysTaskServiceImpl(Dao dao) {
        super(dao);
    }
}
