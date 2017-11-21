package cn.wizzer.app.code.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/21 0021.
 */


@Table("gy_pay")
public class gy_pay extends BaseModel implements Serializable {

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
    @Comment("主要付款方式")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean first;

    @Column
    @Comment("是否禁用")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;
}
