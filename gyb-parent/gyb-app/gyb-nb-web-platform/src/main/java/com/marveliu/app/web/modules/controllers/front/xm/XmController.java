package com.marveliu.app.web.modules.controllers.front.xm;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.page.OffsetPager;
import com.marveliu.framework.services.library.LibSkillService;
import com.marveliu.framework.services.library.LibTaskService;
import com.marveliu.framework.services.xm.XmLimitService;
import com.marveliu.framework.services.xm.XmTaskService;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Marveliu
 * @since 15/05/2018
 **/

@IocBean
@At("/public/xm")
public class XmController {

    private static final Log log = Logs.get();

    @Inject
    @Reference
    private XmTaskService xmTaskService;

    @Inject
    @Reference
    private XmLimitService xmLimitService;

    @Inject
    @Reference
    private LibTaskService libTaskService;

    @Inject
    @Reference
    private LibSkillService libSkillService;


    @At("")
    @Ok("beetl:/public/xm/home.html")
    public void home(HttpServletRequest req) {
        // req.setAttribute("count",xmTaskService.count(Cnd.where("disabled","=","false").and("status","=",1)));
    }

    @At
    @Ok("json:full")
    public Object data(
            @Param("pageIndex") int start,
            @Param("pageCount") int length,
            @Param("category") String category,
            @Param("SearchType") int SearchType,
            @Param("taskName") String taskname
    ) {
        // TODO: 24/03/2018 根据技能种类选择对应的项目
        Cnd cnd = Cnd.NEW();
        if(length == 0 ){
            length = 5;     // 当前页大小
        }
        String linkName = null;
        NutMap re = new NutMap();
        Pager pager = new OffsetPager(start, length);
        // 处于申请阶段并且发布的任务书
        // cnd.and("disabled","=","false").and("status","=",1);
        // todo: for test
        // 查询名称
        if (!Strings.isBlank(taskname)) {
            cnd.and("taskName", "like", "%" + taskname + "%");
        }else{
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
        }


        List<?> list = xmTaskService.query(cnd, pager);
        if (!Strings.isBlank(linkName)) {
            xmTaskService.fetchLinks(list, linkName);
        }


        re.put("data", list);
        re.put("listCount", xmTaskService.count(cnd));
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
