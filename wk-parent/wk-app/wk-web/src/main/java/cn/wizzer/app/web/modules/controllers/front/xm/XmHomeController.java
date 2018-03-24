package cn.wizzer.app.web.modules.controllers.front.xm;

import cn.wizzer.app.gz.modules.services.GzInfService;
import cn.wizzer.app.library.modules.services.LibSkillService;
import cn.wizzer.app.library.modules.services.LibTaskService;
import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.app.xm.modules.services.XmLimitService;
import cn.wizzer.app.xm.modules.services.XmTaskService;
import cn.wizzer.framework.page.OffsetPager;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 89792 on 2017/11/24 0024.
 */
@IocBean
@At("/public/xm")
public class XmHomeController {
    @Inject
    private Dao dao;

    @Inject
    private XmTaskService xmTaskService;

    @Inject
    private XmLimitService xmLimitService;

    @Inject
    private LibTaskService libTaskService;

    @Inject
    private LibSkillService libSkillService;

    @Inject
    private GzInfService gzInfService;

    /**
    * @function:
    * @param: type
    * @return:
    * @note:
    */
    @At("")
    @Ok("beetl:/public/xm/home.html")
    public void home(HttpServletRequest req) {
        req.setAttribute("count",xmTaskService.count(Cnd.where("disabled","=","false").and("status","=",1)));
    }


    /**
     * 
     * 
     * 项目广场分页
     * @param category
     * @param SearchType
     * @param taskname
     * @param start
     * @return
     */
    @At
    @Ok("json:full")
    public Object data(
            @Param("category") String category,
            @Param("SearchType") int SearchType,
            @Param("taskName") String taskname,
            @Param("start") int start
    ) {

        // TODO: 24/03/2018 根据技能种类选择对应的项目
        
        Cnd cnd = Cnd.NEW();
        // 当前页大小
        int length = 16;
        // linksname
        String linkName = null;
        NutMap re = new NutMap();
        Pager pager = new OffsetPager(start, length);


        // 处于申请阶段并且发布的任务书
        cnd.and("disabled","=","false").and("status","=",1);


        // 查询名称
        if (!Strings.isBlank(taskname)) {
            cnd.and("taskName", "like", "%" + taskname + "%");
            re.put("listCount", dao.count(xm_task.class, cnd));
            List<?> list = dao.query(xm_task.class, cnd, pager);
            if (!Strings.isBlank(linkName)) {
                dao.fetchLinks(list, linkName);
            }
            re.put("data", list);
            re.put("recordsTotal", length);
            return re;
        }

        // 查询方式
        switch (SearchType) {
            case 0:
                //全部
            case 1:
                // 时间发布
                cnd.asc("applyendtime");
            case 2:
                // 金额
                cnd.desc("award");
            case 3:
                // 技能匹配
            default:
        }

        re.put("listCount", dao.count(xm_task.class));
        List<?> list0 = dao.query(xm_task.class,cnd,pager);
        dao.fetchLinks(list0,null);
        re.put("data", list0);
        re.put("recordsTotal", length);
        return re;
    }


    @At("/task/?")
    @Ok("beetl:/public/xm/task.html")
    public Object task(String id, HttpServletRequest req) {


        xm_task task = xmTaskService.fetchLinks(xmTaskService.fetch(id),"xmlimits");
        return task;

    }
}
