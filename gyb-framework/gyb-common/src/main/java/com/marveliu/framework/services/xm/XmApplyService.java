package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseService;

import java.util.List;

public interface XmApplyService extends BaseService<xm_apply> {


    public String generateId(String xmtaskid);

    /**
     * 添加项目申请
     * @param xmtaskid
     * @param gyid
     * @param async     高并发支持多线程
     * @return
     */
    public Boolean addXmApply(String xmtaskid,String gyid,Boolean async);

    /**
     * 处理任务申请信息
     *
     * @param xmapplyid
     * @param flag
     * @param uid
     * @return
     */
    public Boolean setXmApplyStatus(String xmapplyid, Boolean flag,String uid) ;

    /**
     * 通过申请编号获得任务
     * @param xmapplyid
     * @return
     */
    public xm_task getXmTaskByAppyid(String xmapplyid);


    /**
     * 通过雇员编号获得雇员申请的项目信息
     * @param gyid
     * @return
     */
    public List<xm_apply> getXmApplyListByGyid(String gyid);


    /**
     * 通过任务书编号获得所有的申请信息
     * @param xmtaskid
     * @return
     */
    public List<xm_apply> getXmApplyListByXmtaskid(String xmtaskid);


    /**
     * 查询雇员是否已经申请了任务书
     * @param xmtaskid
     * @param gyid
     * @return
     */
    public boolean isApplyAllow(String xmtaskid,String gyid);

}
