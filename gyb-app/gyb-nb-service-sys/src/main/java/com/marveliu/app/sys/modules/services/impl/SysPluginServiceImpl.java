package com.marveliu.app.sys.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.sys.Sys_plugin;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.sys.SysPluginService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by wiz on 2016/12/23.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysPluginService.class)
public class SysPluginServiceImpl extends BaseServiceImpl<Sys_plugin> implements SysPluginService {
    public SysPluginServiceImpl(Dao dao) {
        super(dao);
    }
}