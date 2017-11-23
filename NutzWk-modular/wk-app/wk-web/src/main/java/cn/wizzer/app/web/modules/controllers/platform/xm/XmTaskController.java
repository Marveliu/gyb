package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.gz.modules.services.GzInfService;
import cn.wizzer.app.library.modules.models.lib_task;
import cn.wizzer.app.library.modules.services.LibTaskService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.xm_limit;
import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.app.xm.modules.services.XmTaskService;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.wizzer.app.web.commons.util.UserInfUtil.getCurrentGz;

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
    private GzInfService gzInfService;

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
        gz_inf gz= getCurrentGz();

        //todo:需要判定是否为产品经理级别及其以上，用shiro有没有啥字段
        cnd.and("author","=",gz.getId());

        if (!Strings.isBlank(libtaskid) && !"0".equals(libtaskid)) {
            cnd.and("category", "like", "%" + libtaskid + "%");
        }

        if (!Strings.isBlank(title)) {
            cnd.and("title", "like", "%" + title + "%");
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
            @Param("at") String at,
            HttpServletRequest req) {
        try {

            //获取当前用户信息
            gz_inf gz = UserInfUtil.getCurrentGz();



            //设置时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int publishAt = (int) (sdf.parse(at).getTime() / 1000);
            int firstcommitAt = (int) (sdf.parse(firstcommit).getTime() / 1000);
            int endtimeAt = (int) (sdf.parse(endtime).getTime() / 1000);
            xmtask.setPublishAt(publishAt);
            xmtask.setFirstcommit(firstcommitAt);
            xmtask.setEndtime(endtimeAt);

            //初始编辑者和阅读数量
            xmtask.setAuthor(gz.getId());
            xmtask.setReadnum(0);

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
        xm_task task = xmTaskService.fetchLinks(xmTaskService.fetchx(id),"xmlimits");
        return task;
    }

    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.task.edit")
    @SLog(tag = "修改任务书", msg = "任务书标题:${args[0].title}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(@Param("..") xm_task task, @Param("limits") List<xm_limit> limits,@Param("at") String at, HttpServletRequest req) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int publishAt = (int) (sdf.parse(at).getTime() / 1000);
            task.setPublishAt(publishAt);

            //修改技能限制信息
            task.setXmlimits(limits);

            //更新
            xmTaskService.updateWith(task,"xmlimits");

            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/enable/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.task.edit")
    @SLog(tag = "发布任务书", msg = "任务书标题:${args[1].getAttribute('title')}")
    public Object enable(String id, HttpServletRequest req) {
        try {
            req.setAttribute("title", xmTaskService.fetch(id).getTaskname());
            xmTaskService.update(org.nutz.dao.Chain.make("disabled", false), Cnd.where("id", "=", id));
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.task")
    @SLog(tag = "取消发布任务书", msg = "任务书标题:${args[1].getAttribute('title')}")
    public Object disable(String id, HttpServletRequest req) {
        try {
            req.setAttribute("title", xmTaskService.fetch(id).getTaskname());
            xmTaskService.update(org.nutz.dao.Chain.make("disabled", true), Cnd.where("id", "=", id));
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



}
