package cn.wizzer.app.library.modules.services;

import cn.wizzer.app.library.modules.models.lib_skill;
import cn.wizzer.app.library.modules.models.lib_task;
import cn.wizzer.framework.base.service.BaseService;

import java.util.List;

/**
 * Created by wizzer on 2016/12/22.
 */
public interface LibTaskService extends BaseService<lib_task> {
    List<lib_skill> getMenusAndButtons(String taskId);

    List<lib_skill> getDatas(String taskid);

    List<lib_skill> getDatas();

    void save(lib_task skill, String pid);

    void deleteAndChild(lib_task skill);
}
