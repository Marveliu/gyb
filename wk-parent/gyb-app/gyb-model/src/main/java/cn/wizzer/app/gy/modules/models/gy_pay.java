package cn.wizzer.app.gy.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/21 0021.
 */


@Table("gy_pay")
@View("v_gypay")
public class gy_pay extends BaseModel implements Serializable {

    @Column
    @Comment("id")
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("账号编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String payid;

    @Column
    @Comment("收款人")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String payname;

    @Column
    @Comment("种类")
    @ColDefine(type = ColType.INT)
    private int type;

    @Column
    @Comment("主要付款方式")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean first;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;


    @Column
    @Readonly
    public String typename;

    @Column
    @Readonly
    public String realname;

    // 参照

    @One(field = "gyid")
    public gy_inf gy;


    public String getGyid() {
        return gyid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
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

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public gy_inf getGy() {
        return gy;
    }

    public void setGy(gy_inf gy) {
        this.gy = gy;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }


}
