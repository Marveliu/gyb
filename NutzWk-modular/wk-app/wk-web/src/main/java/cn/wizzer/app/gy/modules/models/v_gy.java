package cn.wizzer.app.gy.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/22 0022.
 * 通过视图查询雇员所有的信息
 */

@View("v_gy")
public class v_gy extends BaseModel implements Serializable {

    //id信息
    @Column("gyid")
    @Readonly
    private String gyid;

    @Column("userid")
    @Readonly
    private String userid;

    @Column("authid")
    @Readonly
    private String authid;

    //account
    @Column("loginname")
    @Readonly
    private int loginname;

    @Column("username")
    @Readonly
    private int username;

    @Column("realname")
    @Readonly
    private String realname;


    //contact
    @Column("phone")
    @Readonly
    private String phone;

    @Column("email")
    @Readonly
    private String email;

    @Column("qq")
    @Readonly
    private String qq;


    //inf

    @Column("sex")
    @Readonly
    private int sex;

    @Column("idcard")
    @Readonly
    private String idcard;

    @Column("college")
    @Readonly
    private String college;

    @Column("school")
    @Readonly
    private String school;

    @Column("major")
    @Readonly
    private String major;

    @Column("stuLevel")
    @Readonly
    private String stuLevel;


    @Column("birthday")
    @Readonly
    private int birthday;

    @Column("regYear")
    @Readonly
    private int regYear;


    //auth
    @Column("idcradF")
    @Readonly
    private String idcardF;

    @Column("idcradB")
    @Readonly
    private String idcardB;

    @Column("stuCardF")
    @Readonly
    private String stuCardF;

    @Column("stuCardB")
    @Readonly
    private String stuCardB;

    @Column("reAuthTime")
    @Readonly
    private int reAuthTime;


    //first pay
    @Column("payid")
    @Readonly
    private String payid;

    @Column("payname")
    @Readonly
    private String payname;

    @Column("type")
    @Readonly
    private int type;

    @Column("status")
    @Readonly
    private int status;

    public String getGyid() {
        return gyid;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }

    public int getLoginname() {
        return loginname;
    }

    public void setLoginname(int loginname) {
        this.loginname = loginname;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStuLevel() {
        return stuLevel;
    }

    public void setStuLevel(String stuLevel) {
        this.stuLevel = stuLevel;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getRegYear() {
        return regYear;
    }

    public void setRegYear(int regYear) {
        this.regYear = regYear;
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

    public int getReAuthTime() {
        return reAuthTime;
    }

    public void setReAuthTime(int reAuthTime) {
        this.reAuthTime = reAuthTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
