package com.marveliu.framework.model.base;


import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.*;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.lang.random.R;
import org.nutz.mvc.Mvcs;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_UID = "405a28c9389d4a8581a29c283dc9f5b9";

    @Column
    @Comment("操作人")
    @Prev(els = @EL("$me.uid()"))
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String opBy;

    @Column
    @Comment("操作时间")
    @Prev(els = @EL("$me.now()"))
    @ColDefine(type = ColType.INT,width = 9)
    private Long opAt;

    @Column
    @Comment("删除标记")
    @Prev(els = @EL("$me.flag()"))
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean delFlag;

    public String toString() {
        return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }

    public Boolean flag() {
        return false;
    }

    public Long now() {
        return Times.getTS();
    }

    public String uid() {
        try {
            HttpServletRequest request = Mvcs.getReq();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("platform_uid"));
            }
        }catch (Exception e){}
        return Strings.sNull(DEFAULT_UID);
    }

    public String getOpBy() {
        return opBy;
    }

    public void setOpBy(String opBy) {
        this.opBy = opBy;
    }

    public Long getOpAt() {
        return opAt;
    }

    public void setOpAt(Long opAt) {
        this.opAt = opAt;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String uuid() {
        return R.UU32().toLowerCase();
    }
}
