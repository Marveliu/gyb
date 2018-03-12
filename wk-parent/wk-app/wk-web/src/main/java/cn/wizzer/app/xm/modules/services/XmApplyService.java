package cn.wizzer.app.xm.modules.services;

import cn.wizzer.app.xm.modules.models.xm_apply;
import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.framework.base.service.BaseService;

public interface XmApplyService extends BaseService<xm_apply>{

    public xm_task getTaskByAppyid(String id);
}
