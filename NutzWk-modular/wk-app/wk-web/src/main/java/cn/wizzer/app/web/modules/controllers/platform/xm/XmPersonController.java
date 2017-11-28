package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.xm_apply;
import cn.wizzer.app.xm.modules.services.XmApplyService;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.DateUtil;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@At("/platform/xm/person")
public class XmPersonController {
    private static final Log log = Logs.get();
    @Inject
    private XmApplyService xmApplyService;

    /**
    * @function: 受理用户提交任务书的申请
    * @param: 申请人编号，任务书编号
    * @return: 是否已经申请
    * @note:
    */
    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_apply", msg = "")
    public Object apply(
            @Param("xmtaskid") String xmtaskid) {
        try {
            String gyid = UserInfUtil.getCurrentGyid();
            if(null!= xmApplyService.fetch(Cnd.where("gyid","=",gyid).and("xmtaskid","=",xmtaskid)))
            {
                return Result.error("你已经申请过了！");
            }
            xm_apply apply = new xm_apply();
            apply.setXmtaskid(xmtaskid);
            apply.setGyid(gyid);
            apply.setStatus(0);
            apply.setAt(DateUtil.getTime(DateUtil.getDate()));
            xmApplyService.insert(apply);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("")
    @Ok("beetl:/platform/xm/apply/index.html")
    @RequiresPermissions("platform.xm.apply")
    public void index() {
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
