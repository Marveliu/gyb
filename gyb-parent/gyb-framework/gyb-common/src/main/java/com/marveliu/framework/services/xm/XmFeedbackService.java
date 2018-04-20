package com.marveliu.framework.services.xm;


import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.services.base.BaseService;

public interface XmFeedbackService extends BaseService<xm_feedback> {

    public int getXfdCount(String xminfid);
}
