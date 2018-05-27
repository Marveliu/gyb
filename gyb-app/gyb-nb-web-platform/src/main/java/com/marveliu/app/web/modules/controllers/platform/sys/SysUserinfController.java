package com.marveliu.app.web.modules.controllers.platform.sys;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.sys.Sys_userinf;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.sys.SysRoleService;
import com.marveliu.framework.services.sys.SysUserinfService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Marveliu
 * @since 27/05/2018
 **/

@IocBean
@At("/platform/sys/userinf")
public class SysUserinfController {
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private SysUserinfService sysUserinfService;

    @Inject
    @Reference
    private SysRoleService roleService;



    @At("")
    @Ok("beetl:/platform/sys/userinf/index.html")
    @RequiresPermissions("platform.sys.userinf.list")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.list")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        Object test = sysUserinfService.data(length, start, draw, order, columns, cnd, null);
        return sysUserinfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/sys/userinf/add.html")
    @RequiresPermissions("platform.sys.userinf.add")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.add")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object addDo(
            @Param("..")Sys_userinf userinf,
            @Param("birthdayat") String birthday, HttpServletRequest req) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            userinf.setBirthday(birthdayat);
            sysUserinfService.insert(userinf);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/person")
    @Ok("beetl:/platform/sys/userinf/person.html")
    @RequiresPermissions("platform.sys.userinf.person")
    public void person(HttpServletRequest req) {
        String userid =  StringUtil.getSysuserinfId();
        req.setAttribute("obj", sysUserinfService.fetch(userid));
    }


    @At("/personeditDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.person")
    public Object personeditDo(@Param("..")Sys_userinf userinf, @Param("birthdayat") String birthday, HttpServletRequest req) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            userinf.setBirthday(birthdayat);
            sysUserinfService.updateIgnoreNull(userinf);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/edit/?")
    @Ok("beetl:/platform/sys/userinf/edit.html")
    @RequiresPermissions("platform.sys.userinf.edit")
    public void edit(String id,HttpServletRequest req) {
        req.setAttribute("obj", sysUserinfService.fetch(id));

    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.edit")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object editDo(@Param("..")Sys_userinf userinf, @Param("birthdayat") String birthday, HttpServletRequest req) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            userinf.setBirthday(birthdayat);
            sysUserinfService.updateIgnoreNull(userinf);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/detail/?")
    @Ok("beetl:/platform/sys/userinf/detail.html")
    @RequiresPermissions("platform.sys.userinf")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            Object test = sysUserinfService.fetch(Cnd.where("id","=",id));
            req.setAttribute("obj",test);
        }else{
            req.setAttribute("obj", null);
        }
    }


    @At
    @Ok("beetl:/platform/sys/userinf/selectUser.html")
    @RequiresPermissions("platform.sys.userinf")
    public void selectUser() {
    }


    /**
     * @function: 查询需要提交的用户信息
     * @param: roleid查询用户的角色, name,
     * @return:
     * @note:
     */
    @At
    @Ok("json:full")
    @RequiresPermissions("sys.manager.role")
    public Object selectData(@Param("roleid") String roleid, @Param("name") String name, @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        String sql = "SELECT a.* FROM sys_user a WHERE 1=1 ";
        if (!Strings.isBlank(roleid)) {
            sql += " and a.id NOT IN(SELECT b.userId FROM sys_user_role b WHERE b.roleId='" + roleid + "')";
        }
        if (!Strings.isBlank(name)) {
            sql += " and (a.loginname like '%" + name + "%' or a.nickname like '%" + name + "%') ";
        }
        String s = sql;
        if (order != null && order.size() > 0) {
            for (DataTableOrder o : order) {
                DataTableColumn col = columns.get(o.getColumn());
                s += " order by a." + Sqls.escapeSqlFieldValue(col.getData()).toString() + " " + o.getDir();
            }
        }
        Object test = roleService.data(length, start, draw, Sqls.create(sql), Sqls.create(s));
        return roleService.data(length, start, draw, Sqls.create(sql), Sqls.create(s));
    }

}