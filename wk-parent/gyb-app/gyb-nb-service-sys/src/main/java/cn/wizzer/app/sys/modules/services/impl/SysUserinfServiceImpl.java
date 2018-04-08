package cn.wizzer.app.sys.modules.services.impl;

import cn.wizzer.app.sys.modules.models.Sys_userinf;
import cn.wizzer.app.sys.modules.services.SysUnitService;
import cn.wizzer.app.sys.modules.services.SysUserinfService;
import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by wizzer on 2016/12/22.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysUserinfService.class)
public class SysUserinfServiceImpl extends BaseServiceImpl<Sys_userinf> implements SysUserinfService {
    public SysUserinfServiceImpl(Dao dao) {
        super(dao);
    }
}
