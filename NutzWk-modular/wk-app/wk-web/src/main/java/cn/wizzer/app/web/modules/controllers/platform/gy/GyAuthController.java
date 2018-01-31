package cn.wizzer.app.web.modules.controllers.platform.gy;

import cn.wizzer.app.gy.modules.models.gy_auth;
import cn.wizzer.app.gy.modules.services.GyAuthService;
import cn.wizzer.app.gy.modules.services.VGyService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.StatusCodeUtil;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@At("/platform/gy/auth")
public class GyAuthController{
    private static final Log log = Logs.get();
    @Inject
    private GyAuthService gyAuthService;

    @Inject
    private VGyService vGyService;

    @At("")
    @Ok("beetl:/platform/gy/auth/index.html")
    @RequiresPermissions("platform.gy.auth")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gy.auth")
    public Object data(
            @Param("gyid") String gyid, @Param("realname") String realname, @Param("status") String status,
            @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
        if("3".equals(status)){
            return vGyService.data(length, start, draw, order, columns, cnd, null);
        }
        if (!Strings.isBlank(gyid))
            cnd.and("gyid", "=", gyid);
        if (!Strings.isBlank(realname))
            cnd.and("realname", "like", "%" + realname + "%");
        if (!Strings.isBlank(status))
            cnd.and("status", "=", StatusCodeUtil.bind("gyauth"+status) );
    	return vGyService.data(length, start, draw, order, columns, cnd, null);
    }



    @At("/add")
    @Ok("beetl:/platform/gy/auth/add.html")
    @RequiresPermissions("platform.gy.auth")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.auth.add")
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
    @Ok("beetl:/platform/gy/auth/edit.html")
    @RequiresPermissions("platform.gy.auth")
    public void edit(String id,HttpServletRequest req) {
		Object test =gyAuthService.fetch(id);
        req.setAttribute("obj", gyAuthService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.auth.edit")
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
    @RequiresPermissions("platform.gy.auth.delete")
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
    @Ok("beetl:/platform/gy/auth/detail.html")
    @RequiresPermissions("platform.gy.auth")
	public void detail(String id, HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        cnd.and("gyid","=",id);
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", vGyService.fetch(cnd));
		}else{
            req.setAttribute("obj", null);
        }
    }

    @At("/enable")
    @Ok("json")
    @RequiresPermissions("platform.gy.auth")
    @SLog(tag = "认证通过", msg = "雇员编号")
    public Object enable(
            @Param("gyid") String id,
            @Param("note") String note,
            HttpServletRequest req) {
        try {
            if(null == note || note.isEmpty()){
                note = "恭喜你审核通过！";
            }
            gyAuthService.enable(id,note);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable")
    @Ok("json")
    @RequiresPermissions("platform.gy.auth")
    @SLog(tag = "认证不通过", msg = "雇员编号:${args[1].getAttribute('gyid')}")
    public Object disable(
            @Param("gyid") String id,
            @Param("note") String note,
            HttpServletRequest req) {
        try {
            if(null == note || note.isEmpty()){
                note = "您的身份信息有误，不一致请注意审核！";
            }
            gyAuthService.disable(id,note);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

}
