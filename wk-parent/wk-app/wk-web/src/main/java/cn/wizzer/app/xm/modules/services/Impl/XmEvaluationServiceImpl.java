package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_evaluation;
import cn.wizzer.app.xm.modules.services.XmEvaluationService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmEvaluationServiceImpl extends BaseServiceImpl<xm_evaluation> implements XmEvaluationService {
    public XmEvaluationServiceImpl(Dao dao) {
        super(dao);
    }
}
