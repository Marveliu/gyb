package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.xm.modules.services.V_XmInfService;
import cn.wizzer.app.xm.modules.services.XmInfService;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
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
@At("/platform/xm/final")
public class XmFinalController {
    private static final Log log = Logs.get();

    @Inject
    private XmInfService xmInfService;

    @Inject
    private V_XmInfService v_xmInfService;

    @At("")
    @Ok("beetl:/platform//xm/final/index.html")
    @RequiresPermissions("platform.xm.final")
    public void index( HttpServletRequest req) {
        int total = 0;
        int doing = 0;
        int done = 0;
        int finish = 0;

        total =  xmInfService.count();
        doing = xmInfService.count(Cnd.where("status","=",0));
        done = xmInfService.count(Cnd.where("status","=",1));
        finish = xmInfService.count(Cnd.where("status","=",2));

        req.setAttribute("total",total);
        req.setAttribute("doing", doing);
        req.setAttribute("final", done);
        req.setAttribute("finish", finish);
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.final")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        cnd.and("status","=",1);
        return v_xmInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/final/detail.html")
    @RequiresPermissions("platform.xm.final")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", v_xmInfService.fetch(Cnd.where("id","=",id)));
        }else{
            req.setAttribute("obj", null);
        }
    }

    @At("/item/?")
    @Ok("beetl:/platform/xm/final/item.html")
    @RequiresPermissions("platform.xm.final")
    public void item(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", v_xmInfService.fetch(Cnd.where("id","=",id)));
        }else{
            req.setAttribute("obj", null);
        }
    }
}
