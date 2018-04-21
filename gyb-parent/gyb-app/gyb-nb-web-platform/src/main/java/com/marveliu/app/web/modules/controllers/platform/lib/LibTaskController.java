package com.marveliu.app.web.modules.controllers.platform.lib;
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
import com.marveliu.app.web.commons.utils.ShiroUtil;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.library.lib_skill;
import com.marveliu.framework.model.library.lib_task;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.library.LibSkillService;
import com.marveliu.framework.services.library.LibTaskService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marveliu
 * @since 21/04/2018
 **/
@IocBean
@At("/platform/lib/task")
public class LibTaskController {
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private LibTaskService libTaskService;

    @Inject
    @Reference
    private LibSkillService libSkillService;
    @Inject
    private ShiroUtil shiroUtil;

    //todo:给了管理员什么的权限？
    @At("")
    @Ok("beetl:/platform/lib/task/index.html")
    @RequiresPermissions("lib.task")
    public Object index() {
        if (shiroUtil.hasRole("sysadmin")) {
            return libTaskService.query(Cnd.where("parentId", "=", "").or("parentId", "is", null).asc("path"));
        }
        Sys_user user = (Sys_user) shiroUtil.getPrincipal();
        if (user != null) {
            return libTaskService.query(Cnd.where("id", "=", user.getUnitid()).asc("path"));
        }
        return new ArrayList<>();
    }



    @At
    @Ok("beetl:/platform/lib/task/add.html")
    @RequiresPermissions("lib.task")
    public Object add(@Param("pid") String pid) {

        return Strings.isBlank(pid) ? null : libTaskService.fetch(pid);
    }

    @At
    @Ok("json")
    @RequiresPermissions("lib.task.add")
    @SLog(tag = "新建技能", msg = "技能 名称:${args[0].name}")
    public Object addDo(@Param("..") lib_task task, @Param("parentId") String parentId, HttpServletRequest req) {
        try {
            libTaskService.save(task, parentId);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/child/?")
    @Ok("beetl:/platform/lib/task/child.html")
    @RequiresPermissions("lib.task")
    public Object child(String id) {
        return libTaskService.query(Cnd.where("parentId", "=", id).asc("path"));
    }

    @At("/detail/?")
    @Ok("beetl:/platform/lib/task/detail.html")
    @RequiresPermissions("lib.task")
    public Object detail(String id) {
        return libTaskService.fetch(id);
    }

    @At("/edit/?")
    @Ok("beetl:/platform/lib/task/edit.html")
    @RequiresPermissions("lib.task")
    public Object edit(String id, HttpServletRequest req) {
        lib_task task = libTaskService.fetch(id);
        if (task != null) {
            req.setAttribute("parentUnit", libTaskService.fetch(task.getParentId()));
        }
        return task;
    }

    @At
    @Ok("json")
    @RequiresPermissions("lib.task.edit")
    @SLog(tag = "编辑技能", msg = "技能名称:${args[0].name}")
    public Object editDo(@Param("..") lib_task task, @Param("parentId") String parentId, HttpServletRequest req) {
        try {
            task.setOpBy(StringUtil.getPlatformUid());
            task.setOpAt((Long) (System.currentTimeMillis() / 1000));
            libTaskService.updateIgnoreNull(task);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("lib.task.delete")
    @SLog(tag = "删除技能", msg = "技能名称:${args[1].getAttribute('name')}")
    public Object delete(String id, HttpServletRequest req) {
        try {
            lib_task task = libTaskService.fetch(id);
            req.setAttribute("name", task.getName());
            if ("0001".equals(task.getPath())) {
                return Result.error("system.not.allow");
            }
            libTaskService.deleteAndChild(task);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At
    @Ok("json")
    @RequiresPermissions("lib.task")
    public Object tree(@Param("pid") String pid) {
        List<lib_task> list = new ArrayList<>();
        if (shiroUtil.hasRole("sysadmin")) {
            Cnd cnd = Cnd.NEW();
            if (Strings.isBlank(pid)) {
                cnd.and("parentId", "=", "").or("parentId", "is", null);
            } else {
                cnd.and("parentId", "=", pid);
            }
            cnd.asc("path");
            list = libTaskService.query(cnd);
        } else {
            //todo： 查询员工拥有的技能库
            Sys_user user = (Sys_user) shiroUtil.getPrincipal();
            if (user != null && Strings.isBlank(pid)) {
                list = libTaskService.query(Cnd.where("id", "=", user.getUnitid()).asc("path"));
            } else {
                Cnd cnd = Cnd.NEW();
                if (Strings.isBlank(pid)) {
                    cnd.and("parentId", "=", "").or("parentId", "is", null);
                } else {
                    cnd.and("parentId", "=", pid);
                }
                cnd.asc("path");
                list = libTaskService.query(cnd);
            }
        }
        List<Map<String, Object>> tree = new ArrayList<>();
        for (lib_task task : list) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", task.getId());
            obj.put("text", task.getName());
            obj.put("children", task.isHasChildren());
            tree.add(obj);
        }
        return tree;
    }
    @At("/editSkill/?")
    @Ok("beetl:/platform/lib/task/editskill.html")
    @RequiresPermissions("lib.task")
    public Object editSkill(String taskId, HttpServletRequest req) {
        StringBuilder roleMenuIds = new StringBuilder();
        List<lib_skill> list = new ArrayList<>();
        list = libSkillService.query(Cnd.orderBy().asc("location").asc("path"));

        List<lib_skill> datas = libTaskService.getDatas();
        List<lib_skill> roleMenu = libTaskService.getMenusAndButtons(taskId);

        for (lib_skill m : roleMenu) {
            roleMenuIds.append(m.getId() + "#");
        }
        String roleMenuId = roleMenuIds.toString();

        log.debug(roleMenuId);
        List<NutMap> menus = new ArrayList<>();
        for (lib_skill menu : list) {
            NutMap map = new NutMap();
            for (lib_skill bt : datas) {
                if (menu.getPath().equals(bt.getPath().substring(0, bt.getPath().length() - 4))) {
                    menu.setHasChildren(true);
                    break;
                }
            }
            map.put("id", menu.getId());
            map.put("text", menu.getName());
//            map.put("icon", Strings.sBlank(menu.getIcon()));
            map.put("parent", "".equals(Strings.sNull(menu.getParentId())) ? "#" : menu.getParentId());
//            map.put("data", menu.getHref());
            if ((menu.getPath().length() >=16 || !menu.isHasChildren()) && roleMenuId.contains(menu.getId() + "#")) {
                map.put("state", NutMap.NEW().addv("selected", true));
            } else {
                map.put("state", NutMap.NEW().addv("selected", false));
            }
            menus.add(map);
        }
        req.setAttribute("menus", Json.toJson(menus));
        return Strings.isBlank(taskId) ? null : libTaskService.fetch(taskId);
    }

    @At
    @Ok("json")
    @RequiresPermissions("lib.task")
    @SLog(tag = "修改角色菜单", msg = "角色名称:${args[2].getAttribute('name')}")
    public Object editSkillDo(@Param("skillIds") String skillIds, @Param("taskid") String taskid, HttpServletRequest req) {
        try {
            String[] ids = StringUtils.split(skillIds, ",");
            libTaskService.dao().clear("lib_task_skill", Cnd.where("taskid", "=", taskid));
            for (String s : ids) {
                if (!Strings.isEmpty(s)) {
                    libTaskService.insert("lib_task_skill", org.nutz.dao.Chain.make("taskId", taskid).add("skillId", s));
                }
            }
            lib_task task = libTaskService.fetch(taskid);
            req.setAttribute("name", task.getName());
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

}
