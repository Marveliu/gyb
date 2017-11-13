package cn.wizzer.app.hi.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.hi.modules.models.ceshi;
import cn.wizzer.app.hi.modules.services.CeshiService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class CeshiServiceImpl extends BaseServiceImpl<ceshi> implements CeshiService {
    public CeshiServiceImpl(Dao dao) {
        super(dao);
    }
}
