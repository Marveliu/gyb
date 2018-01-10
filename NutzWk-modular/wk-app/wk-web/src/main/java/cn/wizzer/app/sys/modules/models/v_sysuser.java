package cn.wizzer.app.sys.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.JsonField;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/10 0010.
 */

//产品经理等
@View("v_sysuser")
public class v_sysuser extends BaseModel implements Serializable {

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
    private String username;

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
    private int status;

    @Column
    @Readonly
    private boolean isOnline;

    @Column
    @Readonly
    private String email;

    @Column
    @Readonly
    protected byte[] avatar;

    @Column
    @Readonly
    private String wsid;


    @Column
    @Readonly
    private String loginIp;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getWsid() {
        return wsid;
    }

    public void setWsid(String wsid) {
        this.wsid = wsid;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
