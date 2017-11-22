package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.app.xm.modules.services.XmTaskService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmTaskServiceImpl extends BaseServiceImpl<xm_task> implements XmTaskService {
    public XmTaskServiceImpl(Dao dao) {
        super(dao);
    }
}
