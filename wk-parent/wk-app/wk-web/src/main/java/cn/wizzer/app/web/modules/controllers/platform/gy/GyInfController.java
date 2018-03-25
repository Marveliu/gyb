package cn.wizzer.app.web.modules.controllers.platform.gy;

import cn.wizzer.app.gy.modules.models.gy_inf;
import cn.wizzer.app.gy.modules.models.v_gy;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.gy.modules.services.VGyService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.services.gy.GyService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@IocBean
@At("/platform/gy/inf")
public class GyInfController{
    private static final Log log = Logs.get();

    @Inject
    private GyInfService gyInfService;
    @Inject
    private VGyService vGyService;
    @Inject
    private GyService gyService;
    @Inject
    private SysUserService userService;

    @At("")
    @Ok("beetl:/platform/gy/inf/index.html")
    @RequiresPermissions("platform.gy.inf")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
    	return vGyService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/gy/inf/add.html")
    @RequiresPermissions("platform.gy.inf")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.add")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object addDo(@Param("..")gy_inf gyInf, HttpServletRequest req) {
		try {
			gyInfService.insert(gyInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/gy/inf/edit.html")
    @RequiresPermissions("platform.gy.inf")
    public void edit(String id,HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        Object test = gyInfService.fetch(cnd.and("gyid","=",id));
		req.setAttribute("obj", test);
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.edit")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object editDo(
            @Param("..") gy_inf gyInf,
            @Param("birthdayat") String birthday,
            @Param("regYearat") String regyear,
      HttpServletRequest req) {
        try {
            String userid = gyInf.getUserid();
            Sys_user user = userService.fetch(userid);
            //修改邮箱
            user.setEmail(gyInf.getEmail());
            user.setOpBy(StringUtil.getUid());
            user.setOpAt((int) (System.currentTimeMillis() / 1000));
            userService.updateIgnoreNull(user);

            //修改雇员信息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            int regyearat = (int) (sdf.parse(regyear).getTime() / 1000);
            gyInf.setRegYear(regyearat);
            gyInf.setRegYear(regyearat);
            gyInf.setOpBy(StringUtil.getUid());
            gyInf.setOpAt((int) (System.currentTimeMillis() / 1000));
            gyInfService.updateIgnoreNull(gyInf);
            return Result.success("system.success");

        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.delete")
    @SLog(tag = "gz_inf", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gyInfService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				gyInfService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/gy/inf/detail.html")
    @RequiresPermissions("platform.gy.inf")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            Cnd cnd = Cnd.NEW();
            v_gy v_gy = vGyService.fetch(cnd.and("gyid","=",id));
            req.setAttribute("obj", v_gy);
		}else{
            req.setAttribute("obj", null);
        }
    }


    @At("/setGy4/?")
    @Ok("json")
    @RequiresPermissions("gy.inf.set_offical")
    @SLog(tag = "通过正式雇员", msg = "${req.getAttribute('id')}")
    public Object setGy4(String id, HttpServletRequest req) {
        try {
                if(gyService.updateGyRole(id,"gy4")){
                    req.setAttribute("id", id);
                    //todo: 当前status仅仅是作为标记
                    gyInfService.update(Chain.make("status",1),Cnd.where("userid","=",id));
                    return Result.success("system.success");
                }else {
                    return Result.error("system.error");
                }
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }



}
