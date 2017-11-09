package cn.wizzer.modules.services.gy;

import cn.wizzer.common.base.Service;
import cn.wizzer.modules.models.gy.Gy_inf;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author Wizzer.cn
 * @time 2017-11-09 16:40:06
 * 
 */
@IocBean(args = {"refer:dao"})
public class GyInfService extends Service<Gy_inf> {
		public GyInfService(Dao dao) {
				super(dao);
		}
}