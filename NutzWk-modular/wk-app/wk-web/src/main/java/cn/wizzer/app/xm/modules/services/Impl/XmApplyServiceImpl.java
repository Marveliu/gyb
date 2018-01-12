package cn.wizzer.app.xm.modules.services.Impl;

import cn.wizzer.app.xm.modules.models.xm_apply;
import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.app.xm.modules.services.XmApplyService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
public class XmApplyServiceImpl extends BaseServiceImpl<xm_apply> implements XmApplyService {
    public XmApplyServiceImpl(Dao dao) {
        super(dao);
    }

    public xm_task getTaskByAppyid(String id){
        String taskid = this.dao().fetch(xm_apply.class,id).getXmtaskid();
        return this.dao().fetch(xm_task.class,taskid);
    }
}
