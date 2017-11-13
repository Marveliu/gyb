package cn.wizzer.app.hi.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.hi.modules.models.hitest;
import cn.wizzer.app.hi.modules.services.HitestService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class HitestServiceImpl extends BaseServiceImpl<hitest> implements HitestService {
    public HitestServiceImpl(Dao dao) {
        super(dao);
    }
}
