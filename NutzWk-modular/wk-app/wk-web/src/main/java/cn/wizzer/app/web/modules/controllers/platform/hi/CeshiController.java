package cn.wizzer.app.web.modules.controllers.platform.hi;

import cn.wizzer.framework.base.Result;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import cn.wizzer.app.hi.modules.models.ceshi;
import cn.wizzer.app.hi.modules.services.CeshiService;
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
@At("/platform/hi/country")
public class CeshiController{
    private static final Log log = Logs.get();
    @Inject
    private CeshiService dicCountryService;

    @At("")
    @Ok("beetl:/platform/hi/country/index.html")
    @RequiresPermissions("platform.hi.country")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.hi.country")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return dicCountryService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/hi/country/add.html")
    @RequiresPermissions("platform.hi.country")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.hi.country.add")
    @SLog(tag = "国家", msg = "${args[0].id}")
    public Object addDo(@Param("..")ceshi dicCountry, HttpServletRequest req) {
		try {
			dicCountryService.insert(dicCountry);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/hi/country/edit.html")
    @RequiresPermissions("platform.hi.country")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", dicCountryService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.hi.country.edit")
    @SLog(tag = "国家", msg = "${args[0].id}")
    public Object editDo(@Param("..")ceshi dicCountry, HttpServletRequest req) {
		try {
            dicCountry.setOpBy(StringUtil.getUid());
			dicCountry.setOpAt((int) (System.currentTimeMillis() / 1000));
			dicCountryService.updateIgnoreNull(dicCountry);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.hi.country.delete")
    @SLog(tag = "国家", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				dicCountryService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				dicCountryService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/hi/country/detail.html")
    @RequiresPermissions("platform.hi.country")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", dicCountryService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
