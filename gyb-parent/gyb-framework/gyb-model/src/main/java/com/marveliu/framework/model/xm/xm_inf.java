package com.marveliu.framework.model.xm;

import com.marveliu.framework.model.base.BaseModel;
import org.nutz.boot.AppContext;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/28 0028.
 */
@Table("xm_inf")
@View("v_xminf")
public class xm_inf extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("项目编号")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Prev(els = {@EL("$me.xminfid()")})
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
    @Comment("立项时间")
    @ColDefine(type = ColType.INT)
    private Long at;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;

    @Column
    @Comment("说明")
    @ColDefine(type = ColType.VARCHAR,width = 200)
    private String note;

    @Column
    @Readonly
    private String taskname;

    // 视图
    @Column
    @Readonly
    private String author;

    @Column
    @Readonly
    private String realname;

    @Column
    @Readonly
    private String authorrealname;

    @Column
    @Readonly
    private String category;

    @Column
    @Readonly
    private float award;


    @Column
    @Readonly
    private int designnum;

    @Column
    @Readonly
    private int endtime;

    @Column
    @Readonly
    private int firstcommit;

    @Column
    @Readonly
    private int applyendtime;

    @Column
    @Readonly
    private String taskinfo;

    @Column
    @Readonly
    private String taskcontent;

    @Column
    @Readonly
    private int publishAt;

    @Column
    @Readonly
    private int readnum;

    @Column
    @Readonly
    private Integer location;

    @Column
    @Readonly
    private String fileurl;

    @Column
    @Readonly
    private String xminfstatus;

    @Column
    @Readonly
    private String xmbillid;

    @Column
    @Readonly
    private String xmbillnote;

    @Column
    @Readonly
    private String xmevaid;


    @Column
    @Readonly
    private float xmevagrade;


    @Column
    @Readonly
    private String xmevanote;

    @Column
    @Readonly
    private float paysum;


    //参照
    @One(field = "xmtaskid")
    private xm_task xmtask;


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

    public Long getAt() {
        return at;
    }

    public void setAt(Long at) {
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

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAuthorrealname() {
        return authorrealname;
    }

    public void setAuthorrealname(String authorrealname) {
        this.authorrealname = authorrealname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAward() {
        return award;
    }

    public void setAward(float award) {
        this.award = award;
    }

    public int getDesignnum() {
        return designnum;
    }

    public void setDesignnum(int designnum) {
        this.designnum = designnum;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getFirstcommit() {
        return firstcommit;
    }

    public void setFirstcommit(int firstcommit) {
        this.firstcommit = firstcommit;
    }

    public int getApplyendtime() {
        return applyendtime;
    }

    public void setApplyendtime(int applyendtime) {
        this.applyendtime = applyendtime;
    }

    public String getTaskinfo() {
        return taskinfo;
    }

    public void setTaskinfo(String taskinfo) {
        this.taskinfo = taskinfo;
    }

    public String getTaskcontent() {
        return taskcontent;
    }

    public void setTaskcontent(String taskcontent) {
        this.taskcontent = taskcontent;
    }

    public int getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(int publishAt) {
        this.publishAt = publishAt;
    }

    public int getReadnum() {
        return readnum;
    }

    public void setReadnum(int readnum) {
        this.readnum = readnum;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public xm_task getXmtask() {
        return xmtask;
    }

    public void setXmtask(xm_task xmtask) {
        this.xmtask = xmtask;
    }

    public String getXminfstatus() {
        return xminfstatus;
    }

    public void setXminfstatus(String xminfstatus) {
        this.xminfstatus = xminfstatus;
    }

    public String getXmbillid() {
        return xmbillid;
    }

    public void setXmbillid(String xmbillid) {
        this.xmbillid = xmbillid;
    }

    public String getXmevaid() {
        return xmevaid;
    }

    public void setXmevaid(String xmevaid) {
        this.xmevaid = xmevaid;
    }

    public float getXmevagrade() {
        return xmevagrade;
    }

    public void setXmevagrade(float xmevagrade) {
        this.xmevagrade = xmevagrade;
    }

    public String getXmevanote() {
        return xmevanote;
    }

    public void setXmevanote(String xmevanote) {
        this.xmevanote = xmevanote;
    }

    public String getXmbillnote() {
        return xmbillnote;
    }

    public void setXmbillnote(String xmbillnote) {
        this.xmbillnote = xmbillnote;
    }

    public float getPaysum() {
        return paysum;
    }

    public void setPaysum(float paysum) {
        this.paysum = paysum;
    }


    /**
     * 项目编号生成 xmtaskid
     * xm_+ 任务书标识码
     * @return
     */
    public String xminfid() {
        StringBuilder str = new StringBuilder();
        try {
            String prefix = "rw_";
            String temp = this.getXmtaskid().split("_")[1];
            str.append(prefix);
            str.append(temp);
        }catch (Exception e){
            // Logs.get().debug(e);
        }
        return str.toString();
    }

}
