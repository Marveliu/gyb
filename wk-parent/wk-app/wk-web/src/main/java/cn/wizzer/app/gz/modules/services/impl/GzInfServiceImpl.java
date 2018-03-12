package cn.wizzer.app.gz.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.gz.modules.services.GzInfService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class GzInfServiceImpl extends BaseServiceImpl<gz_inf> implements GzInfService {
    public GzInfServiceImpl(Dao dao) {
        super(dao);
    }
}
