package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gy.modules.models.DicCountry;
import cn.wizzer.app.gy.modules.services.DiccountryService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class DiccountryServiceImpl extends BaseServiceImpl<DicCountry> implements DiccountryService {
    public DiccountryServiceImpl(Dao dao) {
        super(dao);
    }
}
