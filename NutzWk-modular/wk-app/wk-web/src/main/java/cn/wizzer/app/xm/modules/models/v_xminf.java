package cn.wizzer.app.xm.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/28 0028.
 * 查询项目信息的视图
 */

@View("v_xminf")
public class v_xminf extends BaseModel implements Serializable {

    @Column("id")
    @Readonly
    private String id;

    @Column("gyid")
    @Readonly
    private String gyid;

    @Column("gyname")
    @Readonly
    private String gyname;

    @Column("taskname")
    @Readonly
    private String taskname;

    @Column("category")
    @Readonly
    private String category;

    @Column("award")
    @Readonly
    private float award;

    @Column("designnum")
    @Readonly
    private Integer designnum;

    @Column("author")
    @Readonly
    private String author;

    @Column("endtime")
    @Readonly
    private Integer endtime;


    @Column("info")
    @Readonly
    private String info;

    @Column("content")
    @Readonly
    private String content;

    @Column("publishAt")
    @Readonly
    private Integer publishAt;

    @Column("fileurl")
    @Readonly
    private String fileurl;

    @Column("status")
    @Readonly
    private int status;

    // bill
    @Column("billid")
    @Readonly
    private String billid;

    @Column("prepaysum")
    @Readonly
    private float prepaysum;

    @Column("paysum")
    @Readonly
    private float paysum;

    @Column("realpayid")
    @Readonly
    private String realpayid;

    @Column("billnote")
    @Readonly
    private String billnote;

    @Column("payby")
    @Readonly
    private String payby;

    @Column("evaid")
    @Readonly
    private String evaid;

    @Column("grade")
    @Readonly
    private float grade;

    @Column("evanote")
    @Readonly
    private String evanote;

    //pay
    @Column("gypayid")
    @Readonly
    private String gypayid;

    @Column("payid")
    @Readonly
    private String payid;

    @Column("payname")
    @Readonly
    private String payname;

    @Column("type")
    @Readonly
    private int paytype;



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

    public Integer getDesignnum() {
        return designnum;
    }

    public void setDesignnum(Integer designnum) {
        this.designnum = designnum;
    }

    public Integer getEndtime() {
        return endtime;
    }

    public void setEndtime(Integer endtime) {
        this.endtime = endtime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Integer publishAt) {
        this.publishAt = publishAt;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getGyname() {
        return gyname;
    }

    public void setGyname(String gyname) {
        this.gyname = gyname;
    }

    public String getGypayid() {
        return gypayid;
    }

    public void setGypayid(String gypayid) {
        this.gypayid = gypayid;
    }

    public float getPrepaysum() {
        return prepaysum;
    }

    public void setPrepaysum(float prepaysum) {
        this.prepaysum = prepaysum;
    }

    public String getRealpayid() {
        return realpayid;
    }

    public void setRealpayid(String realpayid) {
        this.realpayid = realpayid;
    }

    public String getBillnote() {
        return billnote;
    }

    public void setBillnote(String billnote) {
        this.billnote = billnote;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public float getPaysum() {
        return paysum;
    }

    public void setPaysum(float paysum) {
        this.paysum = paysum;
    }


    public String getEvaid() {
        return evaid;
    }

    public void setEvaid(String evaid) {
        this.evaid = evaid;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getEvanote() {
        return evanote;
    }

    public void setEvanote(String evanote) {
        this.evanote = evanote;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }
}
