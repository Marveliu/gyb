package com.marveliu.framework.model.xm;

import com.marveliu.framework.model.base.BaseModel;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.model.sys.Sys_userinf;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/17 0017.
 */

@Table("xm_feedback")
@View("v_xmfeedback")
public class xm_feedback extends BaseModel implements Serializable {

    //版本信息
    @Column
    @Id
    @Comment("反馈id")
    private long id;

    @Column
    @Comment("父反馈编号")
    @ColDefine(type = ColType.INT)
    private long parentid;

    @Column
    @Comment("反馈编号")
    @ColDefine(type = ColType.VARCHAR,width = 32)
    private String code;

    @Column
    @Comment("项目编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String xminfid;

    @Column
    @Comment("创建时间")
    @ColDefine(type = ColType.INT)
    private Long at;

    @Column
    @Comment("审核文件")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String fileurl;

    @Column
    @Comment("雇员反馈")
    @ColDefine(type = ColType.TEXT)
    private String note;

    //审核信息
    @Column
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("下一次提交时间")
    @ColDefine(type = ColType.INT)
    private int nextcommit;

    @Column
    @Comment("审核反馈")
    @ColDefine(type = ColType.TEXT)
    private String reply;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;


    // 视图
    @Column
    @Readonly
    private String  phone;

    @Column
    @Readonly
    private String  realname;

    @Column
    @Readonly
    private String  author;

    @Column
    @Readonly
    private String  authorrealname;

    @Column
    @Readonly
    private String  xmfeedbackstatus;

    @Column
    @Readonly
    private String  taskname;

    // 参照
    @One(field = "xminfid")
    public xm_inf xmInf;

    // 参照
    @One(field = "gyid")
    public gy_inf gyInf;

    // 参照
    @One(field = "author")
    public Sys_userinf sys_userinf;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXminfid() {
        return xminfid;
    }

    public void setXminfid(String xminfid) {
        this.xminfid = xminfid;
    }

    public Long getAt() {
        return at;
    }

    public void setAt(Long at) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public String getXmfeedbackstatus() {
        return xmfeedbackstatus;
    }

    public void setXmfeedbackstatus(String xmfeedbackstatus) {
        this.xmfeedbackstatus = xmfeedbackstatus;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public xm_inf getXmInf() {
        return xmInf;
    }

    public void setXmInf(xm_inf xmInf) {
        this.xmInf = xmInf;
    }

    public gy_inf getGyInf() {
        return gyInf;
    }

    public void setGyInf(gy_inf gyInf) {
        this.gyInf = gyInf;
    }

    public Sys_userinf getSys_userinf() {
        return sys_userinf;
    }

    public void setSys_userinf(Sys_userinf sys_userinf) {
        this.sys_userinf = sys_userinf;
    }
}
