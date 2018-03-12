package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_bill;
import cn.wizzer.app.xm.modules.services.XmBillService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmBillServiceImpl extends BaseServiceImpl<xm_bill> implements XmBillService {
    public XmBillServiceImpl(Dao dao) {
        super(dao);
    }
}
