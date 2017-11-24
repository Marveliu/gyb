package cn.wizzer.app.web.modules.controllers.front.xm;

import cn.wizzer.app.gz.modules.services.GzInfService;
import cn.wizzer.app.library.modules.services.LibSkillService;
import cn.wizzer.app.library.modules.services.LibTaskService;
import cn.wizzer.app.xm.modules.models.xm_task;
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
    public void home() {
    }


    /**
    * @function:
    * @param: start 当前页 页数大小
    * @return:
    * @note: 支持筛选: 最近时间发布,金额最高,技能匹配,任务类别,名称匹配
    */
    @At
    @Ok("json:full")
    public Object data(
            @Param("SearchFilter") String searchfilter,
            @Param("taskName") int taskname,
            @Param("start") int start
    ){


        Cnd cnd = Cnd.NEW();
        int length = 16;                //当前页大小
        String linkName = "xmlimits";   //linksname

        NutMap re = new NutMap();
        Pager pager = new OffsetPager(start, length);

        re.put("recordsFiltered", dao.count(xm_task.class, cnd));
        List<?> list = dao.query(xm_task.class, cnd, pager);

        if (!Strings.isBlank(linkName)) {
            dao.fetchLinks(list, linkName);
        }
        re.put("data", list);
        re.put("recordsTotal", length);
        return re;
    }
}
