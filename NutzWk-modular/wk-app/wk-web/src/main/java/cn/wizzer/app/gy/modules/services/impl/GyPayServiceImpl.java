package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.gy.modules.services.GyPayService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gy.modules.models.gy_pay;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class GyPayServiceImpl extends BaseServiceImpl<gy_pay> implements GyPayService {
    public GyPayServiceImpl(Dao dao) {
        super(dao);
    }
}
