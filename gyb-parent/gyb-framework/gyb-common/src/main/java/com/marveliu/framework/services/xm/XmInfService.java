package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseService;

public interface XmInfService extends BaseService<xm_inf> {


    /**
     * 初始化项目信息
     * @param xmTask
     * @param gyid
     * @param uid
     * @return
     */
    public xm_inf initXminf(xm_task xmTask,String gyid,String uid);
}
