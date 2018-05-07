package com.marveliu.app.web.modules.controllers.platform.xm;
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
import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.app.web.commons.utils.ShiroUtil;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.xm.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Marveliu
 * @since 04/05/2018
 **/

@IocBean
@At("/platform/xm/apply")
public class XmApplyController{
    private static final Log log = Logs.get();

    @Inject
    @Reference
    private XmApplyService xmApplyService;
    @Inject
    @Reference
    private XmTaskService xmTaskService;
    @Inject
    @Reference
    private XmInfService xmInfService;
    @Inject
    @Reference
    private XmBillService xmBillService;

    @Inject
    @Reference
    private XmFacadeService xmFacadeService;



    @Inject
    private ShiroUtil shiroUtil;

    @At("")
    @Ok("beetl:/platform/xm/apply/index.html")
    @RequiresPermissions("platform.xm.apply")
    public void index() {
    }


    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.apply")
    public Object data(
            @Param("xmtaskid") String xmtaskid,
            @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {

        Cnd cnd = Cnd.NEW();
        String sysuserid=StringUtil.getPlatformUid();

        // 权限管理

        //项目总监:项目总监的权限标识为sys.allpm,超管权限标识platform.xm.task.add.allpm
        if(!shiroUtil.hasAnyPermissions("platform.xm.task.add.allpm")){
            cnd.and("author","=", sysuserid);
        }
        // xmtaskid不为空
        if( xmtaskid != null && !xmtaskid.isEmpty()){
            cnd.and("xmtaskid","=",xmtaskid);
        }
        return xmApplyService.data(length, start, draw, order, columns, cnd, null);
    }


    @At("/detail/?")
    @Ok("beetl:/platform/xm/apply/detail.html")
    @RequiresPermissions("platform.xm.apply")
    @SLog(type="xm",tag = "查看任务申请", msg = "任务申请编号:${args[0]}")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            xm_apply apply = xmApplyService.fetch(id);
            req.setAttribute("obj", xmApplyService.fetch(id));
        }else{
            req.setAttribute("obj", null);
        }
    }


    @At({"/deal"})
    @Ok("json")
    @RequiresPermissions("platform.xm.apply.deal")
    @SLog(type = "xm",tag = "审批任务申请", msg = "任务申请编号:${args[0]},处理结果:${flag}")
    public Object deal(
            @Param("xmapplyid") String xmapplyid,
            @Param("flag") boolean flag,
            HttpServletRequest req) {
        try {
            // 申请通过
            if(flag){
                xm_inf xmInf = xmFacadeService.acceptXmapply(xmapplyid,StringUtil.getPlatformUid());
                if (Lang.isEmpty(xmInf))  return Result.error("任务书已经认领");
            }
            // 拒绝申请
            xmApplyService.setXmApplyStatus(xmapplyid,false,StringUtil.getPlatformUid());
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }





}