package cn.wizzer.app.web.modules.controllers.platform.lib;

import cn.wizzer.app.library.modules.models.lib_skill;
import cn.wizzer.app.library.modules.services.LibSkillService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.util.ShiroUtil;
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
import java.util.*;

/**
 * Created by wizzer on 2016/6/24.
 */
@IocBean
@At("/platform/lib/skill")
public class LibSkillController {
    private static final Log log = Logs.get();
    @Inject
    private LibSkillService libSkillService;
    @Inject
    private ShiroUtil shiroUtil;

    //todo:给了管理员什么的权限？
    @At("")
    @Ok("beetl:/platform/lib/skill/index.html")
    @RequiresPermissions("lib.skill")
    public Object index() {
        if (shiroUtil.hasRole("sysadmin")) {
            return libSkillService.query(Cnd.where("parentId", "=", "").or("parentId", "is", null).asc("path"));
        }
        Sys_user user = (Sys_user) shiroUtil.getPrincipal();
        if (user != null) {
            return libSkillService.query(Cnd.where("id", "=", user.getUnitid()).asc("path"));
        }
        return new ArrayList<>();
    }

    @At
    @Ok("beetl:/platform/lib/skill/add.html")
    @RequiresPermissions("lib.skill")
    public Object add(@Param("pid") String pid) {
        return Strings.isBlank(pid) ? null : libSkillService.fetch(pid);
    }

    @At
    @Ok("json")
    @RequiresPermissions("lib.skill.add")
    @SLog(tag = "新建技能", msg = "技能 名称:${args[0].name}")
    public Object addDo(@Param("..") lib_skill skill, @Param("parentId") String parentId, HttpServletRequest req) {
        try {
            libSkillService.save(skill, parentId);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/child/?")
    @Ok("beetl:/platform/lib/skill/child.html")
    @RequiresPermissions("lib.skill")
    public Object child(String id) {
        return libSkillService.query(Cnd.where("parentId", "=", id).asc("path"));
    }

    @At("/detail/?")
    @Ok("beetl:/platform/lib/skill/detail.html")
    @RequiresPermissions("lib.skill")
    public Object detail(String id) {
        return libSkillService.fetch(id);
    }

    @At("/edit/?")
    @Ok("beetl:/platform/lib/skill/edit.html")
    @RequiresPermissions("lib.skill")
    public Object edit(String id, HttpServletRequest req) {
        lib_skill skill = libSkillService.fetch(id);
        if (skill != null) {
            req.setAttribute("parentUnit", libSkillService.fetch(skill.getParentId()));
        }
        return skill;
    }

    @At
    @Ok("json")
    @RequiresPermissions("lib.skill.edit")
    @SLog(tag = "编辑技能", msg = "技能名称:${args[0].name}")
    public Object editDo(@Param("..") lib_skill skill, @Param("parentId") String parentId, HttpServletRequest req) {
        try {
            skill.setOpBy(StringUtil.getUid());
            skill.setOpAt((int) (System.currentTimeMillis() / 1000));
            libSkillService.updateIgnoreNull(skill);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("lib.skill.delete")
    @SLog(tag = "删除技能", msg = "技能名称:${args[1].getAttribute('name')}")
    public Object delete(String id, HttpServletRequest req) {
        try {
            lib_skill skill = libSkillService.fetch(id);
            req.setAttribute("name", skill.getName());
            if ("0001".equals(skill.getPath())) {
                return Result.error("system.not.allow");
            }
            libSkillService.deleteAndChild(skill);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At
    @Ok("json")
    @RequiresPermissions("lib.skill")
    public Object tree(@Param("pid") String pid) {
        List<lib_skill> list = new ArrayList<>();
        if (shiroUtil.hasRole("sysadmin")) {
            Cnd cnd = Cnd.NEW();
            if (Strings.isBlank(pid)) {
                cnd.and("parentId", "=", "").or("parentId", "is", null);
            } else {
                cnd.and("parentId", "=", pid);
            }
            cnd.asc("path");
            list = libSkillService.query(cnd);
        } else {
            //todo： 查询员工拥有的技能库
            Sys_user user = (Sys_user) shiroUtil.getPrincipal();
            if (user != null && Strings.isBlank(pid)) {
                list = libSkillService.query(Cnd.where("id", "=", user.getUnitid()).asc("path"));
            } else {
                Cnd cnd = Cnd.NEW();
                if (Strings.isBlank(pid)) {
                    cnd.and("parentId", "=", "").or("parentId", "is", null);
                } else {
                    cnd.and("parentId", "=", pid);
                }
                cnd.asc("path");
                list = libSkillService.query(cnd);
            }
        }
        List<Map<String, Object>> tree = new ArrayList<>();
        for (lib_skill skill : list) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", skill.getId());
            obj.put("text", skill.getName());
            obj.put("children", skill.isHasChildren());
            tree.add(obj);
        }
        return tree;
    }
}
