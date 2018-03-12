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


    @Column("userid")
    @Readonly
    private String userid;

    @Column("username")
    @Readonly
    private String username;

    @Column("realname")
    @Readonly
    private String realname;

    @Column("avator")
    @Readonly
    private String avator;

    @Column("gyid")
    @Readonly
    private String gyid;

    @Column("isOnline")
    @Readonly
    private String isOnline;


    @Column("LoginIp")
    @Readonly
    private String LoginIp;


    @Column("phone")
    @Readonly
    private String phone;

    @Column("email")
    @Readonly
    private String email;


    @Column("email_checked")
    @Readonly
    private String email_checked;

    @Column("wsid")
    @Readonly
    private String wsid;


    @Column("qq")
    @Readonly
    private String qq;

    @Column("sex")
    @Readonly
    private String sex;

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

    @Column("idcardF")
    @Readonly
    private String idcardF;

    @Column("idcardB")
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

    @Column("status")
    @Readonly
    private String status;


    @Column("gyauthstatus")
    @Readonly
    private int gyauthstatus;


    @Column("gyauthstatusname")
    @Readonly
    private String gyauthstatusname;

    @Column("disabled")
    @Readonly
    private String disabled;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getGyid() {
        return gyid;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getLoginIp() {
        return LoginIp;
    }

    public void setLoginIp(String loginIp) {
        LoginIp = loginIp;
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

    public String getEmail_checked() {
        return email_checked;
    }

    public void setEmail_checked(String email_checked) {
        this.email_checked = email_checked;
    }

    public String getWsid() {
        return wsid;
    }

    public void setWsid(String wsid) {
        this.wsid = wsid;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public int getGyauthstatus() {
        return gyauthstatus;
    }

    public void setGyauthstatus(int gyauthstatus) {
        this.gyauthstatus = gyauthstatus;
    }

    public String getGyauthstatusname() {
        return gyauthstatusname;
    }

    public void setGyauthstatusname(String gyauthstatusname) {
        this.gyauthstatusname = gyauthstatusname;
    }
}
