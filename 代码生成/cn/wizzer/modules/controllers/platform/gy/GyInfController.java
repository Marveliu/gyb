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

import cn.wizzer.modules.models.gy.Gy_inf;
import cn.wizzer.modules.services.gy.GyInfService;

/**
 * @author Wizzer.cn
 * @time 2017-11-09 16:40:06
 * 
 */
@IocBean
@At("/platform/gy/gyinf")
@Filters({ @By(type = PrivateFilter.class)})
public class GyInfController {
private static final Log log = Logs.get();
	@Inject
	GyInfService gyInfService;

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
	@SLog(tag = "Add", msg = "Add:gy_inf")
	public Object addDo(@Param("..") Gy_inf gy_inf, HttpServletRequest req) {
		try {
			gyInfService.insert(gy_inf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
	
	@At("/edit/?")
	@Ok("beetl:/platform/gy/edit.html")
	@RequiresAuthentication
	public Object edit(long id) {
		return gyInfService.fetch(id);
	}
	
	@At
	@Ok("json")
	@RequiresPermissions("platform.gy.edit")
	@SLog(tag = "Edit", msg = "Edit:gy_inf")
	public Object editDo(@Param("..") Gy_inf gy_inf, HttpServletRequest req) {
		try {
			gyInfService.updateIgnoreNull(gy_inf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
	
	@At({"/delete","/delete/?"})
	@Ok("json")
	@RequiresPermissions("platform.gy.delete")
	@SLog(tag = "Delete", msg = "Delete:gy_inf")
	public Object delete(long id,@Param("ids") long[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gyInfService.delete(ids);
			}else{
				gyInfService.delete(id);
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
		return gyInfService.data(length, start, draw, order, columns, cnd, null);
	}

}