package com.marveliu.framework.model.xm;

import com.marveliu.framework.model.base.BaseModel;
import com.marveliu.framework.model.gy.gy_pay;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.*;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/17 0017.
 */
@Table("xm_bill")
@View("v_xmbill")
public class xm_bill extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("账单编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("$me.xmbillid()")})
    private String id;

    @Column
    @Comment("项目编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String xminfid;

    @Column
    @Comment("雇员要求付款方式")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gypayid;

    @Column
    @Comment("实际付款方式")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String realgypayid;

    @Column
    @Comment("预付总额")
    @ColDefine(type = ColType.FLOAT)
    private float prepaysum;

    @Column
    @Comment("实际付款总额")
    @ColDefine(type = ColType.FLOAT)
    private float paysum;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;

    @Column
    @Comment("说明")
    @ColDefine(type = ColType.VARCHAR,width = 200)
    private String note;

    @Column
    @Comment("付款人")
    @ColDefine(type = ColType.VARCHAR,width = 32)
    private String payby;

    @Column
    @Comment("付款时间")
    @ColDefine(type = ColType.INT)
    private Long at;

    //参照
    @One(field = "gypayid")
    private gy_pay gypay;

    //参照
    @One(field = "realgypayid")
    private gy_pay realgypay;

    //参照
    @One(field = "xminfid")
    private xm_inf xmInf;

    // 视图
    @Column
    @Readonly
    private Float award;

    @Column
    @Readonly
    private String sysuserid;

    @Column
    @Readonly
    private String paybyname;


    @Column
    @Readonly
    private String taskname;

    @Column
    @Readonly
    private String author;


    @Column
    @Readonly
    private String authorrealname;


    @Column
    @Readonly
    private String xmbillstatus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGypayid() {
        return gypayid;
    }

    public void setGypayid(String gypayid) {
        this.gypayid = gypayid;
    }

    public String getRealgypayid() {
        return realgypayid;
    }

    public void setRealgypayid(String realgypayid) {
        this.realgypayid = realgypayid;
    }

    public float getPrepaysum() {
        return prepaysum;
    }

    public void setPrepaysum(float prepaysum) {
        this.prepaysum = prepaysum;
    }

    public float getPaysum() {
        return paysum;
    }

    public void setPaysum(float paysum) {
        this.paysum = paysum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby;
    }

    public Long getAt() {
        return at;
    }

    public void setAt(Long at) {
        this.at = at;
    }

    public String getXminfid() {
        return xminfid;
    }

    public void setXminfid(String xminfid) {
        this.xminfid = xminfid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public gy_pay getGypay() {
        return gypay;
    }

    public void setGypay(gy_pay gypay) {
        this.gypay = gypay;
    }

    public Float getAward() {
        return award;
    }

    public void setAward(Float award) {
        this.award = award;
    }

    public String getSysuserid() {
        return sysuserid;
    }

    public void setSysuserid(String sysuserid) {
        this.sysuserid = sysuserid;
    }

    public String getPaybyname() {
        return paybyname;
    }

    public void setPaybyname(String paybyname) {
        this.paybyname = paybyname;
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

    public String getAuthorrealname() {
        return authorrealname;
    }

    public void setAuthorrealname(String authorrealname) {
        this.authorrealname = authorrealname;
    }

    public String getXmbillstatus() {
        return xmbillstatus;
    }

    public void setXmbillstatus(String xmbillstatus) {
        this.xmbillstatus = xmbillstatus;
    }

    public gy_pay getRealgypay() {
        return realgypay;
    }

    public void setRealgypay(gy_pay realgypay) {
        this.realgypay = realgypay;
    }

    public xm_inf getXmInf() {
        return xmInf;
    }

    public void setXmInf(xm_inf xmInf) {
        this.xmInf = xmInf;
    }

    /**
     * 项目账单编号
     * @return
     */
    public String xmbillid() {
        StringBuilder str = new StringBuilder();
        String[] arr = this.xminfid.split("_");
        str.append("bill_");
        str.append(arr[1]);
        return str.toString();
    }

}
