package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.v_xminf;
import cn.wizzer.app.xm.modules.services.V_XmInfService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class V_XmInfServiceImpl extends BaseServiceImpl<v_xminf> implements V_XmInfService {
    public V_XmInfServiceImpl(Dao dao) {
        super(dao);
    }
}
