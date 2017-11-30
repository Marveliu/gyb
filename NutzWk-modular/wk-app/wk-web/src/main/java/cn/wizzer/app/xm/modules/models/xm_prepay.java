package cn.wizzer.app.xm.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/17 0017.
 */
@Table("xm_prepay")
public class xm_prepay extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("预付流水")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("账单编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String billid;

    @Column
    @Comment("雇员要求付款方式")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gypayid;

    @Column
    @Comment("实际付款方式")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String realgypayid;

    @Column
    @Comment("预付总额")
    @ColDefine(type = ColType.FLOAT)
    private float prepaycount;

    @Column
    @Comment("预付说明")
    @ColDefine(type = ColType.VARCHAR,width = 200)
    private String note;

    @Column
    @Comment("付款人")
    @ColDefine(type = ColType.VARCHAR,width = 32)
    private String payby;

    @Column
    @Comment("付款时间")
    @ColDefine(type = ColType.INT)
    private int at;

}
