package cn.wizzer.app.library.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/17 0017.
 */

//技能库
@Table("lib_skill")
@TableIndexes({@Index(name = "INDEX_SYS_UNIT_PATH", fields = {"path"}, unique = true)})
public class lib_skill extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("父级ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String parentId;

    @Column
    @Comment("树路径")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String path;

    @Column
    @Comment("技能名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;

    @Column
    @Comment("技能别称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String aliasName;

    @Column
    @Comment("技能编码")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String unitcode;

    @Column
    @Comment("技能介绍")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    @Column
    @Comment("链接网站")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String website;

    @Column
    @Comment("排序字段")
    @Prev({
            @SQL(db= DB.MYSQL,value = "SELECT IFNULL(MAX(location),0)+1 FROM sys_unit"),
            @SQL(db= DB.ORACLE,value = "SELECT COALESCE(MAX(location),0)+1 FROM sys_unit")
    })
    private Integer location;

    @Column
    @Comment("有子节点")
    private boolean hasChildren;



}
