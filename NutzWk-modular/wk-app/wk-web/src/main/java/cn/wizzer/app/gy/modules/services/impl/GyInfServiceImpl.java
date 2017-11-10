package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.services.GyInfService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class GyInfServiceImpl extends BaseServiceImpl<gy_inf> implements GyInfService {
    public GyInfServiceImpl(Dao dao) {
        super(dao);
    }
}
