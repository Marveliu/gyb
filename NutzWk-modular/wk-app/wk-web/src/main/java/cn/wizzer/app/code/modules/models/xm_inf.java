package cn.wizzer.app.code.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/28 0028.
 */
@Table("xm_inf")
public class xm_inf extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("任务书编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("$me.taskid()")})
    private String id;

    @Column
    @Comment("雇员编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String gyid;

    @Column
    @Comment("任务书编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String xmtaskid;

    @Column
    @Comment("立项时间")
    @ColDefine(type = ColType.INT)
    private int at;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT)
    private int status;

    @Column
    @Comment("说明")
    @ColDefine(type = ColType.VARCHAR,width = 200)
    private String note;



}
