package com.marveliu.framework.model.gy;


import com.marveliu.framework.model.base.BaseModel;
import com.marveliu.framework.model.xm.xm_task;
import org.nutz.boot.AppContext;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Lang;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 89792 on 2017/11/10 0010.
 */

@Table("gy_inf")
@View("v_gy")
public class gy_inf extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("$me.gyid()")})
    private String id;

    @Column
    @Comment("登陆名")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String userid;

    @Column
    @Comment("真实姓名")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String realname;


    @Column
    @Comment("出生日期")
    @ColDefine(type = ColType.INT)
    private Integer birthday;

    @Column
    @Comment("性别")
    @ColDefine(type = ColType.INT)
    private Integer sex;

    @Column
    @Comment("身份证")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String idcard;

    @Column
    @Comment("就读学校")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String college;

    @Column
    @Comment("所在学院")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String school;

    @Column
    @Comment("就读专业")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String major;

    @Column
    @Comment("注册年份")
    @ColDefine(type = ColType.INT)
    private Integer regYear;

    @Column
    @Comment("最高学历")
    @ColDefine(type = ColType.INT)
    private int stuLevel;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    //参照
    @ManyMany(relation = "xm_apply", from = "gyid", to = "xmtaskid")
    private List<xm_task> xmtasks;

    @ManyMany(relation = "gy_skill", from = "gyid", to = "skillid")
    private List<gy_skill> gyskills;

    // gy_pay查询是通过视图，所以此处是gyid
    @Many(field = "gyid")
    private List<gy_pay> gypays;

    @Column("username")
    @Readonly
    private String username;

    @Column("sexname")
    @Readonly
    private String sexname;


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


    @Column("email")
    @Readonly
    private String email;


    @Column("email_checked")
    @Readonly
    private String email_checked;

    @Column("wsid")
    @Readonly
    private String wsid;

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


    @Column("gyauthstatus")
    @Readonly
    private int gyauthstatus;


    @Column("gyauthstatusname")
    @Readonly
    private String gyauthstatusname;

    @Column("stuLevelname")
    @Readonly
    private String stuLevelname;

    @Column("qq")
    @Readonly
    private String qq;

    @Column("phone")
    @Readonly
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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

    public Integer getRegYear() {
        return regYear;
    }

    public void setRegYear(Integer regYear) {
        this.regYear = regYear;
    }

    public int getStuLevel() {
        return stuLevel;
    }

    public void setStuLevel(int stuLevel) {
        this.stuLevel = stuLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<xm_task> getXmtasks() {
        return xmtasks;
    }

    public void setXmtasks(List<xm_task> xmtasks) {
        this.xmtasks = xmtasks;
    }

    public List<gy_skill> getGyskills() {
        return gyskills;
    }

    public void setGyskills(List<gy_skill> gyskills) {
        this.gyskills = gyskills;
    }

    public List<gy_pay> getGypays() {
        return gypays;
    }

    public void setGypays(List<gy_pay> gypays) {
        this.gypays = gypays;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSexname() {
        return sexname;
    }

    public void setSexname(String sexname) {
        this.sexname = sexname;
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

    public String getStuLevelname() {
        return stuLevelname;
    }

    public void setStuLevelname(String stuLevelname) {
        this.stuLevelname = stuLevelname;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @function: 雇员编号
     * @param:
     * @return:
     * @note: 编号说明:17年份,10497学校代码,0学历:(0本科1研究生2博士),0性别 (0女生 ,1男生),010顺序码
     */
    public String gyid() {
        StringBuilder str = new StringBuilder();
        try {
            Date date = new Date();
            long times =date.getTime();//时间戳
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String dateString = formatter.format(date);
            Dao dao =  AppContext.getDefault().getIoc().get(Dao.class);
            // web modules也会来找dao
            // 1. 给其配置数据库
            // 2. 跳过这些配置
            if (Lang.isEmpty(dao)) return null;
            int count =AppContext.getDefault().getIoc().get(Dao.class).count(this.getClass());
            str.append("gy");
            str.append(dateString.substring(2, 6));
            str.append(count + 1);
        }catch (Exception e){
            // Logs.get().debug(e);
        }

        return str.toString();
    }
}
