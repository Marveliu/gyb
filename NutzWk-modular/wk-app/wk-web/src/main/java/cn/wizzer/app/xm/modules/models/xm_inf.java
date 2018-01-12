package cn.wizzer.app.xm.modules.models;

import cn.wizzer.app.library.modules.models.lib_task;
import cn.wizzer.app.web.commons.util.NumberUtil;
import cn.wizzer.app.xm.modules.services.Impl.XmInfServiceImpl;
import cn.wizzer.app.xm.modules.services.Impl.XmTaskServiceImpl;
import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.*;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/28 0028.
 */
@Table("xm_inf")
public class xm_inf extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("项目编号")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Prev(els = {@EL("$me.xminfid()")})
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

    //参照
    @One(field = "xmtaskid")
    private xm_task xmtask;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGyid() {
        return gyid;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
    }

    public String getXmtaskid() {
        return xmtaskid;
    }

    public void setXmtaskid(String xmtaskid) {
        this.xmtaskid = xmtaskid;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @function: 立项编号
     * @param:
     * @return:
     * @note:
     */
    public String xminfid() {

        String id = new String();
        //顺序码
        Dao dao =  Mvcs.getIoc().get((Dao.class));
        try {
            int count = Mvcs.getIoc().get(XmTaskServiceImpl.class).count();
            return  id =  Mvcs.getIoc().get(NumberUtil.class).XminfidGenerator(count,dao.fetch(xm_task.class,this.xmtaskid).getId());
        }catch (Exception e){
            Logs.get().debug(e);
        }

        return id;
    }
}
