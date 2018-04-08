package cn.wizzer.app.sys.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by wizzer on 2016/6/21.
 */
@Table("sys_log_${month}")
public class Sys_log extends BaseModel implements Serializable {
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

    @Column
    @Comment("日志类型")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String type;

    @Column
    @Comment("日志标识")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String tag;

    @Column
    @Comment("执行类")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String src;

    @Column
    @Comment("来源IP")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String ip;

    @Column
    @Comment("日志内容")
    @ColDefine(type = ColType.TEXT)
    private String msg;

    @Column
    @Comment("请求结果")
    @ColDefine(type = ColType.TEXT)
    private String param;

    @Column
    @Comment("执行结果")
    @ColDefine(type = ColType.TEXT)
    private String result;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
