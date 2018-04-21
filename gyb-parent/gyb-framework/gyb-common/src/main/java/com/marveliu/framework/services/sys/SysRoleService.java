package com.marveliu.framework.services.sys;


import com.marveliu.framework.model.sys.Sys_menu;
import com.marveliu.framework.model.sys.Sys_role;
import com.marveliu.framework.services.base.BaseService;

import java.util.List;


// 系统角色接口
public interface SysRoleService extends BaseService<Sys_role> {
    List<Sys_menu> getMenusAndButtons(String roleId);

    List<Sys_menu> getDatas(String roleId);

    List<Sys_menu> getDatas();

    List<String> getPermissionNameList(Sys_role role);

    void del(String roleid);

    void del(String[] roleids);

    Sys_role getRoleFormCode(String code);
}
