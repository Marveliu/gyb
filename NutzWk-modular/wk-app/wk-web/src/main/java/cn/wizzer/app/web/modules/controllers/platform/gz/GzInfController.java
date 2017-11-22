package cn.wizzer.app.web.modules.controllers.platform.gz;

import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.gz.modules.services.GzInfService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@At("/platform/gz/inf")
public class GzInfController{
    private static final Log log = Logs.get();
    @Inject
    private GzInfService gzInfService;

    @At("")
    @Ok("beetl:/platform/gz/inf/index.html")
    @RequiresPermissions("platform.gz.inf")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gz.inf")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return gzInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/gz/inf/add.html")
    @RequiresPermissions("platform.gz.inf")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.gz.inf.add")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object addDo(@Param("..")gz_inf gzInf, HttpServletRequest req) {
		try {
			gzInfService.insert(gzInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/gz/inf/edit.html")
    @RequiresPermissions("platform.gz.inf")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", gzInfService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.gz.inf.edit")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object editDo(@Param("..")gz_inf gzInf, HttpServletRequest req) {
		try {
            gzInf.setOpBy(StringUtil.getUid());
			gzInf.setOpAt((int) (System.currentTimeMillis() / 1000));
			gzInfService.updateIgnoreNull(gzInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.gz.inf.delete")
    @SLog(tag = "gz_inf", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gzInfService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				gzInfService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/gz/inf/detail.html")
    @RequiresPermissions("platform.gz.inf")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", gzInfService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
