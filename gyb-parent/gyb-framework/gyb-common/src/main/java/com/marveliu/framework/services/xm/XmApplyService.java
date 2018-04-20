package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseService;

public interface XmApplyService extends BaseService<xm_apply> {

    public xm_task getTaskByAppyid(String id);
}
