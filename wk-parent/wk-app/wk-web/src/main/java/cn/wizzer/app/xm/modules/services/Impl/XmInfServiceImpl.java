package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_inf;
import cn.wizzer.app.xm.modules.services.XmInfService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmInfServiceImpl extends BaseServiceImpl<xm_inf> implements XmInfService {
    public XmInfServiceImpl(Dao dao) {
        super(dao);
    }
}
