package cn.wizzer.app.xm.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/28 0028.
 * 查询项目信息的视图
 */

@View("v_xmfeedback")
public class v_xmfeedback extends BaseModel implements Serializable {

    //版本信息
    @Column("id")
    @Readonly
    private long id;

    @Column("parentid")
    @Readonly
    private long parentid;

    @Column("xminfid")
    @Readonly
    private String xminfid;

    @Column("at")
    @Readonly
    private int at;

    @Column("fileurl")
    @Readonly
    private String fileurl;

    @Column("note")
    @Readonly
    private String note;

    //审核信息
    @Column("gyid")
    @Readonly
    private String gyid;

    @Column("nextcommit")
    @Readonly
    private int nextcommit;

    @Column("reply")
    @Readonly
    private String reply;

    @Column("status")
    @Readonly
    private int status;

    @Column("author")
    @Readonly
    private String author;

    @Column("taskname")
    @Readonly
    private String taskname;

    @Column("realname")
    @Readonly
    private String realname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public String getXminfid() {
        return xminfid;
    }

    public void setXminfid(String xminfid) {
        this.xminfid = xminfid;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGyid() {
        return gyid;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
    }

    public int getNextcommit() {
        return nextcommit;
    }

    public void setNextcommit(int nextcommit) {
        this.nextcommit = nextcommit;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
