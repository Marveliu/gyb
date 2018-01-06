package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.app.gy.modules.services.GyPayService;
import cn.wizzer.app.gy.modules.services.GySkillService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean(args = {"refer:dao"})
public class GySkillServiceImpl extends BaseServiceImpl<gy_skill> implements GySkillService {
    public GySkillServiceImpl(Dao dao) {
        super(dao);
    }

}
