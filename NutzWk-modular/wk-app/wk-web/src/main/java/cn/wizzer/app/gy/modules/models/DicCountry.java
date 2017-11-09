package cn.wizzer.app.gy.modules.models;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
/**
 * Created by 89792 on 2017/11/9 0009.
 */
@Comment("国家")
@Table("dic_country")
public class DicCountry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Name
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @Comment("编码")
    @ColDefine(type = ColType.VARCHAR)
    private String code;
    @Column
    @Comment("名称")
    @ColDefine(type = ColType.VARCHAR)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}