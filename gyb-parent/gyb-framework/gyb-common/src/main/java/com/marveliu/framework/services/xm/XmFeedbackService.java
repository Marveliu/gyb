package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseService;

public interface XmFeedbackService extends BaseService<xm_feedback> {

    /**
     * 获得项目反馈总数
     * @param xminfid
     * @return
     */
    public int getXmfeedbackCount(String xminfid);


    /**
     * 是否运行进行反馈
     * @param xminfid
     * @return
     */
    public boolean isXmeedbackAllowed(String xminfid);


    /**
     * 雇员添加项目
     *
     * @param xmFeedback
     * @return
     */
    public xm_feedback addXmfeedback(xm_feedback xmFeedback);


    /**
     * 雇员提交项目反馈
     *
     * @param xmfeedbackid
     * @return
     */
    public boolean commitXmfeedback(long xmfeedbackid);


    /**
     * 项目经理审批项目反馈
     *
     * @param xmFeedback
     * @return
     */
    public boolean checkXmfeedback(xm_feedback xmFeedback);


    /**
     * 项目经理确认项目反馈
     *
     * @param xmfeedbackid
     * @param flag
     * @return
     */
    public boolean confirmXmfeedback(long xmfeedbackid, Boolean flag);


    /**
     * 获得项目最新一次的反馈
     * @param xminfid
     * @return
     */
    public xm_feedback getLatestXmfeedback(String xminfid);


    /**
     * 根据项目反馈编号获得任务书
     * @param xmfeedbackid
     * @return
     */
    public xm_task getXmtaskByXmfeedbackid(long xmfeedbackid);




}
