package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.app.xm.modules.services.XmTaskService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean(args = {"refer:dao"})
public class XmTaskServiceImpl extends BaseServiceImpl<xm_task> implements XmTaskService {
    public XmTaskServiceImpl(Dao dao) {
        super(dao);
    }

    @Inject
    private Dao dao;

    //将所有的参考字段填充
    public List<xm_task> fetchWithAllLinks(){
        return null;
    }
}
