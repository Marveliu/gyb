package cn.wizzer.app.web.modules.controllers.platform.sys;

import cn.wizzer.app.sys.modules.models.Sys_log;
import cn.wizzer.app.sys.modules.models.Sys_menu;
import cn.wizzer.app.sys.modules.models.Sys_role;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysMenuService;
import cn.wizzer.app.sys.modules.services.impl.VUserinfServiceImpl;
import cn.wizzer.app.web.commons.base.Globals;
import cn.wizzer.app.web.commons.slog.SLogService;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController;
import cn.wizzer.framework.util.StringUtil;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wizzer on 2016/6/23.
 */
@IocBean
@At("/platform/home")
public class SysHomeController {
    private static final Log log = Logs.get();

    @Inject
    private SysMenuService menuService;

    @Inject
    private UserInfUtil userInfUtil;

    @Inject
    private VUserinfServiceImpl vUserinfService;

    @Inject
    private SLogService sLogService;

    @Inject
    private  GyPersonController gyPersonController;


    /**
     *
     * 分发登录控制：登录角色校验，并且根据不同角色，调用不同角色的登录接口
     * 管理员，雇员，雇主
     *
     */


    @At(" ")
    @Ok("re:beetl:/platform/sys/home.html")
    @RequiresAuthentication
    public String  home(HttpServletRequest req) {
        //系统登录
        int roleFlag = 0;                                           //默认是管理员
        Sys_user user =  userInfUtil.getCurrentUser();
        List<Sys_role> roles= user.getRoles();
        StringBuilder msg = new StringBuilder("系统账号登录");      // 登录日志信息
        Pattern gypattern = Pattern.compile("^gy");                 // 雇员匹配正则表达式
        Pattern gzpattern = Pattern.compile("^gz");                 // 雇主匹配正则表达式

        // TODO: 2018/1/5 0005 角色关系，一个人是否只是有一个角色
        // 角色匹配
        for(Sys_role role : roles){
            String code = role.getCode();
            Matcher match1 = gypattern.matcher(code);
            Matcher match2 = gzpattern.matcher(code);
            if(match1.lookingAt()){
                msg.delete(0,msg.length());
                msg.append("雇员模块");
                roleFlag = 1;                                       // 修改为雇员
                break;
            }

            if(match2.lookingAt()){
                msg.delete(0,msg.length());
                msg.append("雇主模块");
                roleFlag = 2;                                       // 修改为雇主
                break;
            }
        }

        // 登录角色校验日志等级
        Sys_log sysLog = new Sys_log();
        sysLog.setType("info");
        sysLog.setTag("用户校验");
        sysLog.setSrc(this.getClass().getName() + "#home");
        sysLog.setMsg("成功登录系统！");
        sysLog.setIp(StringUtil.getRemoteAddr());
        sysLog.setOpBy(user.getId());
        sysLog.setOpAt((int) (System.currentTimeMillis() / 1000));
        sysLog.setUsername(user.getUsername());
        sLogService.async(sysLog);

        // 登录分发
        switch (roleFlag){
            case 1:
                return "forward:/platform/gy/person";           // 雇员
            case 2:
                return "forward:/platform/gz/person";           // 雇主
            default:
                break;
        }
        String sysuserid = vUserinfService.fetch(Cnd.where("userid","=",user.getId())).getId();
        req.getSession().setAttribute("sysuserid",sysuserid);
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
