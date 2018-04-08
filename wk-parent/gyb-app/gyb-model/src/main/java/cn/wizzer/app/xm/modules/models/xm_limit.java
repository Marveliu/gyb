package cn.wizzer.app.xm.modules.models;

import cn.wizzer.app.library.modules.models.lib_skill;
import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/22 0022.
 */

//设定项目所需技能级别限制

@Table("xm_limit")
public class xm_limit extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("任务书编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String xmtaskid;

    @Column
    @Comment("技能编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String skillid;

    @Column
    @Comment("技能等级")
    @ColDefine(type = ColType.INT)
    private int skilllevel;

    //参照关系
    @One(field = "skillid")
    private lib_skill skill;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmtaskid() {
        return xmtaskid;
    }

    public void setXmtaskid(String xmtaskid) {
        this.xmtaskid = xmtaskid;
    }

    public String getSkillid() {
        return skillid;
    }

    public void setSkillid(String skillid) {
        this.skillid = skillid;
    }

    public int getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(int skilllevel) {
        this.skilllevel = skilllevel;
    }

    public lib_skill getSkill() {
        return skill;
    }

    public void setSkill(lib_skill skill) {
        this.skill = skill;
    }
}
