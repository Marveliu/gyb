package com.marveliu.framework.services.sys;


import com.marveliu.framework.model.sys.Sys_config;
import com.marveliu.framework.services.base.BaseService;

import java.util.List;

/**
 * Created by wiz on 2016/12/23.
 */
public interface SysConfigService extends BaseService<Sys_config> {
    List<Sys_config> getAllList();
}
