package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_feedback;
import cn.wizzer.app.xm.modules.services.XmFeedbackService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmFeedbackServiceImpl extends BaseServiceImpl<xm_feedback> implements XmFeedbackService {
    public XmFeedbackServiceImpl(Dao dao) {
        super(dao);
    }
}
