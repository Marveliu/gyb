package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.xm.modules.services.XmEvaluationService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.xm.modules.models.xm_evaluation;
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
@At("/platform/xm/evaluation")
public class XmEvaluationController{
    private static final Log log = Logs.get();
    @Inject
    private XmEvaluationService xmEvaluationService;

    @At("")
    @Ok("beetl:/platform/xm/evaluation/index.html")
    @RequiresPermissions("platform.xm.evaluation")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.evaluation")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return xmEvaluationService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/xm/evaluation/add.html")
    @RequiresPermissions("platform.xm.evaluation")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.evaluation.add")
    @SLog(tag = "xm_evaluation", msg = "${args[0].id}")
    public Object addDo(@Param("..")xm_evaluation xmEvaluation, HttpServletRequest req) {
		try {
			xmEvaluationService.insert(xmEvaluation);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/evaluation/edit.html")
    @RequiresPermissions("platform.xm.evaluation")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", xmEvaluationService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.evaluation.edit")
    @SLog(tag = "xm_evaluation", msg = "${args[0].id}")
    public Object editDo(@Param("..")xm_evaluation xmEvaluation, HttpServletRequest req) {
		try {
            xmEvaluation.setOpBy(StringUtil.getUid());
			xmEvaluation.setOpAt((int) (System.currentTimeMillis() / 1000));
			xmEvaluationService.updateIgnoreNull(xmEvaluation);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.xm.evaluation.delete")
    @SLog(tag = "xm_evaluation", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				xmEvaluationService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				xmEvaluationService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/evaluation/detail.html")
    @RequiresPermissions("platform.xm.evaluation")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", xmEvaluationService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
