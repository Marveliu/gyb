package cn.wizzer.app.gz.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/10 0010.
 */

//产品经理等
@View("v_gz")
public class v_gz extends BaseModel implements Serializable {

    @Column
    @Readonly
    private String id;

    @Column
    @Readonly
    private String userid;

    @Column
    @Readonly
    private String realname;

    @Column
    @Readonly
    private String qq;

    @Column
    @Readonly
    private String phone;

    @Column
    @Readonly
    private Integer birthday;

    @Column
    @Readonly
    private Integer sex;

    @Column
    @Readonly
    private String idcard;

    @Column
    @Readonly
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

}
