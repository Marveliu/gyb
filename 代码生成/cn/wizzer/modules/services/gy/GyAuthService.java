package cn.wizzer.modules.services.gy;

import cn.wizzer.common.base.Service;
import cn.wizzer.modules.models.gy.Gy_auth;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author Wizzer.cn
 * @time 2017-11-09 16:40:06
 * 
 */
@IocBean(args = {"refer:dao"})
public class GyAuthService extends Service<Gy_auth> {
		public GyAuthService(Dao dao) {
				super(dao);
		}
}