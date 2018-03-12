package cn.wizzer.app.library.modules.services;

import cn.wizzer.app.library.modules.models.lib_skill;
import cn.wizzer.framework.base.service.BaseService;

/**
 * Created by wizzer on 2016/12/22.
 */
public interface LibSkillService extends BaseService<lib_skill> {
    void save(lib_skill skill, String pid);

    void deleteAndChild(lib_skill skill);
}
