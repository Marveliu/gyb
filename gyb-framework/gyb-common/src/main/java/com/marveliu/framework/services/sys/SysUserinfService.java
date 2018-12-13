package com.marveliu.framework.services.sys;


import com.marveliu.framework.model.sys.Sys_userinf;
import com.marveliu.framework.services.base.BaseService;

/**
 * Created by wiz on 2016/12/22.
 */
public interface SysUserinfService extends BaseService<Sys_userinf> {


    /**
     * 获得uid
     * @param uid
     * @return
     */
    public String getSysuserinfid(String uid);
}
