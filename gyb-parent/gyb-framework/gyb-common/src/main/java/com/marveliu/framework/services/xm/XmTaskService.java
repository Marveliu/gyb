package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;

public interface XmTaskService extends BaseService<xm_task> {


    /**
     * 启用或者禁用任务书
     * @param xmtaskid
     * @param flag true 启用 false 禁用
     * @return
     */
    public Boolean setXmTaskStatus(String xmtaskid, Boolean flag);

}
