package com.marveliu.framework.model.gy;

import com.marveliu.framework.model.base.BaseModel;
import com.marveliu.framework.model.library.lib_skill;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/22 0022.
 */

//设定项目所需技能级别限制

@Table("gy_skill")
public class gy_skill extends BaseModel implements Serializable {

    @Column
    @Id
    private long id;

    @Column
    @Comment("雇员id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("技能id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String skillid;

    @Column
    @Comment("技能等级")
<<<<<<< HEAD:gyb-parent/gyb-framework/gyb-model/src/main/java/com/marveliu/framework/model/gy/gy_skill.java
    @ColDefine(type = ColType.INT)
=======
    @ColDefine(type = ColType.FLOAT)
>>>>>>> master:wk-parent/wk-app/wk-web/src/main/java/cn/wizzer/app/gy/modules/models/gy_skill.java
    private float skilllevel;

    // 参照 技能和系统雇员
    @One(field = "skillid")
    private lib_skill skill;

    @One(field = "gyid")
    private gy_inf gyinf;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGyid() {
        return gyid;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
    }

    public String getSkillid() {
        return skillid;
    }

    public void setSkillid(String skillid) {
        this.skillid = skillid;
    }

    public float getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(float skilllevel) {
        this.skilllevel = skilllevel;
    }

    public lib_skill getSkill() {
        return skill;
    }

    public void setSkill(lib_skill skill) {
        this.skill = skill;
    }

    public gy_inf getGyinf() {
        return gyinf;
    }

    public void setGyinf(gy_inf gyinf) {
        this.gyinf = gyinf;
    }
}
