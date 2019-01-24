package com.marveliu.app.sys.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.sys.Sys_config;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.sys.SysConfigService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * Created by wiz on 2016/12/23.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass = SysConfigService.class)
public class SysConfigServiceImpl extends BaseServiceImpl<Sys_config> implements SysConfigService {

    public SysConfigServiceImpl(Dao dao) {
        super(dao);
    }

    @Override
    public List<Sys_config> getAllList() {
        return this.query(Cnd.where("delFlag", "=", false));
    }
}