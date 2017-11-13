package cn.wizzer.app.hi.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;


/**
 * Createdby89792on2017/11/130013.
 */
@Table("gy_auth")
public class gy_auth extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("认证单号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("RandomBatchNum()")})
    private String id;

    @Column
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("认证时间")
    @ColDefine(type = ColType.INT)
    private Integer reAuthTime;

    @Column
    @Comment("身份证正面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String idcardF;

    @Column
    @Comment("身份证方面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String idcardB;

    @Column
    @Comment("学生证正面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String stuCardF;

    @Column
    @Comment("学生证背面")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String stuCardB;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;
}