package com.marveliu.app.sys.modules.services.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.sys.SysMsgService;
import org.nutz.dao.Dao;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.IOException;

import static org.nutz.integration.rabbitmq.aop.RabbitmqMethodInterceptor.channel;


@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysMsgService.class)
public class SysMsgServiceImpl extends BaseServiceImpl<Sys_msg> implements SysMsgService {


    private final static Log log = Logs.get();

    public SysMsgServiceImpl(Dao dao) {
        super(dao);
    }


    /**
     * 基于RabbitMQ消息推送
     *
     * @param sysMsg
     */
    @Override
    @Aop("rabbitmq")
    public boolean pushMsg(Sys_msg sysMsg) {
        try {
            this.insert(sysMsg);
            channel().basicPublish("", sysMsg.getTag(), null, Lang.toBytes(sysMsg));
            return true;
        }catch (IOException e){
            log.error("fail to get byte[]",e);
        }catch (Exception e){
            log.error("插入消息队列失败");
        }

        return false;
    }
}
