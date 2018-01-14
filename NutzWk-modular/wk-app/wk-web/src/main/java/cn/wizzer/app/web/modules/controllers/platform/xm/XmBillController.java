package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.xm.modules.services.XmBillService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.xm.modules.models.xm_bill;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.*;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@At("/platform/xm/bill")
public class XmBillController{
    private static final Log log = Logs.get();
    @Inject
    private XmBillService xmBillService;

    @At("")
    @Ok("beetl:/platform/xm/bill/index.html")
    @RequiresPermissions("platform.xm.bill")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.bill")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
        // cnd.and("status","=",2);
    	return xmBillService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/xm/bill/add.html")
    @RequiresPermissions("platform.xm.bill")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.bill.add")
    @SLog(tag = "xm_bill", msg = "${args[0].id}")
    public Object addDo(@Param("..")xm_bill xmBill, HttpServletRequest req) {
		try {
			xmBillService.insert(xmBill);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/bill/edit.html")
    @RequiresPermissions("platform.xm.bill")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", xmBillService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.bill.edit")
    @SLog(tag = "xm_bill", msg = "${args[0].id}")
    public Object editDo(@Param("..")xm_bill xmBill, HttpServletRequest req) {
		try {
            xmBill.setOpBy(StringUtil.getUid());
			xmBill.setOpAt((int) (System.currentTimeMillis() / 1000));
			xmBillService.updateIgnoreNull(xmBill);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.xm.bill.delete")
    @SLog(tag = "xm_bill", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				xmBillService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				xmBillService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/bill/detail.html")
    @RequiresPermissions("platform.xm.bill")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
		    xm_bill bill = xmBillService.fetch(id);
		    bill = xmBillService.fetchLinks(bill,"realgypay");
            req.setAttribute("obj", bill);
		}else{
            req.setAttribute("obj", null);
        }
    }

    @At("/check/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.bill")
    @SLog(tag = "xm_bill", msg = "${req.getAttribute('id')}")
    public Object check(String id, HttpServletRequest req) {
        try {
            String sysuerid = StringUtil.getSysuserid();
            if(!Strings.isBlank(id)){
                int flag = xmBillService.update(
                        org.nutz.dao.Chain.make("status",3)
                                .add("payby",sysuerid)
                                .add("at",(int) (System.currentTimeMillis() / 1000)),
                        Cnd.where("id","=",id));
                if(flag!=0)
                {
                    req.setAttribute("id",id);
                    return Result.success("system.success");
                }
            }
            return Result.error("system.error");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }



}
