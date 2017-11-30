package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.xm.modules.services.XmFeedbackService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.xm.modules.models.xm_feedback;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
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
@At("/platform/xm/feedback")
public class XmFeedbackController{
    private static final Log log = Logs.get();
    @Inject
    private XmFeedbackService xmFeedbackService;

    @At("")
    @Ok("beetl:/platform/xm/feedback/index.html")
    @RequiresPermissions("platform.xm.feedback")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return xmFeedbackService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/xm/feedback/add.html")
    @RequiresPermissions("platform.xm.feedback")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.add")
    @SLog(tag = "xm_feedback", msg = "${args[0].id}")
    public Object addDo(@Param("..")xm_feedback xmFeedback, HttpServletRequest req) {
		try {
			xmFeedbackService.insert(xmFeedback);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/feedback/edit.html")
    @RequiresPermissions("platform.xm.feedback")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", xmFeedbackService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.edit")
    @SLog(tag = "xm_feedback", msg = "${args[0].id}")
    public Object editDo(@Param("..")xm_feedback xmFeedback, HttpServletRequest req) {
		try {
            xmFeedback.setOpBy(StringUtil.getUid());
			xmFeedback.setOpAt((int) (System.currentTimeMillis() / 1000));
			xmFeedbackService.updateIgnoreNull(xmFeedback);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.delete")
    @SLog(tag = "xm_feedback", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				xmFeedbackService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				xmFeedbackService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/feedback/detail.html")
    @RequiresPermissions("platform.xm.feedback")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", xmFeedbackService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
