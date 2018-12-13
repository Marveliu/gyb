package com.marveliu.app.sys.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.sys.Sys_route;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.sys.SysRouteService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by wiz on 2016/12/23.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysRouteService.class)
public class SysRouteServiceImpl extends BaseServiceImpl<Sys_route> implements SysRouteService {
    public SysRouteServiceImpl(Dao dao) {
        super(dao);
    }
}