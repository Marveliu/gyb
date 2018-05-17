package com.marveliu.framework.model.gy;

import com.marveliu.framework.model.base.BaseModel;
import org.nutz.dao.entity.annotation.*;
import org.nutz.mvc.Mvcs;

import java.io.Serializable;


/**
 * Createdby89792on2017/11/130013.
 */

@Table("gy_auth")
public class gy_auth extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("认证单号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("$me.gyauid()")})
    private String id;

    @Column
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("认证时间")
    @ColDefine(type = ColType.INT)
    private Long reAuthTime;

    @Column
    @Comment("身份证正面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String idcardF;

    @Column
    @Comment("身份证方面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String idcardB;

    @Column
    @Comment("学生证正面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String stuCardF;

    @Column
    @Comment("学生证背面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String stuCardB;

    @Column
    @Comment("审核说明")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;



    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGyid() {
        return gyid;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
    }

    public Long getReAuthTime() {
        return reAuthTime;
    }

    public void setReAuthTime(Long reAuthTime) {
        this.reAuthTime = reAuthTime;
    }

    public String getIdcardF() {
        return idcardF;
    }

    public void setIdcardF(String idcardF) {
        this.idcardF = idcardF;
    }

    public String getIdcardB() {
        return idcardB;
    }

    public void setIdcardB(String idcardB) {
        this.idcardB = idcardB;
    }

    public String getStuCardF() {
        return stuCardF;
    }

    public void setStuCardF(String stuCardF) {
        this.stuCardF = stuCardF;
    }

    public String getStuCardB() {
        return stuCardB;
    }

    public void setStuCardB(String stuCardB) {
        this.stuCardB = stuCardB;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String gyauid() {
        StringBuilder str = new StringBuilder();
        str.append("Au");
        str.append(this.gyid);
        return str.toString();
    }
}