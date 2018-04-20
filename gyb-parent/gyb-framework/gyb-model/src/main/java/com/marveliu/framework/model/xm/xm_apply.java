package com.marveliu.framework.model.xm;

import com.marveliu.framework.model.base.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/22 0022.
 * 雇员表和任务书的多对多映射关系表
 */
@Table("xm_apply")
@View("v_xmapply")
public class xm_apply extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("申请编号")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String id;

    @Column
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("任务书编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String xmtaskid;

    @Column
    @Comment("申请时间")
    @ColDefine(type = ColType.INT)
    private int at;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;

    @Column
    @Comment("说明")
    @ColDefine(type = ColType.VARCHAR,width = 200)
    private String note;


    // 视图
    @Column
    @Readonly
    private String applystatus;

    @Column
    @Readonly
    private String taskname;


    @Column
    @Readonly
    private String gyrealname;


    @Column
    @Readonly
    private String author;

    @Column
    @Readonly
    private String authorrealname;


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

    public String getXmtaskid() {
        return xmtaskid;
    }

    public void setXmtaskid(String xmtaskid) {
        this.xmtaskid = xmtaskid;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
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

    public String getApplystatus() {
        return applystatus;
    }

    public void setApplystatus(String applystatus) {
        this.applystatus = applystatus;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getGyrealname() {
        return gyrealname;
    }

    public void setGyrealname(String gyrealname) {
        this.gyrealname = gyrealname;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorrealname() {
        return authorrealname;
    }

    public void setAuthorrealname(String authorrealname) {
        this.authorrealname = authorrealname;
    }

}
