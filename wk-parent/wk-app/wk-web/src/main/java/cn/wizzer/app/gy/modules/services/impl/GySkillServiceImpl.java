package cn.wizzer.app.gy.modules.services.impl;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.app.gy.modules.services.GyPayService;
import cn.wizzer.app.gy.modules.services.GySkillService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IocBean(args = {"refer:dao"})
public class GySkillServiceImpl extends BaseServiceImpl<gy_skill> implements GySkillService {
    public GySkillServiceImpl(Dao dao) {
        super(dao);
    }

    // 通过雇员id获得用户的技能等级
    public Map<String,Float> getGySkills(String gyid){
        Map<String,Float> result = new HashMap<String, Float>();
        Map<String,Integer> count = new HashMap<>();
        Cnd cnd = Cnd.where("gyid","=",gyid);
        List<gy_skill> skillList =  this.dao().query(gy_skill.class,cnd);
        if(!skillList.isEmpty()){
            for (gy_skill skill : skillList){
                if (count.containsKey(skill.getSkillid())){
                    // 加权计算
                    result.put(skill.getSkillid(),skill.getSkilllevel());
                    count.put(skill.getSkillid(),count.get(skill.getSkillid())+1);
                }else {
                    result.put(skill.getSkillid(),skill.getSkilllevel());
                }
            }
        }
        for (Map.Entry<String,Integer> item : count.entrySet()){
            if(result.containsKey(item.getKey())){
                result.put(item.getKey(),result.get(item.getKey())/item.getValue());
            }
        }
        return result;
    }

    // 对雇员的技能进行批量评级
    public Boolean setGySkillLevel(List<gy_skill> skills){
       return !this.dao().insert(skills).isEmpty() ;
    }
}
