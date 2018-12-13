package com.marveliu.framework.services.sys;


import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.services.base.BaseService;

/**
 * Created by wiz on 2016/12/22.
 */
public interface SysMsgService extends BaseService<Sys_msg> {

    /**
     * 消息推送
     * @param sysMsg
     */
    public boolean pushMsg(Sys_msg sysMsg);

}
