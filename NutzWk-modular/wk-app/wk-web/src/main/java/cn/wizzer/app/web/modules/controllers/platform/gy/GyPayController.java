package cn.wizzer.app.web.modules.controllers.platform.gy;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.services.GyPayService;
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
@At("/platform/gy/pay")
public class GyPayController{
    private static final Log log = Logs.get();
    @Inject
    private GyPayService gyPayService;

    @At("")
    @Ok("beetl:/platform/gy/pay/index.html")
    @RequiresPermissions("platform.gy.pay")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gy.pay")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return gyPayService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/gy/pay/add.html")
    @RequiresPermissions("platform.gy.pay")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.pay.add")
    @SLog(tag = "gy_pay", msg = "${args[0].id}")
    public Object addDo(@Param("src/test")gy_pay gyPay, HttpServletRequest req) {
		try {
			gyPayService.insert(gyPay);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/gy/pay/edit.html")
    @RequiresPermissions("platform.gy.pay")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", gyPayService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.pay.edit")
    @SLog(tag = "gy_pay", msg = "${args[0].id}")
    public Object editDo(@Param("src/test")gy_pay gyPay, HttpServletRequest req) {
		try {
            gyPay.setOpBy(StringUtil.getUid());
			gyPay.setOpAt((int) (System.currentTimeMillis() / 1000));
			gyPayService.updateIgnoreNull(gyPay);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.gy.pay.delete")
    @SLog(tag = "gy_pay", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gyPayService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				gyPayService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/gy/pay/detail.html")
    @RequiresPermissions("platform.gy.pay")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", gyPayService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }

}
