package cn.wizzer.app.xm.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by 89792 on 2017/11/28 0028.
 * 查询项目信息的视图
 */

@View("v_xminf")
public class v_xminf extends BaseModel implements Serializable {

    @Column("id")
    @Readonly
    private String id;

    @Column("gyid")
    @Readonly
    private String gyid;

    @Column("author")
    @Readonly
    private String author;

    @Column("taskname")
    @Readonly
    private String taskname;




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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }
}
