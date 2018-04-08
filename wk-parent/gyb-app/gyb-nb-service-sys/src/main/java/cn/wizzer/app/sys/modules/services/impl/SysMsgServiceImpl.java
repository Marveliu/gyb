package cn.wizzer.app.sys.modules.services.impl;


import cn.wizzer.app.sys.modules.models.Sys_msg;
import cn.wizzer.app.sys.modules.services.SysMenuService;
import cn.wizzer.app.sys.modules.services.SysMsgService;
import cn.wizzer.framework.base.service.BaseService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;



@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysMsgService.class)
public class SysMsgServiceImpl extends BaseServiceImpl<Sys_msg> implements SysMsgService {
    public SysMsgServiceImpl(Dao dao) {
        super(dao);
    }
}
