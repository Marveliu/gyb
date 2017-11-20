package cn.wizzer.app.gy.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;


/**
 * Createdby89792on2017/11/130013.
 */
@Table("gy_auth")
@View("v_gy")
public class gy_auth extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("认证单号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;



    //视图字段
    @Column("realname")    // 其实可以不用声明数据库字段名 "taskcount"，因为多数数据库忽略大小写
    @Readonly                 // <- 这里声明了只读字段，即视图里增加的字段
    private String realname;

    @Column("phone")
    @Readonly
    private String phone;

    @Column("qq")
    @Readonly
    private String qq;


    @Column("idcard")
    @Readonly
    private String idcard;



    @Column("email")
    @Readonly
    private String email;

    @Column("auid")
    @Readonly
    private String auid;

    public String getAuid() {
        return auid;
    }

    public String getRealname() {
        return realname;
    }

    public String getPhone() {
        return phone;
    }

    public String getQq() {
        return qq;
    }

    public String getEmail() {
        return email;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

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





}