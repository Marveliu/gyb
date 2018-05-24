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
import com.marveliu.framework.model.xm.xm_feedback;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.sys.SysUserinfService;
import com.marveliu.framework.services.xm.XmFeedbackService;
import com.marveliu.framework.services.xm.XmInfService;
import com.marveliu.framework.services.xm.XmTaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.UTF8JsonView;
import org.nutz.mvc.view.ViewWrapper;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Marveliu
 * @since 07/05/2018
 **/

@IocBean
@At("/platform/xm/feedback")
public class XmFeedbackController {
    private static final Log log = Logs.get();

    @Inject
    @Reference
    private XmFeedbackService xmFeedbackService;

    @Inject
    @Reference
    private XmTaskService xmTaskService;

    @Inject
    @Reference
    private XmInfService xmInfService;

    @Inject
    @Reference
    private SysUserinfService sysUserinfService;

    @Inject
    private ShiroUtil shiroUtil;


    @At("")
    @Ok("beetl:/platform/xm/feedback/index.html")
    @RequiresPermissions("platform.xm.feedback")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback")
    public Object data(
            @Param("authorname") String authorname,
            @Param("xminfid") String xminfid,
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {


        Cnd cnd = Cnd.where("status", "!=", 0);
        if (!Strings.isEmpty(xminfid)) {
            cnd.and("xminfid", "=", xminfid);
        }
        if (!Strings.isEmpty(authorname)) {
            cnd.and("authorrealname", "like", "%" + authorname + "%");
        }

        return xmFeedbackService.data(length, start, draw, order, columns, cnd, null);
    }


    @At("/edit/?")
    @Ok("beetl:/platform/xm/feedback/edit.html")
    @RequiresPermissions("platform.xm.feedback")
    public View edit(int id, HttpServletRequest req) {
        String author = xmFeedbackService.fetch(Cnd.where("id", "=", id)).getAuthor();
        if (shiroUtil.isSuper() || author.equals(sysUserinfService.getSysuserinfid(StringUtil.getPlatformUid()))) {
            xm_feedback xmFeedback = xmFeedbackService.fetch(id);
            req.setAttribute("obj", xmFeedback);
        } else {
            return new ViewWrapper(new UTF8JsonView(), "It is not your bussiness!");
        }
        return null;
    }

    // 项目审核
    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.edit")
    @AdaptBy(type = WhaleAdaptor.class)
    @SLog(type = "xm", tag = "项目经理审核反馈", msg = "反馈编号:{args[0]}")
    public Object editDo(
            @Param("xmfeedbackid") long xmfeedbackid,
            @Param("nextcommitat") String nextcommit,
            @Param("reply") String reply,
            HttpServletRequest req) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int nextcommitat = (int) (sdf.parse(nextcommit).getTime() / 1000);
            if (xmFeedbackService.checkXmfeedback(xmfeedbackid,nextcommitat,reply,StringUtil.getPlatformUid())){
                return Result.success("system.success");
            }
        } catch (Exception e) {
            return Result.error("system.error");
        }
        return Result.error("system.error");
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/feedback/detail.html")
    @RequiresPermissions("platform.xm.feedback")
    // @SLog(type = "xm", tag = "查看任务反馈详细信息", msg = "任务反馈编号:${args[0]}")
    public void detail(long xmfeedbackid, HttpServletRequest req) {
        if (isAllowForFeedback(xmfeedbackid)) {
            xm_feedback feedback = xmFeedbackService.fetch(xmfeedbackid);
            req.setAttribute("obj", feedback);
        } else {
            req.setAttribute("obj", null);
        }
    }


    @At("/feedbackcommit")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.edit")
    @SLog(type = "xm", tag = "提交反馈信息", msg = "任务反馈编号:${args[0]},是否为最终反馈：${flag}")
    public Object feedbackcommit(
            @Param("xmfeedbackid") long xmfeedbackid,
            @Param("flag") boolean flag,
            HttpServletRequest req) {
        if (isAllowForFeedback(xmfeedbackid)) {
            if (xmFeedbackService.confirmXmfeedback(xmfeedbackid, flag)) return Result.success("提交反馈信息成功，请刷新界面!");
        }else {
            return Result.error("你没有权限进行操作！");
        }
        return Result.error("system.error");
    }

    // 能否对某一任务书进行修改等操作
    private boolean isAllowForFeedback(long xmfeedbackid) {
        if (shiroUtil.isSuper()) return true;
        xm_task xmTask = xmFeedbackService.getXmtaskByXmfeedbackid(xmfeedbackid);
        if (!Lang.isEmpty(xmTask)) {
            return xmTask.getAuthor().equals(sysUserinfService.getSysuserinfid(StringUtil.getPlatformUid()));
        }
        return false;
    }

}
