package cn.wizzer.app.web.modules.controllers.platform.gy;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
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
@At("/platform/gy/inf")
public class GyInfController{
    private static final Log log = Logs.get();
    @Inject
    private GyInfService gyInfService;

    @At("")
    @Ok("beetl:/platform/gy/inf/index.html")
    @RequiresPermissions("platform.gy.inf")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return gyInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/gy/inf/add.html")
    @RequiresPermissions("platform.gy.inf")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.add")
    @SLog(tag = "gy_inf", msg = "${args[0].id}")
    public Object addDo(@Param("..")gy_inf gyInf, HttpServletRequest req) {
		try {
			gyInfService.insert(gyInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/gy/inf/edit.html")
    @RequiresPermissions("platform.gy.inf")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", gyInfService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.edit")
    @SLog(tag = "gy_inf", msg = "${args[0].id}")
    public Object editDo(@Param("..")gy_inf gyInf, HttpServletRequest req) {
		try {
            gyInf.setOpBy(StringUtil.getUid());
			gyInf.setOpAt((int) (System.currentTimeMillis() / 1000));
			gyInfService.updateIgnoreNull(gyInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.delete")
    @SLog(tag = "gy_inf", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gyInfService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				gyInfService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/gy/inf/detail.html")
    @RequiresPermissions("platform.gy.inf")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", gyInfService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
