package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.web.commons.services.xm.XmService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.*;
import cn.wizzer.app.xm.modules.services.*;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.ShiroUtil;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.*;
import org.nutz.dao.Chain;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@At("/platform/xm/apply")
public class XmApplyController{
    private static final Log log = Logs.get();
    @Inject
    private XmApplyService xmApplyService;
    @Inject
    private XmTaskService xmTaskService;
    @Inject
    private XmInfService xmInfService;
    @Inject
    private XmBillService xmBillService;
    @Inject
    private XmService xmService;
    @Inject
    private Dao dao;
    @Inject
    private ShiroUtil shiroUtil;

    @At("")
    @Ok("beetl:/platform/xm/apply/index.html")
    @RequiresPermissions("platform.xm.apply")
    public void index() {
    }

    /**
     *
     * 后台处理项目申请
     *
     * @param id
     * @param gyid
     * @param req
     * @return
     */
    @At({"/deal"})
    @Ok("json")
    @RequiresPermissions("platform.xm.apply.deal")
    @SLog(tag = "xm_apply", msg = "后台受理项目申请")
    public Object deal(
            @Param("applyid") String id,
            @Param("gyid") String gyid,
            HttpServletRequest req) {
        try {
            if(xmApplyService.fetch(id).getStatus() != 0){
                return Result.error("任务书已经认领");
            }
            xm_task task = xmApplyService.getTaskByAppyid(id);
            xmService.regXminf(task.getId(),gyid);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.apply")
    public Object data(
            @Param("xmtaskid") String xmtaskid,
            @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {

        Cnd cnd = Cnd.NEW();
        String sysuserid=StringUtil.getSysuserid();
        //项目总监:项目总监的权限标识为sys.allpm,超管权限标识platform.xm.task.add.allpm
        if(!shiroUtil.hasAnyPermissions("platform.xm.task.add.allpm")){
            cnd.and("author","=", sysuserid);
        }
        // xmtaskid不为空
        if( xmtaskid != null && !xmtaskid.isEmpty()){
            cnd.and("xmtaskid","=",xmtaskid);
        }
        return xmApplyService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/xm/apply/add.html")
    @RequiresPermissions("platform.xm.apply")
    public void add() {

    }


    /**
     *
     * 雇员申请项目
     *
     * @param xmApply
     * @param req
     * @return
     */
    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.apply.add")
    @SLog(tag = "xm_apply", msg = "${args[0].id}")
    public Object addDo(@Param("..")xm_apply xmApply, HttpServletRequest req) {
		try {
			xmApplyService.insert(xmApply);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/apply/edit.html")
    @RequiresPermissions("platform.xm.apply")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", xmApplyService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.apply.edit")
    @SLog(tag = "xm_apply", msg = "${args[0].id}")
    public Object editDo(@Param("..")xm_apply xmApply, HttpServletRequest req) {
		try {
            xmApply.setOpBy(StringUtil.getUid());
			xmApply.setOpAt((int) (System.currentTimeMillis() / 1000));
			xmApplyService.updateIgnoreNull(xmApply);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.xm.apply.delete")
    @SLog(tag = "xm_apply", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
            //查询该Apply是否还有申请
            String xm_task_id=xmApplyService.fetch(id).getXmtaskid();
            //如果没有申请了,就更新状态码为1即任务书申请状态
            List<xm_apply> XmApplies=xmApplyService.query(Cnd.where("xmtaskid", "=", xm_task_id));
            if(XmApplies.size()==1)
                dao.update(xm_task.class,Chain.make("status",1),Cnd.where("id","=",xm_task_id));
            if(ids!=null&&ids.length>0){
				xmApplyService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				xmApplyService.delete(id);
    			req.setAttribute("id", id);
			}
			return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/apply/detail.html")
    @RequiresPermissions("platform.xm.apply")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            xm_apply apply = xmApplyService.fetch(id);
            req.setAttribute("obj", xmApplyService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
