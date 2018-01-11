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
        req.setAttribute("count",xmTaskService.count(Cnd.where("disabled","=","false")));
    }


    /**
     * 项目广场分页
     * @param category
     * @param searchfilter
     * @param taskname
     * @param start
     * @return
     */
    @At
    @Ok("json:full")
    public Object data(
            @Param("category") String category,
            @Param("SearchFilter") int searchfilter,
            @Param("taskName") String taskname,
            @Param("start") int start
    ) {

        Cnd cnd = Cnd.NEW();
        cnd.and("disabled","=","false");
        int length = 16;                //当前页大小
        String linkName = null;         //linksname
        NutMap re = new NutMap();
        Pager pager = new OffsetPager(start, length);
        //查询名称
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

        switch (searchfilter) {
            case 0:             //全部
                re.put("listCount", dao.count(xm_task.class));
                List<?> list = dao.query(xm_task.class,cnd,pager);
                dao.fetchLinks(list,null);
                re.put("data", list);
                re.put("recordsTotal", length);
                return re;
            case 1:             //时间发布
            case 2:             //金额
            case 3:             //技能匹配
            default:
        }
        return null;
    }


    @At("/task/?")
    @Ok("beetl:/public/xm/task.html")
    public Object task(String id, HttpServletRequest req) {


        xm_task task = xmTaskService.fetchLinks(xmTaskService.fetch(id),"xmlimits");
        return task;

    }
}
