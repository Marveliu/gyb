package cn.wizzer.app.sys.modules.services.impl;

import cn.wizzer.app.sys.modules.models.v_sysuser;
import cn.wizzer.app.sys.modules.services.VSysuserService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by wizzer on 2016/12/22.
 */
@IocBean(args = {"refer:dao"})
public class VUserinfServiceImpl extends BaseServiceImpl<v_sysuser> implements VSysuserService {
    public VUserinfServiceImpl(Dao dao) {
        super(dao);
    }
}
