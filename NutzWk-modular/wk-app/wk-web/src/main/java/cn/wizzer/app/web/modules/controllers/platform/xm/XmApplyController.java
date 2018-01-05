package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.*;
import cn.wizzer.app.xm.modules.services.*;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.*;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
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
    private Dao dao;

    @At("")
    @Ok("beetl:/platform/xm/apply/index.html")
    @RequiresPermissions("platform.xm.apply")
    public void index() {
    }

    /**
     *
     * 处理项目申请
     *
     * @param id
     * @param gyid
     * @param req
     * @return
     */
    @At({"/deal"})
    @Ok("json")
    @RequiresPermissions("platform.xm.apply.deal")
    @SLog(tag = "xm_apply", msg = "受理项目申请")
    public Object deal(
            @Param("applyid") String id,
            @Param("gyid") String gyid,
            HttpServletRequest req) {
        try {
            gz_inf gz = UserInfUtil.getCurrentGz();
            xm_apply apply =  xmApplyService.fetch(id);
            xm_task task = xmTaskService.fetch(apply.getXmtaskid());
            String opBy = gz.getId();
            int opAt = (int) (System.currentTimeMillis() / 1000);

            Trans.exec(new Atom() {
                @Override
                public void run() {
                    //更新其他列表
                    dao.execute(Sqls.create("update xm_apply set status = @status,opBy = @opBy,opAt = @opAt where xmtaskid = @xmtaskid")
                            .setParam("status",2)
                            .setParam("opBy",opBy)
                            .setParam("opAt",opAt)
                            .setParam("xmtaskid",id)
                    );

                    apply.setStatus(1);
                    apply.setOpBy(opBy);
                    apply.setOpAt(opAt);
                    xmApplyService.update(apply);

                    //立项 账单
                    xm_inf xf = new  xm_inf();
                    xm_bill bill = new xm_bill();
                    xf.setGyid(gyid);
                    xf.setXmtaskid(task.getId());
                    xf.setAt(opAt);
                    xf.setOpBy(opBy);
                    xf.setStatus(0);
                    xf = xmInfService.insert(xf);

                    bill.setXminfid(xf.getId());
                    bill.setOpBy(opBy);
                    bill.setOpAt(opAt);
                    bill.setPaysum(task.getAward());
                    xmBillService.insert(bill);
                }
            });
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.apply")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
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
