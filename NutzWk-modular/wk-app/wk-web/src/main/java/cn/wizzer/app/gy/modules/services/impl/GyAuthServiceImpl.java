package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.framework.base.service.BaseServiceImpl;
import cn.wizzer.app.gy.modules.models.gy_auth;
import cn.wizzer.app.gy.modules.services.GyAuthService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;

@IocBean(args = {"refer:dao"})
public class GyAuthServiceImpl extends BaseServiceImpl<gy_auth> implements GyAuthService {
    public GyAuthServiceImpl(Dao dao) {
        super(dao);
    }

    public gy_auth getGyAuthByGyid(String gyid){
        return this.fetch(Cnd.where("gyid","=",gyid));
    }

    public boolean checkGyAuth(String gyid) {
       return Lang.isEmpty(this.getGyAuthByGyid(gyid));
    }

    public boolean ifAuth(String gyid){
        gy_auth auth = this.getGyAuthByGyid(gyid);
        if(Lang.isEmpty(auth)) return false;

        if(auth.getStatus() == 4){
            return true;
        }else {
            return false;
        }
    }

    public boolean enable(String gyid,String note){

        if(this.dao().update(gy_auth.class, Chain.make("status", 2).add("note",note), Cnd.where("gyid", "=", gyid))!=0){
            return true;

        }
        return false;
    }

    public boolean disable(String gyid,String note){
        if(this.dao().update(gy_auth.class, Chain.make("status", 3).add("note",note), Cnd.where("gyid", "=", gyid))!=0){
            return true;
        }
        return false;
    }
}
