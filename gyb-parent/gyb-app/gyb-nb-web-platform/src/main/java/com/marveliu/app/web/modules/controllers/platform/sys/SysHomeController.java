package com.marveliu.app.web.modules.controllers.platform.sys;


import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.web.commons.base.Globals;
import com.marveliu.app.web.commons.utils.ShiroUtil;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.sys.Sys_log;
import com.marveliu.framework.model.sys.Sys_menu;
import com.marveliu.framework.model.sys.Sys_role;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.sys.SysMenuService;
import com.marveliu.framework.services.sys.SysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by wizzer on 2016/6/23.
 */
@IocBean
@At("/platform/home")
public class SysHomeController {
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private SysMenuService menuService;

    @Inject
    private ShiroUtil shiroUtil;

    /**
     * 不同的角色返回不同的主页
     */
    @At("")
    @Ok("re:beetl:/platform/sys/home.html")
    @RequiresAuthentication
    public String home(HttpServletRequest req) {
        // 角色：管理员和雇员
        if(shiroUtil.isGy()){
            return "forward:/platform/gy/person";
        }
        return null;
    }

    @At
    @Ok("beetl:/platform/sys/left.html")
    @RequiresAuthentication
    public void left(@Param("url") String url, HttpServletRequest req) {
        String path = "";
        String perpath = "";
        if (!Strings.isBlank(Globals.AppBase)) {
            url = Strings.sBlank(url).substring(Globals.AppBase.length());
        }
        if (Strings.sBlank(url).indexOf("?") > 0)
            url = url.substring(0, url.indexOf("?"));
        Sys_menu menu = menuService.fetch(Cnd.where("href", "=", url));
        if (menu != null) {
            if (menu.getPath().length() > 8) {
                path = menu.getPath().substring(0, 8);
                perpath = menu.getPath().substring(0, 4);
            } else if (menu.getPath().length() == 8) {
                perpath = menu.getPath().substring(0, 4);
            }
            req.setAttribute("mpath", menu.getPath());
        }
        req.setAttribute("path", path);
        req.setAttribute("perpath", perpath);
    }

    @At
    @Ok("beetl:/platform/sys/left.html")
    @RequiresAuthentication
    public void path(@Param("url") String url, HttpServletRequest req) {
        if (Strings.sBlank(url).indexOf("//") > 0) {
            String[] u = url.split("//");
            String s = u[1].substring(u[1].indexOf("/"));
            if (Strings.sBlank(s).indexOf("?") > 0)
                s = s.substring(0, s.indexOf("?"));
            if (!Strings.isBlank(Globals.AppBase)) {
                s = s.substring(Globals.AppBase.length());
            }
            String[] urls = s.split("/");
            List<String> list = new ArrayList<>();
            if (urls.length > 5) {
                list.add("/" + urls[1] + "/" + urls[2] + "/" + urls[3] + "/" + urls[4] + "/" + urls[5]);
                list.add("/" + urls[1] + "/" + urls[2] + "/" + urls[3] + "/" + urls[4]);
                list.add("/" + urls[1] + "/" + urls[2] + "/" + urls[3]);
                list.add("/" + urls[1] + "/" + urls[2]);
                list.add("/" + urls[1]);
            } else if (urls.length == 5) {
                list.add("/" + urls[1] + "/" + urls[2] + "/" + urls[3] + "/" + urls[4]);
                list.add("/" + urls[1] + "/" + urls[2] + "/" + urls[3]);
                list.add("/" + urls[1] + "/" + urls[2]);
                list.add("/" + urls[1]);
            } else if (urls.length == 4) {
                list.add("/" + urls[1] + "/" + urls[2] + "/" + urls[3]);
                list.add("/" + urls[1] + "/" + urls[2]);
                list.add("/" + urls[1]);
            } else if (urls.length == 3) {
                list.add("/" + urls[1] + "/" + urls[2]);
                list.add("/" + urls[1]);
            } else if (urls.length == 2) {
                list.add("/" + urls[1]);
            } else list.add(url);
            String path = "";
            String perpath = "";
            Sys_menu menu = menuService.fetch(Cnd.where("href", "in", list).desc("href").desc("path"));
            if (menu != null) {
                if (menu.getPath().length() > 8) {
                    path = menu.getPath().substring(0, 8);
                    perpath = menu.getPath().substring(0, 4);
                } else if (menu.getPath().length() == 8) {
                    perpath = menu.getPath().substring(0, 4);
                }
                req.setAttribute("mpath", menu.getPath());
            }
            req.setAttribute("path", path);
            req.setAttribute("perpath", perpath);
        }
    }
    
    @At(value={"/", "/index"}, top=true)
    @Ok(">>:/sysadmin")
    public void index() {}
}
