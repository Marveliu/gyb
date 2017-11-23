package cn.wizzer.app.web.modules.controllers.platform.gz;

import cn.wizzer.app.gz.modules.models.gz_inf;
import cn.wizzer.app.gz.modules.services.GzInfService;
import cn.wizzer.app.sys.modules.services.SysRoleService;
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
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@IocBean
@At("/platform/gz/inf")
public class GzInfController{
    private static final Log log = Logs.get();
    @Inject
    private GzInfService gzInfService;

    @Inject
    private SysRoleService roleService;

    @At("")
    @Ok("beetl:/platform/gz/inf/index.html")
    @RequiresPermissions("platform.gz.inf")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gz.inf")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
        Object test = gzInfService.data(length, start, draw, order, columns, cnd, null);
    	return gzInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/gz/inf/add.html")
    @RequiresPermissions("platform.gz.inf")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.gz.inf.add")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object addDo(@Param("..")gz_inf gzInf, @Param("birthdayat") String birthday,HttpServletRequest req) {
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            gzInf.setBirthday(birthdayat);
			gzInfService.insert(gzInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/gz/inf/edit.html")
    @RequiresPermissions("platform.gz.inf")
    public void edit(String id,HttpServletRequest req) {
		req.setAttribute("obj", gzInfService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.gz.inf.edit")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object editDo(@Param("..")gz_inf gzInf, @Param("birthdayat") String birthday, HttpServletRequest req) {
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            gzInf.setBirthday(birthdayat);

            gzInf.setOpBy(StringUtil.getUid());
			gzInf.setOpAt((int) (System.currentTimeMillis() / 1000));
			gzInfService.updateIgnoreNull(gzInf);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.gz.inf.delete")
    @SLog(tag = "gz_inf", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				gzInfService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				gzInfService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/gz/inf/detail.html")
    @RequiresPermissions("platform.gz.inf")
	public void detail(String id, HttpServletRequest req) {
		if (!Strings.isBlank(id)) {
            req.setAttribute("obj", gzInfService.fetch(id));
		}else{
            req.setAttribute("obj", null);
        }
    }


    @At
    @Ok("beetl:/platform/gz/inf/selectUser.html")
    @RequiresPermissions("platform.gz.inf")
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
