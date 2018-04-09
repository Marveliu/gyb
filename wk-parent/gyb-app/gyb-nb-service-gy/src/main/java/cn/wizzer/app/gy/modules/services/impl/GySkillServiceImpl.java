package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.app.gy.modules.services.GyAuthService;
import cn.wizzer.app.gy.modules.services.GySkillService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GySkillService.class)
public class GySkillServiceImpl extends BaseServiceImpl<gy_skill> implements GySkillService {
    public GySkillServiceImpl(Dao dao) {
        super(dao);
    }

}
