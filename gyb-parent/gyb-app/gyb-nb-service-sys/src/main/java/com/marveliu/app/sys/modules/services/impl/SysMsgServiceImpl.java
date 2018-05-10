package com.marveliu.app.sys.modules.services.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.sys.SysMsgService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;



@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysMsgService.class)
public class SysMsgServiceImpl extends BaseServiceImpl<Sys_msg> implements SysMsgService {
    public SysMsgServiceImpl(Dao dao) {
        super(dao);
    }


    /**
     * 消息推送
     *
     * @param sysMsg
     */
    @Override
    public void pushMsg(Sys_msg sysMsg) {
        //todo: 业务实现
    }
}
