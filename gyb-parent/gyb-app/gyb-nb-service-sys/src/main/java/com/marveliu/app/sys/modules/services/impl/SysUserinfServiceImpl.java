package com.marveliu.app.sys.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.sys.Sys_userinf;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.sys.SysUserinfService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

/**
 * Created by wizzer on 2016/12/22.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass = SysUserinfService.class)
public class SysUserinfServiceImpl extends BaseServiceImpl<Sys_userinf> implements SysUserinfService {
    public SysUserinfServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * @param uid
     * @return
     */
    @Override
    public String getSysuserinfid(String uid) {
        return Strings.sNull(this.fetch(Cnd.where("userid", "=", uid)).getId());
    }
}
