package com.marveliu.app.library.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.library.lib_skill;
import com.marveliu.framework.model.library.lib_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.library.LibTaskService;
import org.apache.commons.lang3.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * Created by wizzer on 2016/12/22.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass=LibTaskService.class)
public class LibTaskServiceImpl extends BaseServiceImpl<lib_task> implements LibTaskService {

    public LibTaskServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 新增任务类型
     *
     * @param skill
     * @param pid
     */
    @Aop(TransAop.READ_COMMITTED)
    public void save(lib_task skill, String pid) {
        String path = "";
        if (!Strings.isEmpty(pid)) {
            lib_task pp = this.fetch(pid);
            path = pp.getPath();
        }
        skill.setPath(getSubPath("lib_task", "path", path));
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
    public void deleteAndChild(lib_task skill) {

        //todo：如何删除呢
        dao().execute(Sqls.create("delete from lib_task where path like @path").setParam("path", skill.getPath() + "%"));
        dao().execute(Sqls.create("delete from sys_user_unit where unitId=@id or unitId in(SELECT id FROM lib_task WHERE path like @path)").setParam("id", skill.getId()).setParam("path", skill.getPath() + "%"));
        dao().execute(Sqls.create("delete from sys_role where unitid=@id or unitid in(SELECT id FROM lib_task WHERE path like @path)").setParam("id", skill.getId()).setParam("path", skill.getPath() + "%"));


        if (!Strings.isEmpty(skill.getParentId())) {
            int count = count(Cnd.where("parentId", "=", skill.getParentId()));
            if (count < 1) {
                dao().execute(Sqls.create("update lib_task set hasChildren=0 where id=@pid").setParam("pid", skill.getParentId()));
            }
        }
    }

    public List<lib_skill> getDatas(String taskId) {
        Sql sql = Sqls.create("select distinct a.* from lib_skill a,lib_task_skill b where a.id=b.skillId and" +
                " b.taskId=@taskId order by a.location ASC,a.path asc");
        sql.params().set("taskId", taskId);
        Entity<lib_skill> entity = dao().getEntity(lib_skill.class);
        sql.setEntity(entity);          //用来获取实体对象
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(lib_skill.class);
    }

    public List<lib_skill> getDatas() {
        Sql sql = Sqls.create("select distinct a.* from lib_skill a,lib_task_skill b where a.id=b.skillId order by a.location ASC,a.path asc");
        Entity<lib_skill> entity = dao().getEntity(lib_skill.class);
        sql.setEntity(entity);
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(lib_skill.class);
    }

    public List<lib_skill> getMenusAndButtons(String taskId) {
        Sql sql = Sqls.create("select distinct a.* from lib_skill a,lib_task_skill b where a.id=b.skillId and" +
                " b.taskId=@taskId order by a.location ASC,a.path asc");
        sql.params().set("taskId", taskId);
        Entity<lib_skill> entity = dao().getEntity(lib_skill.class);
        sql.setEntity(entity);
        sql.setCallback(Sqls.callback.entities());
        dao().execute(sql);
        return sql.getList(lib_skill.class);
    }

    /**
     * 修改任务所需要的技能信息
     * @param skillIds
     * @param taskid
     * @return
     */
    @Override
    public boolean editSkillsForTask(String skillIds, String taskid) {
        try {
            String[] ids = StringUtils.split(skillIds, ",");
            this.clear("lib_task_skill", Cnd.where("taskid", "=", taskid));
            for (String s : ids) {
                if (!Strings.isEmpty(s)) {
                    this.insert("lib_task_skill",Chain.make("taskId", taskid).add("skillId", s));
                }
            }
            return true;
        } catch (Exception e) {
        }

        return false;
    }
}
