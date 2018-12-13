package com.marveliu.framework.services.sys;


import com.marveliu.framework.model.sys.Sys_menu;
import com.marveliu.framework.services.base.BaseService;

/**
 * Created by wiz on 2016/12/22.
 */
public interface SysMenuService extends BaseService<Sys_menu> {
    void save(Sys_menu menu, String pid);
    void deleteAndChild(Sys_menu unit);
}
