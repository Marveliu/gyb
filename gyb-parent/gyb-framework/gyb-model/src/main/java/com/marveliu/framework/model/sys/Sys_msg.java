package com.marveliu.framework.model.sys;

import com.marveliu.framework.model.base.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by wizzer on 2016/6/21.
 */
@Table("sys_msg")
public class Sys_msg extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Id
//    @Prev({
//            //仅做演示,实际使用oracle时,请使用触发器+序列的方式实现自增长ID,否则高并发下此种写法性能是个瓶颈
//            //实际上不推荐在主键上使用自定义sql来生成
//            @SQL(db = DB.ORACLE, value = "SELECT SYS_LOG_S.nextval FROM dual")
//    })
    private long id;

    @Column
    @Comment("创建昵称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String username;

    // ConfigUtil SYS_MSG_TYPE_* 对应
    @Column
    @Comment("推送方式")
    @ColDefine(type = ColType.INT)
    private Integer type;

    // 雇员，系统，任务三大消息标识
    @Column
    @Comment("消息标识")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String tag;

    // sys_usr id 可以获得联系方式
    @Column
    @Comment("消息接受人")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String revid;

    // @Column
    // @Comment("消息接受账号")
    // @ColDefine(type = ColType.VARCHAR, width = 100)
    // private String revaccount;

    @Column
    @Comment("消息发送人")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String sendid;

    @Column
    @Comment("来源IP")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String ip;

    // json数据
    @Column
    @Comment("推送内容")
    @ColDefine(type = ColType.TEXT)
    private String msg;

    @Column
    @Comment("是否查阅")
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean checked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRevid() {
        return revid;
    }

    public void setRevid(String revid) {
        this.revid = revid;
    }

    public String getSendid() {
        return sendid;
    }

    public void setSendid(String sendid) {
        this.sendid = sendid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
