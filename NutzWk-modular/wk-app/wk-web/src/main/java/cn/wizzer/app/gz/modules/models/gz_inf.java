package cn.wizzer.app.gz.modules.models;

import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.gy.modules.services.impl.GyInfServiceImpl;
import cn.wizzer.framework.base.model.BaseModel;
import cn.wizzer.framework.util.DateUtil;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.*;
import org.nutz.mvc.Mvcs;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 89792 on 2017/11/10 0010.
 */

//产品经理等
@Table("gz_inf")
public class gz_inf extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("雇主编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("$me.gzid()")})  //todo: 雇主编号的生成方式
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @function: 雇主编号
     * @param:
     * @return:
     * @note:
     */
    public String gzid() {
        StringBuilder str = new StringBuilder();
        str.append("gz");
        //年份
        str.append(DateUtil.format(new Date(),"yyyy").substring(2,4));
        //性别
        str.append(this.sex);
        //顺序码
        Dao dao =  Mvcs.getIoc().get((Dao.class));
        GyInfService gyservice = new GyInfServiceImpl(dao);
        str.append(gyservice.count());
        return str.toString();
    }

}
