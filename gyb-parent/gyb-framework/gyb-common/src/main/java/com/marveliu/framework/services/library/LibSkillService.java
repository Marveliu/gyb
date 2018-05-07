package com.marveliu.framework.services.library;


import com.marveliu.framework.model.library.lib_skill;
import com.marveliu.framework.services.base.BaseService;

/**
 * Created by wizzer on 2016/12/22.
 */
public interface LibSkillService extends BaseService<lib_skill> {
    // 保存技能库信息
    void save(lib_skill skill, String pid);

    // 级联删除技能库信息
    void deleteAndChild(lib_skill skill);
}
