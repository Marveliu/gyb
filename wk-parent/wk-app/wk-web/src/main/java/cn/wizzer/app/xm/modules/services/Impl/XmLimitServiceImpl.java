package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_limit;
import cn.wizzer.app.xm.modules.services.XmLimitService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean(args = {"refer:dao"})
public class XmLimitServiceImpl extends BaseServiceImpl<xm_limit> implements XmLimitService {
    public XmLimitServiceImpl(Dao dao) {
        super(dao);
    }

    @Inject
    private Dao dao;

    //将所有的参考字段填充
    public List<xm_limit> fetchWithAllLinks(){
        return null;
    }
}
