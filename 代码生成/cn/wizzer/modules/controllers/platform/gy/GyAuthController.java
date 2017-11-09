package cn.wizzer.modules.controllers.platform.gy;

import cn.wizzer.common.annotation.SLog;
import cn.wizzer.common.base.Result;
import cn.wizzer.common.filter.PrivateFilter;
import cn.wizzer.common.page.DataTableColumn;
import cn.wizzer.common.page.DataTableOrder;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import cn.wizzer.modules.models.gy.Gy_auth;
import cn.wizzer.modules.services.gy.GyAuthService;

/**
 * @author Wizzer.cn
 * @time 2017-11-09 16:40:06
 * 
 */
@IocBean
@At("/platform/gy/gyauth")
@Filters({ @By(type = PrivateFilter.class)})
public class GyAuthController {
private static final Log log = Logs.get();
	@Inject
	GyAuthService gyAuthService;

	@At("")
	@Ok("beetl:/platform/gy/index.html")
	@RequiresAuthentication
	public void index() {
		
	}
	
	@At
	@Ok("beetl:/platform/gy/add.html")
	@RequiresAuthentication
	public void add() {
	
	}
	
	@At
	@Ok("json")
	@RequiresPermissions("platform.gy.add")
	@SLog(tag = "Add", msg = "Add:gy_auth")
	public Object addDo(@Param("..") Gy_auth gy_auth, HttpServletRequest req) {
		try {
			gyAuthService.insert(gy_auth);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
	
	@At("/edit/?")
	@Ok("beetl:/platform/gy/edit.html")
	@RequiresAuthentication
	public Object edit(String id) {
		return gyAuthService.fetch(id);
	}
	
	@At
	@Ok("json")
	@RequiresPermissions("platform.gy.edit")
	@SLog(tag = "Edit", msg = "Edit:gy_auth")
	public Object editDo(@Param("..") Gy_auth gy_auth, HttpServletRequest req) {
		try {
			gyAuthService.updateIgnoreNull(gy_auth);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
	
	@At({"/delete","/delete/?"})
	@Ok("json")
	@RequiresPermissions("platform.gy.delete")
	@SLog(tag = "Delete", msg = "Delete:gy_auth")
	public Object delete(String id,@Param("ids") String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gyAuthService.delete(ids);
			}else{
				gyAuthService.delete(id);
			}
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
	
	@At
	@Ok("json:full")
	@RequiresAuthentication
	public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
		return gyAuthService.data(length, start, draw, order, columns, cnd, null);
	}

}