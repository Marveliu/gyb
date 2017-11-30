package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.gy.modules.XmInfService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.xm.modules.models.xm_inf;
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
@At("/platform/xm/inf")
public class XmInfController{
    private static final Log log = Logs.get();
    @Inject
    private XmInfService xmInfService;

    @At("")
    @Ok("beetl:/platform/code/inf/index.html")
    @RequiresPermissions("platform.code.inf")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.code.inf")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return xmInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/code/inf/add.html")
    @RequiresPermissions("platform.code.inf")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.code.inf.add")
    @SLog(tag = "xm_inf", msg = "${args[0].id}")
    public Object addDo(@Param("..")xm_inf xmInf, HttpServletRequest req) {
		try {
			xmInfService.insert(xmInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/code/inf/edit.html")
    @RequiresPermissions("platform.code.inf")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", xmInfService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.inf.edit")
    @SLog(tag = "xm_inf", msg = "${args[0].id}")
    public Object editDo(@Param("..")xm_inf xmInf, HttpServletRequest req) {
		try {
            xmInf.setOpBy(StringUtil.getUid());
			xmInf.setOpAt((int) (System.currentTimeMillis() / 1000));
			xmInfService.updateIgnoreNull(xmInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.code.inf.delete")
    @SLog(tag = "xm_inf", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				xmInfService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				xmInfService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/code/inf/detail.html")
    @RequiresPermissions("platform.code.inf")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", xmInfService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
