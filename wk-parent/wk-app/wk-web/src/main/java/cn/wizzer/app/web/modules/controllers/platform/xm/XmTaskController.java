package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.gy.modules.models.v_gy;
import cn.wizzer.app.library.modules.models.lib_task;
import cn.wizzer.app.library.modules.services.LibSkillService;
import cn.wizzer.app.library.modules.services.LibTaskService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.xm.modules.models.v_xminf;
import cn.wizzer.app.xm.modules.models.xm_limit;
import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.app.xm.modules.services.XmTaskService;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.ShiroUtil;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by wizzer on 2016/6/28.
 */
@IocBean
@At("/platform/xm/task")
public class XmTaskController {
    private static final Log log = Logs.get();
    @Inject
    private XmTaskService xmTaskService;
    @Inject
    private LibTaskService libTaskService;
    @Inject
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

    /**
     * @function: 任务书列表
     * @param: 任务书所属项目类别，任务书题目
     * @return:
     * @note:
     */
    @At
    @Ok("json:full")
    @RequiresPermissions("platform.xm.task")
    public Object data(@Param("libtaskId") String libtaskid,@Param("title") String title, @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {

        Cnd cnd = Cnd.NEW();
        // 超级管理员
        if(!shiroUtil.hasAnyPermissions("platform.xm.task.add.manager")){
            cnd.and("author","=", StringUtil.getSysuserid());
        }
        String sysuserid=StringUtil.getSysuserid();
        if(!sysuserid.equals("gyb201800")){
            cnd.and("author","=", sysuserid);
        }
        if (!Strings.isBlank(libtaskid) && !"0".equals(libtaskid)) {
            cnd.and("category", "like", "%" + libtaskid + "%");
        }
        if (!Strings.isBlank(title)) {
            cnd.and("taskname", "like", "%" + title + "%");
        }
        return xmTaskService.data(length, start, draw, order, columns, cnd, null);
    }


    //跳转到任务书添加界面
    @At
    @Ok("beetl:/platform/xm/task/add.html")
    @RequiresPermissions("platform.xm.task")
    public void add(@Param("libtaskId") String libtaskid,HttpServletRequest req) {
        req.setAttribute("libtask", libtaskid != null && !"0".equals(libtaskid) ? libTaskService.fetch(libtaskid) : null);
    }


    /**
     * @function: 添加任务书
     * @param: xm_task,xm_limit
     * @return:
     * @note:
     */
    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.task.add")
    @SLog(tag = "添加任务书", msg = "任务书标题:${args[0].taskname}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(
            @Param("::") xm_task xmtask,
            @Param("firstcommitat") String firstcommit,
            @Param("endtimeat") String endtime,
            @Param("applyendtimeat") String applyendtime,
            @Param("at") String at,
            HttpServletRequest req) {
        try {

            //获取当前用户信息
            String sysuserid = StringUtil.getSysuserid();

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
            xmtask.setAuthor(sysuserid);
            xmtask.setReadnum(0);
            xmtask.setDisabled(true);
            //插入任务书，和任务书的技能要求
            xmTaskService.insertWith(xmtask,"xmlimits");

            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/task/edit.html")
    @RequiresPermissions("platform.xm.task")
    public Object edit(String id, HttpServletRequest req) {
        xm_task task = xmTaskService.fetchLinks(xmTaskService.fetch(id),null);
        req.setAttribute("libtask", task.getLibtask());
        List<xm_limit>  limits = task.getXmlimits();
        List<xm_limit>  xmlimits = new ArrayList<>();
        for( xm_limit limit :limits){
            limit = libSkillService.fetchLinks(limit,"skill");
            xmlimits.add(limit);
        }
        req.setAttribute("xmlimits", xmlimits);
        return task;
    }

    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.task.edit")
    @SLog(tag = "修改任务书", msg = "任务书标题:${args[0].taskname}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(
            @Param("::") xm_task xmtask,
            @Param("firstcommitat") String firstcommit,
            @Param("endtimeat") String endtime,
            @Param("applyendtimeat") String applyendtime,
            @Param("at") String at,
            HttpServletRequest req) {
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


            Trans.exec(new Atom() {
                @Override
                public void run() {
                    //清空之前的技能限制
                    xm_task oldxmtask = xmTaskService.fetchLinks(xmTaskService.fetch(xmtask.getId()),null);
                    Dao dao = Mvcs.getIoc().get(Dao.class);
                    dao.deleteLinks(oldxmtask,"xmlimits");
                    xmTaskService.insertLinks(xmtask,"xmlimits");
                    //更新
                    xmTaskService.updateIgnoreNull(xmtask);
                }
            });


            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/enable/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.task.add.manager")
    @SLog(tag = "发布任务书", msg = "任务书标题:${args[1].getAttribute('title')}")
    public Object enable(String id, HttpServletRequest req) {
        try {
            // TODO: 2018/1/11 0011 新的任务书发布，并且附上链接进行推送
            req.setAttribute("title", xmTaskService.fetch(id).getTaskname());
            xmTaskService.update(org.nutz.dao.Chain.make("disabled", false).add("status","1"), Cnd.where("id", "=", id));
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.task.add.manager")
    @SLog(tag = "取消发布任务书", msg = "任务书标题:${args[1].getAttribute('title')}")
    public Object disable(String id, HttpServletRequest req) {
        try {
            req.setAttribute("title", xmTaskService.fetch(id).getTaskname());
            xmTaskService.update(org.nutz.dao.Chain.make("disabled", true).add("status","0"), Cnd.where("id", "=", id));
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.xm.task.delete")
    @SLog(tag = "删除任务书", msg = "ID:${args[2].getAttribute('id')}")
    public Object delete(String oneId, @Param("ids") String[] ids, HttpServletRequest req) {
        try {
            if (ids != null && ids.length > 0) {
                //todo:采取事务，同时也要删除对应的技能限制信息
                xmTaskService.delete(ids);
                req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
            } else {
                xmTaskService.delete(oneId);
                req.setAttribute("id", oneId);
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
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
    public Object limitdata(@Param("libtaskid") String libtaskid){
        lib_task libtask = libTaskService.fetch(libtaskid);
        libtask = libTaskService.fetchLinks(libtask,"skills");
        return libtask.getSkills();
    }


    @At("/detail/?")
    @Ok("beetl:/platform/gy/inf/detail.html")
    @RequiresPermissions("platform.xm.task")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            Cnd cnd = Cnd.NEW();
            cnd.and("id","=",id);
            String sysuserid = StringUtil.getSysuserid();
            Subject subject = SecurityUtils.getSubject();
            if(!subject.isPermitted("platform.xm.task.manager")){
                cnd.and("author","=",sysuserid);
            }
            xm_task xm_task = xmTaskService.fetch(cnd);
            req.setAttribute("obj", xm_task);
        }else{
            req.setAttribute("obj", null);
        }
    }


    /**
     * @function: 查询当前雇员经理负责的所有项目
     * @param:
     * @return:
     * @note:
     */
    @At("/xmtasklist")
    @Ok("json")
    @RequiresPermissions("platform.xm.task")
    public Object xmtasklist(
            @Param("xmtaskname") String xmtaskname,
            HttpServletRequest req
    ){
        String sysuserid = StringUtil.getSysuserid();

        Cnd cnd = Cnd.NEW();

        if(xmtaskname == null || xmtaskname.isEmpty()){
            cnd.and("author","=",sysuserid);
            cnd.and("disabled","=","false");
        }else {
            cnd.and("taskname","like","%"+xmtaskname+"%");
        }

        List<xm_task> tasks = xmTaskService.query(cnd);
        Map<String, String> obj = new HashMap<>();
        for(xm_task task:tasks){
            String taskname = task.getTaskname();
            obj.put(taskname,task.getId());
        }
        return obj;
    }





}
