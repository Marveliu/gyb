package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.services.GyInfService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class GyInfServiceImpl extends BaseServiceImpl<gy_inf> implements GyInfService {

    @Inject
    private GyInfService gyInfService;

    public GyInfServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 通过用户id获得雇员信息
     * @param userid
     * @return
     */
    public gy_inf getGyByUserId(String userid){
        return this.dao().fetch(gy_inf.class,Cnd.where("userid","=",userid));
    }

}
