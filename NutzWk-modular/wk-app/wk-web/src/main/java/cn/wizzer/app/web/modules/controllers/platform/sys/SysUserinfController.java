package cn.wizzer.app.web.modules.controllers.platform.sys;

import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.gz.modules.services.GzInfService;
import cn.wizzer.app.sys.modules.models.Sys_userinf;
import cn.wizzer.app.sys.modules.services.SysRoleService;
import cn.wizzer.app.sys.modules.services.SysUserinfService;
import cn.wizzer.app.sys.modules.services.VSysuserService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@IocBean
@At("/platform/sys/userinf")
public class SysUserinfController {
    private static final Log log = Logs.get();
    @Inject
    private SysUserinfService sysUserinfService;

    @Inject
    private SysRoleService roleService;


    @Inject
    private VSysuserService vSysuserService;


    @At("")
    @Ok("beetl:/platform/sys/userinf/index.html")
    @RequiresPermissions("platform.sys.userinf.list")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.list")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
        Object test = vSysuserService.data(length, start, draw, order, columns, cnd, null);
    	return vSysuserService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/sys/userinf/add.html")
    @RequiresPermissions("platform.sys.userinf.add")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.add")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object addDo(
            @Param("..")Sys_userinf userinf,
            @Param("birthdayat") String birthday, HttpServletRequest req) {
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            userinf.setBirthday(birthdayat);
            sysUserinfService.insert(userinf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }


    @At("/person")
    @Ok("beetl:/platform/sys/userinf/person.html")
    @RequiresPermissions("platform.sys.userinf.person")
    public void person(String id,HttpServletRequest req) {
        String userid =  StringUtil.getSysuserid();
        req.setAttribute("obj", sysUserinfService.fetch(userid));
    }


    @At("/personeditDo")
    @Ok("beetl:/platform/sys/userinf/person.html")
    @RequiresPermissions("platform.sys.userinf.person")
    public Object personeditDo(@Param("..")Sys_userinf userinf, @Param("birthdayat") String birthday, HttpServletRequest req) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            userinf.setBirthday(birthdayat);
            userinf.setOpBy(StringUtil.getUid());
            userinf.setOpAt((int) (System.currentTimeMillis() / 1000));
            sysUserinfService.updateIgnoreNull(userinf);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/edit/?")
    @Ok("beetl:/platform/sys/userinf/edit.html")
    @RequiresPermissions("platform.sys.userinf.edit")
    public void edit(String id,HttpServletRequest req) {
        req.setAttribute("obj", sysUserinfService.fetch(id));

    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.edit")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object editDo(@Param("..")Sys_userinf userinf, @Param("birthdayat") String birthday, HttpServletRequest req) {
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            userinf.setBirthday(birthdayat);
            userinf.setOpBy(StringUtil.getUid());
            userinf.setOpAt((int) (System.currentTimeMillis() / 1000));
            sysUserinfService.updateIgnoreNull(userinf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.sys.userinf.delete")
    @SLog(tag = "gz_inf", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
                sysUserinfService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
                sysUserinfService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/sys/userinf/detail.html")
    @RequiresPermissions("platform.sys.userinf")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
		    Object test = vSysuserService.fetch(Cnd.where("id","=",id));
            req.setAttribute("obj",test);
		}else{
            req.setAttribute("obj", null);
        }
    }


    @At
    @Ok("beetl:/platform/sys/userinf/selectUser.html")
    @RequiresPermissions("platform.sys.userinf")
    public void selectUser() {
    }


    /**
    * @function: 查询需要提交的用户信息
    * @param: roleid查询用户的角色, name,
    * @return:
    * @note:
    */
    @At
    @Ok("json:full")
    @RequiresPermissions("sys.manager.role")
    public Object selectData(@Param("roleid") String roleid, @Param("name") String name, @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        String sql = "SELECT a.* FROM sys_user a WHERE 1=1 ";
        if (!Strings.isBlank(roleid)) {
            sql += " and a.id NOT IN(SELECT b.userId FROM sys_user_role b WHERE b.roleId='" + roleid + "')";
        }
        if (!Strings.isBlank(name)) {
            sql += " and (a.loginname like '%" + name + "%' or a.nickname like '%" + name + "%') ";
        }
        String s = sql;
        if (order != null && order.size() > 0) {
            for (DataTableOrder o : order) {
                DataTableColumn col = columns.get(o.getColumn());
                s += " order by a." + Sqls.escapeSqlFieldValue(col.getData()).toString() + " " + o.getDir();
            }
        }
        Object test = roleService.data(length, start, draw, Sqls.create(sql), Sqls.create(s));
        return roleService.data(length, start, draw, Sqls.create(sql), Sqls.create(s));
    }

}
