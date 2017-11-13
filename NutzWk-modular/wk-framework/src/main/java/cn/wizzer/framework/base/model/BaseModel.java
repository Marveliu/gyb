package cn.wizzer.framework.base.model;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Marvleiu on 2016/6/21.
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Comment("操作人")
    @Prev(els = @EL("$me.uid()"))
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String opBy;

    @Column
    @Comment("操作时间")
    @Prev(els = @EL("$me.now()"))
    @ColDefine(type = ColType.INT)
    private Integer opAt;

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

    public Integer now() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public String uid() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                return Strings.sNull(subject.getSession(true).getAttribute("uid"));
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String getOpBy() {
        return opBy;
    }

    public void setOpBy(String opBy) {
        this.opBy = opBy;
    }

    public Integer getOpAt() {
        return opAt;
    }

    public void setOpAt(Integer opAt) {
        this.opAt = opAt;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
    * @function: UU32
    * @param:
    * @return:
    * @note:
    */
    public String uuid() {
        return R.UU32().toLowerCase();
    }

    /**
     * 日期编号
     * 生成规则：当天日期[8位]+序列号[3至24位]，如：201008010000001
     * @return
     */
    private String RandomBatchNum()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(new Date());
        int max=24;
        int min=3;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        StringBuffer buffer =new StringBuffer();
        for(int i=0;i<s;i++)
        {
            Integer val = (int)(Math.random()*9+1);
            buffer.append(val.toString());
        }
        return format+buffer.toString();
    }

}
