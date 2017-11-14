package cn.wizzer.app.web.modules.controllers.platform.gy;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 89792 on 2017/11/13 0013.
 */
@IocBean
@At("/platform/gy/person/")
public class GyPersonController {

    //todo:RequiresPermissions没有处理

    private static final Log log = Logs.get();
    @Inject
    private GyInfService gyInfService;


    @At("/inf")
    @Ok("beetl:/platform/gy/person/infedit.html")
    @RequiresPermissions("gy.person")
    public void inf(HttpServletRequest req) {
        Subject currentUser = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) currentUser.getPrincipal();
        req.setAttribute("obj", gyInfService.fetch(user.getId()));
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("gy.person")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        return gyInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/infeditDo")
    @Ok("json")
    @RequiresPermissions("gy.person")
    @SLog(tag = "gy_inf", msg = "${args[0].id}")
    public Object infeditDo(@Param("..")gy_inf gyInf, HttpServletRequest req) {
        try {
            gyInf.setOpBy(StringUtil.getUid());
            gyInf.setOpAt((int) (System.currentTimeMillis() / 1000));
            gyInfService.updateIgnoreNull(gyInf);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }





}
