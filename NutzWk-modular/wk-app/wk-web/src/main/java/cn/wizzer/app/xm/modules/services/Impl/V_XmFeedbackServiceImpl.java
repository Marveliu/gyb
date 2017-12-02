package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.v_xmfeedback;
import cn.wizzer.app.xm.modules.services.V_XmFeedbackService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class V_XmFeedbackServiceImpl extends BaseServiceImpl<v_xmfeedback> implements V_XmFeedbackService {
    public V_XmFeedbackServiceImpl(Dao dao) {
        super(dao);
    }
}
