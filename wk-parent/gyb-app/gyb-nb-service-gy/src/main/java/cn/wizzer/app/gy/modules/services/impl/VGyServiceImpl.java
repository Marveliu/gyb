package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.app.gy.modules.models.v_gy;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.gy.modules.services.GySkillService;
import cn.wizzer.app.gy.modules.services.VGyService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GySkillService.class)
public class VGyServiceImpl extends BaseServiceImpl<v_gy> implements VGyService {

    public VGyServiceImpl(Dao dao) {
        super(dao);
    }

}
