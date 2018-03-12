package cn.wizzer.app.library.modules.services.impl;

import cn.wizzer.app.library.modules.models.lib_skill;
import cn.wizzer.app.library.modules.services.LibSkillService;
import cn.wizzer.framework.base.service.BaseServiceImpl;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.*;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

/**
 * Created by wizzer on 2016/12/22.
 */
@IocBean(args = {"refer:dao"})
public class LibSkillServiceImpl extends BaseServiceImpl<lib_skill> implements LibSkillService {

    public LibSkillServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 新增单位
     *
     * @param skill
     * @param pid
     */
    @Aop(TransAop.READ_COMMITTED)
    public void save(lib_skill skill, String pid) {
        String path = "";
        if (!Strings.isEmpty(pid)) {
            lib_skill pp = this.fetch(pid);
            path = pp.getPath();
        }
        skill.setPath(getSubPath("lib_skill", "path", path));
        skill.setParentId(pid);
        dao().insert(skill);
        if (!Strings.isEmpty(pid)) {
            this.update(Chain.make("hasChildren", true), Cnd.where("id", "=", pid));
        }
    }

    /**
     * 级联删除单位
     *
     * @param skill
     */
    @Aop(TransAop.READ_COMMITTED)
    public void deleteAndChild(lib_skill skill) {


        dao().execute(Sqls.create("delete from lib_skill where path like @path").setParam("path", skill.getPath() + "%"));
        dao().execute(Sqls.create("delete from sys_user_unit where unitId=@id or unitId in(SELECT id FROM lib_skill WHERE path like @path)").setParam("id", skill.getId()).setParam("path", skill.getPath() + "%"));
        dao().execute(Sqls.create("delete from sys_role where unitid=@id or unitid in(SELECT id FROM lib_skill WHERE path like @path)").setParam("id", skill.getId()).setParam("path", skill.getPath() + "%"));


        if (!Strings.isEmpty(skill.getParentId())) {
            int count = count(Cnd.where("parentId", "=", skill.getParentId()));
            if (count < 1) {
                dao().execute(Sqls.create("update lib_skill set hasChildren=0 where id=@pid").setParam("pid", skill.getParentId()));
            }
        }
    }
}
