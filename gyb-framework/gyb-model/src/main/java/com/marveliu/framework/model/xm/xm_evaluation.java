package com.marveliu.framework.model.xm;

import com.marveliu.framework.model.base.BaseModel;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.*;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/17 0017.
 */

@Table("xm_evaluation")
public class xm_evaluation extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("评价编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    // @Prev(els = {@EL("$me.xmevaid()")})
    private String id;

    @Column
    @Comment("项目编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String xminfid;

    @Column
    @Comment("评分")
    @ColDefine(type = ColType.FLOAT)
    private float grade;

    @Column
    @Comment("说明")
    @ColDefine(type = ColType.VARCHAR,width = 200)
    private String note;

    @Column
    @Comment("评价时间")
    @ColDefine(type = ColType.INT)
    private int at;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXminfid() {
        return xminfid;
    }

    public void setXminfid(String xminfid) {
        this.xminfid = xminfid;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    /**
     * 项目评价编号
     * @return
     */
    public xm_evaluation xmevaid() {
        StringBuilder str = new StringBuilder();
        String[] arr = this.xminfid.split("_");
        str.append("eva_");
        str.append(arr[1]);
        this.setId(str.toString());
        return this;
    }
}
