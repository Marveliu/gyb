package cn.wizzer.app.gy.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/13 0013.
 */
@Comment("国家")
@Table("dic_country")
public class ceshi extends BaseModel implements Serializable {
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
}