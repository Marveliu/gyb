package com.marveliu.app.web.modules.controllers.platform.xm;
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
import com.marveliu.framework.model.library.lib_task;
import com.marveliu.framework.model.xm.xm_limit;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.library.LibSkillService;
import com.marveliu.framework.services.library.LibTaskService;
import com.marveliu.framework.services.sys.SysUserinfService;
import com.marveliu.framework.services.xm.XmTaskService;
import com.marveliu.framework.util.statusUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/

@IocBean
@At("/platform/xm/task")
public class XmTaskController {
    private static final Log log = Logs.get();

    @Inject
    @Reference
    private XmTaskService xmTaskService;

    @Inject
    @Reference
    private SysUserinfService sysUserinfService;
    @Inject
    @Reference
    private LibTaskService libTaskService;
    @Inject
    @Reference
    private LibSkillService libSkillService;
    @Inject
    private ShiroUtil shiroUtil;

    @At("")
    @Ok("beetl:/platform/xm/task/index.html")
    @RequiresPermissions("platform.xm.task")
    public void index() {
    }

    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.task")
    public Object tree(@Param("pid") String pid) {
        List<lib_task> list = libTaskService.query(Cnd.where("parentId", "=", Strings.sBlank(pid)).asc("location").asc("path"));
        List<Map<String, Object>> tree = new ArrayList<>();
        if (Strings.isBlank(pid)) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", "0");
            obj.put("text", "所有类型");
            obj.put("children", false);
            tree.add(obj);
        }

        for (lib_task libTask : list) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", libTask.getId());
            obj.put("text", libTask.getName());
            obj.put("children", libTask.isHasChildren());
            tree.add(obj);
        }
        return tree;
    }

    //递归一个cnd
    private List<lib_task> ChildTaskID(String ParentTaskId) {
        List<lib_task> list = new CopyOnWriteArrayList();
        List<lib_task> alllist = new CopyOnWriteArrayList();
        list = libTaskService.query(Cnd.where("parentId", "=", Strings.sBlank(ParentTaskId)).asc("location").asc("path"));
        if (!list.isEmpty()) {
            for (lib_task libTask : list) {
                alllist.addAll(ChildTaskID(libTask.getId()));
            }
        }
        alllist.addAll(list);
        return alllist;
    }

    /**
     * @function: 任务书列表
     * @param: 任务书所属项目类别，任务书题目
     * @return:
     * @note:
     */
    @At
    @Ok("json:full")
    @RequiresPermissions("platform.xm.task")
    public Object data(
            @Param("libtaskId") String libtaskid,
            @Param("title") String title,
            @Param("status") int status,
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {

        Cnd cnd = Cnd.NEW();

        // 超级权限判定
        if (!shiroUtil.isSuper()) {
            cnd.and("author", "=", sysUserinfService.getSysuserinfid(StringUtil.getPlatformUid()));
        }

        // 任务书名称
        if (!Strings.isBlank(title)) {
            cnd.and("taskname", "like", "%" + title + "%");
        }else{
            // 其他描述
            if(5!= status){
                cnd.and("status","=",status);
            }
            // 任务类别
            if (!Strings.isBlank(libtaskid) && !"0".equals(libtaskid)) {
                List<lib_task> libtask = ChildTaskID(libtaskid);
                for (lib_task Lib_Task : libtask) {
                    cnd.and("category", "like", "%" + Lib_Task.getId() + "%");
                }
                cnd.and("category", "like", "%" + libtaskid + "%");
            }
        }

        return xmTaskService.data(length, start, draw, order, columns, cnd, null);
    }


    @At
    @Ok("beetl:/platform/xm/task/add.html")
    @RequiresPermissions("platform.xm.task.add")
    public void add(@Param("libtaskId") String libtaskid, HttpServletRequest req) {
        String sysuserinfid = sysUserinfService.getSysuserinfid(StringUtil.getPlatformUid());
        req.setAttribute("authorid", sysuserinfid);
        req.setAttribute("libtask", libtaskid != null && !"0".equals(libtaskid) ? libTaskService.fetch(libtaskid) : null);
    }


    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.task.add")
    @SLog(type = "xm", tag = "添加任务书", msg = "任务书标题:${args[0].taskname}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(
            @Param("::") xm_task xmtask,
            @Param("firstcommitat") String firstcommit,
            @Param("endtimeat") String endtime,
            @Param("applyendtimeat") String applyendtime,
            @Param("at") String at,
            HttpServletRequest req) {
        try {

            String sysuserid = StringUtil.getPlatformUid();
            //设置时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int publishAt = (int) (sdf.parse(at).getTime() / 1000);
            int firstcommitAt = (int) (sdf.parse(firstcommit).getTime() / 1000);
            int endtimeAt = (int) (sdf.parse(endtime).getTime() / 1000);
            int applyendtimeAt = (int) (sdf.parse(applyendtime).getTime() / 1000);
            xmtask.setPublishAt(publishAt);
            xmtask.setFirstcommit(firstcommitAt);
            xmtask.setEndtime(endtimeAt);
            xmtask.setApplyendtime(applyendtimeAt);
            //初始编辑者和阅读数量
            xmtask.setAuthor(sysUserinfService.getSysuserinfid(StringUtil.getPlatformUid()));
            // 默认不发布
            xmtask.setDisabled(true);
            xmtask.setStatus(statusUtil.XM_TASK_INIT);
            //插入任务书，和任务书的技能要求
            xmTaskService.insertWith(xmtask, "xmlimits");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/task/edit.html")
    @RequiresPermissions("platform.xm.task.edit")
    public Object edit(String xmtaskid, HttpServletRequest req) {
        if (isAllowForXmtask(xmtaskid)) {
            xm_task xmTask = xmTaskService.getXmtaskDetail(xmtaskid);
            if (!Lang.isEmpty(xmTask)) {
                req.setAttribute("libtask", xmTask.getLibtask());
                req.setAttribute("xmlimits", xmTask.getXmlimits());
                return xmTask;
            }
        }
        return null;
    }

    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.task.edit")
    @SLog(type = "xm", tag = "修改任务书", msg = "任务书标题:${args[0].taskname}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(
            @Param("::") xm_task xmtask,
            @Param("firstcommitat") String firstcommit,
            @Param("endtimeat") String endtime,
            @Param("applyendtimeat") String applyendtime,
            @Param("at") String at,
            HttpServletRequest req) {
        if (!isAllowForXmtask(xmtask.getId())) return Result.error("system.nopermission");
        try {
            //设置时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int publishAt = (int) (sdf.parse(at).getTime() / 1000);
            int firstcommitAt = (int) (sdf.parse(firstcommit).getTime() / 1000);
            int endtimeAt = (int) (sdf.parse(endtime).getTime() / 1000);
            int applyendtimeAt = (int) (sdf.parse(applyendtime).getTime() / 1000);
            xmtask.setPublishAt(publishAt);
            xmtask.setFirstcommit(firstcommitAt);
            xmtask.setEndtime(endtimeAt);
            xmtask.setApplyendtime(applyendtimeAt);
            xmtask.setStatus(statusUtil.XM_TASK_INIT);
            xmtask.setDisabled(true);
            if (xmTaskService.updateXmtask(xmtask)) {
                return Result.success("system.success");
            }
        } catch (Exception e) {
            return Result.error("system.error");
        }
        return Result.error("system.error");
    }

    @At("/setXmtaskStatus")
    @Ok("json")
    @RequiresPermissions("platform.xm.task.edit")
    @SLog(type = "xm", tag = "修改任务书状态", msg = "任务书编号：${args[0]},启用状态：${args[1]}")
    public Object setXmtaskStatus(
            @Param("xmtaskid") String xmtaskid,
            @Param("flag") boolean flag,
            HttpServletRequest req) {
        try {
            if (xmTaskService.setXmTaskStatus(xmtaskid, flag)) {
                return Result.success("任务书编号" + xmtaskid + "启用状态:" + flag);
            }
        } catch (Exception e) {
            return Result.error("system.error");
        }
        return Result.error("system.error");
    }


    // 只允许一次删除一个任务书
    @At({"/delete/?"})
    @Ok("json")
    @RequiresPermissions("platform.xm.task.delete")
    @SLog(type = "xm", tag = "删除任务书", msg = "ID:${args[0]}")
    public Object delete(String oneId, HttpServletRequest req) {
        try {
            xm_task xmTask = xmTaskService.fetch(oneId);
            if(Lang.isEmpty(xmTask)) return Result.success("没有该任务书信息");
            if(xmTask.getStatus() >= statusUtil.XM_TASK_APPLYING) return Result.success("任务书已经在申请了，无法删除");
            if(xmTaskService.deleteXmtask(oneId)){
                req.setAttribute("id", oneId);
                return Result.success("system.success");
            }
        } catch (Exception e) {
            return Result.error("system.error");
        }
        return Result.error("system.error");
    }

    /**
     * @function: 获得指定任务类型的所有需要技能
     * @param:
     * @return:
     * @note:
     */
    @At
    @Ok("json:full")
    @RequiresPermissions("platform.xm.task")
    public Object limitdata(@Param("libtaskid") String libtaskid) {
        lib_task libtask = libTaskService.fetch(libtaskid);
        libtask = libTaskService.fetchLinks(libtask, "skills");
        return libtask.getSkills();
    }


    @At("/detail/?")
    @Ok("beetl:/platform/xm/task/detail.html")
    @RequiresPermissions("platform.xm.task")
    @SLog(type = "xm", tag = "查看任务书", msg = "任务书编号：${args[0]}")
    public void detail(String xmtaskid, HttpServletRequest req) {
        if (!Strings.isBlank(xmtaskid)) {
            Cnd cnd = Cnd.NEW();
            cnd.and("id", "=", xmtaskid);
            String sysuserid = StringUtil.getPlatformUid();

            // Subject subject = SecurityUtils.getSubject();
            // if (!subject.isPermitted("platform.xm.task.manager")) {
            //     cnd.and("author", "=", sysuserid);
            // }

            xm_task xm_task = xmTaskService.fetch(cnd);
            xm_task = xmTaskService.fetchLinks(xm_task, null);

            req.setAttribute("obj", xm_task);
        } else {
            req.setAttribute("obj", null);
        }
    }


    /**
     * 查询任务书
     *
     * @param xmtaskname
     * @param req
     * @return
     */
    @At("/xmtasklist")
    @Ok("json")
    @RequiresPermissions("platform.xm.task")
    public Object xmtasklist(
            @Param("xmtaskname") String xmtaskname,
            HttpServletRequest req
    ) {
        String sysuserid = StringUtil.getPlatformUid();
        String sysuserinfid = sysUserinfService.getSysuserinfid(sysuserid);
        Cnd cnd = Cnd.where("disabled", "=", "false");
        if (Lang.isEmpty(xmtaskname)) {
            if(!shiroUtil.isSuper()){
                cnd.and("author", "=", sysuserinfid);
            }
        } else {
            cnd.and("taskname", "like", "%" + xmtaskname + "%");
        }
        List<xm_task> tasks = xmTaskService.query(cnd.desc("publishAt"));
        // Map<String, String> obj = new HashMap<>();
        // for (xm_task task : tasks) {
        //     String taskname = task.getTaskname();
        //     obj.put(taskname, task.getId());
        // }
        return tasks;
    }


    // 能否对某一任务书进行修改等操作
    private boolean isAllowForXmtask(String xmtaskid) {
        if(shiroUtil.isSuper()) return true;
        xm_task xmTask = xmTaskService.fetch(xmtaskid);
        if (!Lang.isEmpty(xmTask)) {
            return xmTask.getAuthor().equals(sysUserinfService.getSysuserinfid(StringUtil.getPlatformUid()));
        }
        return false;
    }

}