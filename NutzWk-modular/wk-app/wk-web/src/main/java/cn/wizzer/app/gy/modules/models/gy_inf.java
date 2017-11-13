package cn.wizzer.app.gy.modules.models;

import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.framework.base.model.BaseModel;
import cn.wizzer.framework.util.DateUtil;
import org.nutz.dao.entity.annotation.*;
import org.nutz.ioc.loader.annotation.Inject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 89792 on 2017/11/10 0010.
 */

@Table("gy_inf")
public class gy_inf extends BaseModel implements Serializable {

    @Inject
    private GyInfService gyInfService;

    @Column
    @Name
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("gyid()")})  //todo: 雇员编号的生成方式
    private String id;

    @Column
    @Comment("登陆名")
    @ColDefine(type = ColType.VARCHAR, width = 32) //todo: 参照sys_user.id
    private String userid;

    @Column
    @Comment("真实姓名")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String realname;

    @Column
    @Comment("qq")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String qq;

    @Column
    @Comment("电话")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String phone;

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


    /**
     * @function: 雇员编号
     * @param:
     * @return:
     * @note: 编号说明:17年份,10497学校代码,0学历:(0本科1研究生2博士),0性别 (0女生 ,1男生),010顺序码
     */
    public String gyid() {
        StringBuilder str = new StringBuilder();
        //年份
        str.append(DateUtil.format(new Date(),"yyyy").indexOf(2,2));
        //学校代码
        str.append(this.college);
        //学历
        str.append(this.stuLevel);
        //性别
        str.append(this.sex);
        //顺序码
        str.append(gyInfService.count());
        return str.toString();
    }
}
