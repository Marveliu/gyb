package cn.wizzer.app.xm.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/17 0017.
 */

@Table("xm_evaluation")
public class xm_evaluation extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("评价编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("项目编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String xminfid;

    @Column
    @Comment("评分")
    @ColDefine(type = ColType.FLOAT)
    private Double grade;

    @Column
    @Comment("说明")
    @ColDefine(type = ColType.VARCHAR,width = 200)
    private String note;

    @Column
    @Comment("评价时间")
    @ColDefine(type = ColType.INT)
    private int at;

}
