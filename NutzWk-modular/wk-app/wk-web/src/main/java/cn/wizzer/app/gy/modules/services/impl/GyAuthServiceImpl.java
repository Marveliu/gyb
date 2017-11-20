package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gy.modules.models.gy_auth;
import cn.wizzer.app.gy.modules.services.GyAuthService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class GyAuthServiceImpl extends BaseServiceImpl<gy_auth> implements GyAuthService {
    public GyAuthServiceImpl(Dao dao) {
        super(dao);
    }
}
