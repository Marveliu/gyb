package cn.wizzer.app.code.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.code.modules.models.xm_inf;
import cn.wizzer.app.code.modules.services.XmInfService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmInfServiceImpl extends BaseServiceImpl<xm_inf> implements XmInfService {
    public XmInfServiceImpl(Dao dao) {
        super(dao);
    }
}
