package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_prepay;
import cn.wizzer.app.xm.modules.services.XmPrepayService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmPrepayServiceImpl extends BaseServiceImpl<xm_prepay> implements XmPrepayService {
    public XmPrepayServiceImpl(Dao dao) {
        super(dao);
    }
}
