package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_apply;
import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmApplyServiceImpl extends BaseServiceImpl<xm_apply> implements BaseService<xm_apply> {
    public XmApplyServiceImpl(Dao dao) {
        super(dao);
    }
}
