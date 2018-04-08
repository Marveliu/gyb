package cn.wizzer.app.gy.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

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
    private String id;

    @Column
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("认证时间")
    @ColDefine(type = ColType.INT)
    private Integer reAuthTime;

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

    public Integer getReAuthTime() {
        return reAuthTime;
    }

    public void setReAuthTime(Integer reAuthTime) {
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
}