package cn.wizzer.app.web.modules.controllers.platform.hi;

import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.hi.modules.models.gy_auth;
import cn.wizzer.app.hi.modules.services.GyAuthService;
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
@At("/platform/hi/auth")
public class GyAuthController{
    private static final Log log = Logs.get();
    @Inject
    private GyAuthService gyAuthService;

    @At("")
    @Ok("beetl:/platform/hi/auth/index.html")
    @RequiresPermissions("platform.hi.auth")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.hi.auth")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return gyAuthService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/hi/auth/add.html")
    @RequiresPermissions("platform.hi.auth")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.hi.auth.add")
    @SLog(tag = "gy_auth", msg = "${args[0].id}")
    public Object addDo(@Param("..")gy_auth gyAuth, HttpServletRequest req) {
		try {
			gyAuthService.insert(gyAuth);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/hi/auth/edit.html")
    @RequiresPermissions("platform.hi.auth")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", gyAuthService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.hi.auth.edit")
    @SLog(tag = "gy_auth", msg = "${args[0].id}")
    public Object editDo(@Param("..")gy_auth gyAuth, HttpServletRequest req) {
		try {
            gyAuth.setOpBy(StringUtil.getUid());
			gyAuth.setOpAt((int) (System.currentTimeMillis() / 1000));
			gyAuthService.updateIgnoreNull(gyAuth);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.hi.auth.delete")
    @SLog(tag = "gy_auth", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gyAuthService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				gyAuthService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/hi/auth/detail.html")
    @RequiresPermissions("platform.hi.auth")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", gyAuthService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
