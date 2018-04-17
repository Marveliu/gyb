package cn.wizzer.app.gy.modules.services;


import cn.wizzer.app.gy.modules.models.gy_skill;
import cn.wizzer.framework.base.service.BaseService;

import java.util.List;
import java.util.Map;

public interface GySkillService extends BaseService<gy_skill>{


    // 通过雇员id获得用户的技能等级
    public Map<String,Float> getGySkills(String gyid);

    // 批量评级
    public Boolean setGySkillLevel(List<gy_skill> skills);
}
