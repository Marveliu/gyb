package com.marveliu.app.web.modules.controllers.front.gy;
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
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.xm.*;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.gy.GyFacadeService;
import com.marveliu.framework.services.gy.GyInfService;
import com.marveliu.framework.services.gy.GyPayService;
import com.marveliu.framework.services.xm.*;
import com.marveliu.framework.util.ConfigUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.core.misc.NumberUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marveliu
 * @since 17/05/2018
 **/


@IocBean
@At("/platform/xm/person")
public class XmPersonController {
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private XmApplyService xmApplyService;
    @Inject
    @Reference
    private XmFeedbackService xmFeedbackService;
    @Inject
    @Reference
    private XmInfService xmInfService;
    @Inject
    @Reference
    private XmTaskService xmTaskService;
    @Inject
    @Reference
    private XmFacadeService xmFacadeService;
    @Inject
    @Reference
    private XmBillService xmBillService;
    @Inject
    @Reference
    private GyInfService gyInfService;
    @Inject
    @Reference
    private GyPayService gyPayService;
    @Inject
    @Reference
    private GyFacadeService gyFacadeService;

    @Inject
    private ShiroUtil shiroUtil;


    /**
     * 用户提交项目申请
     *
     * @param xmtaskid
     * @return
     */
    @At("/apply")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(type = "xm", tag = "项目申请", msg = "${args[0]}")
    public Object apply(@Param("xmtaskid") String xmtaskid) {
        try {
            if (Strings.isBlank(xmtaskid)) {
                return Result.error("请选择申请的项目");
            }
            if (!shiroUtil.hasAnyRoles("gy4")) {
                return Result.error("您不是正式会员，暂时不能申请任务!");
            }
            gy_inf gyInf = gyInfService.getGyByUserId(StringUtil.getPlatformUid());
            List<gy_pay> gyPayList = gyFacadeService.getPaysByGyid(gyInf.getId());
            if (Lang.isEmpty(gyPayList)) {
                return Result.error("请先设置您的收款方式!");
            }

            if (xmApplyService.addXmApply(xmtaskid, gyInf.getId(), true)) {
                return Result.success("system.success");
            }
        } catch (Exception e) {
            log.error("申请任务书失败",e);
        }
        return Result.error("system.error");
    }


    @At("/applyindex")
    @Ok("beetl:/platform/xm/person/xmapply.html")
    @RequiresPermissions("platform.xm.person.applyindex")
    public void applyindex() { }

    @At("/applydata")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object applydata(
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {
        String gyid =  shiroUtil.getCurrentGyid();
        //查询雇员
        Cnd cnd = Cnd.NEW();
        cnd.and("gyid", "=", gyid);
        //高级筛选
        return xmApplyService.data(length, start, draw, order, columns, cnd, null);
    }



    @At("/personxm")
    @Ok("beetl:/platform/xm/person/xmfeedback.html")
    @RequiresPermissions("platform.xm.person")
    public void psersonxm() {
    }


    @At("/feedbackdata")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object feedbackdata(
            @Param("xmid") String xminfid,
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        String gyid =  shiroUtil.getCurrentGyid();
        if (Strings.isBlank(xminfid)) {
            cnd.and("gyid", "=", gyid);
        } else {
            cnd.and("xminfid", "=", xminfid);
        }
        cnd.desc("at");
        return xmFeedbackService.data(length, start, draw, order, columns, cnd, null);
    }


    @At("/feedbackdetail/?")
    @Ok("beetl:/platform/xm/person/xmfeedbackdetail.html")
    @RequiresPermissions("platform.xm.person")
    public void feedbackdetail(int id, HttpServletRequest req) {
        xm_feedback xfd = xmFeedbackService.fetch(id);
        String gyid =  shiroUtil.getCurrentGyid();
        if (xmFacadeService.isGyForXm(xfd.getXminfid(), gyid)) {
            if (id != 0) {
                req.setAttribute("obj", xmFeedbackService.fetch(Cnd.where("id", "=", id)));
            } else {
                req.setAttribute("obj", null);
            }
        }
    }


    @At("/feedbackadd/?")
    @Ok("beetl:/platform/xm/person/xmfeedbackadd.html")
    @RequiresPermissions("platform.xm.person")
    public void feedbackadd(String id, HttpServletRequest req) {
        String gyid =  shiroUtil.getCurrentGyid();
        Cnd cnd = Cnd.NEW();
        cnd.and("xminfid", "=", id).and("gyid", "=", gyid);
        if (xmFacadeService.isGyForXm(id, gyid)) {
            // 当前项目的反馈次数
            int count = xmFeedbackService.count(cnd);
            //设置父id
            if (count != 0) {
                List<xm_feedback> xfd = xmFeedbackService.query(cnd.desc("at"));
                req.setAttribute("xmfeedbackparentid", xfd.get(0).getId());
            }
            req.setAttribute("count", count);
            req.setAttribute("xmid", id);
            req.setAttribute("taskname", xmInfService.fetch(id).getTaskname());
        }
    }


    @At("/feedbackaddDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @AdaptBy(type = WhaleAdaptor.class)
    @SLog(type = "xm",tag = "雇员添加项目反馈", msg = "${args[0]}")
    public Object feedbackaddDo(@Param("..") xm_feedback xmFeedback, HttpServletRequest req) {
        String gyid =  shiroUtil.getCurrentGyid();
        if (xmFacadeService.isGyForXm(xmFeedback.getXminfid(), gyid)) {
            try {
                xmFeedback.setGyid(gyid);
                xmFeedback.setReply(" ");
                if(!Lang.isEmpty(xmFeedbackService.addXmfeedback(xmFeedback))){
                    return Result.success("system.success");
                }
            } catch (Exception e) {
                log.error(e);
            }
        }
        return Result.error("system.error");
    }



    @At("/feedbackcommit/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(type = "xm",tag = "雇员提交项目反馈", msg = "${args[0]}")
    public Object feedbackcommit(@Param("id") Long id) {
        try {
            if(xmFeedbackService.commitXmfeedback(id)){
                return Result.success("system.success");
            }
        } catch (Exception e) {
            log.error("反馈提交失败",e);
        }
        return Result.error("system.error");
    }


    @At("/modifycheck/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object modifycheck(String id, HttpServletRequest req) {
        if (xmFeedbackService.isXmeedbackAllowed(id)) {
            return Result.success("system.success");
        }
        return Result.error("当前反馈状态不允许修改哦！");
    }


    /**
     * 项目跟踪修改界面
     *
     * @param id
     * @param req
     */
    @At("/feedbackedit/?")
    @Ok("beetl:/platform/xm/person/xmfeedbackedit.html")
    @RequiresPermissions("platform.xm.person")
    public void edit(int id, HttpServletRequest req) {
        xm_feedback xfd = xmFeedbackService.fetch(id);
        String gyid =  shiroUtil.getCurrentGyid();
        if (xmFacadeService.isGyForXm(xfd.getXminfid(), gyid)) {
            req.setAttribute("obj", xfd);
        }
    }

    // 项目反馈修改
    @At("/feedbackeditDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(type = "xm",tag = "雇员修改项目反馈", msg = "${args[0].id}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object feedbackeditDo(
            @Param("..") xm_feedback xmFeedback,
            HttpServletRequest req) {
        try {
            xmFeedback.setOpBy(StringUtil.getPlatformUid());
            xmFeedback.setOpAt(Times.getTS());
            xmFeedbackService.updateIgnoreNull(xmFeedback);
            return Result.success("system.success");
        } catch (Exception e) {
            log.error("修改项目反馈失败");
        }
        return Result.error("system.error");
    }

    // 项目结算
    @At
    @Ok("beetl:/platform//xm/person/xmcompleted.html")
    @RequiresPermissions("platform.xm.person")
    public void xmcompleted() { }

    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object xmcompleteddata(
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        String gyid =  shiroUtil.getCurrentGyid();
        cnd.and("status", "=", ConfigUtil.XM_INF_CHECKING);
        cnd.and("gyid", "=", gyid);
        return xmInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/xmcompleteddetail/?")
    @Ok("beetl:/platform/xm/person/xmcompleteddetail.html")
    @RequiresPermissions("platform.xm.person")
    public void xmcompleteddetail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            Cnd cnd = Cnd.where("xminfid", "=", id);
            xm_bill bill = xmBillService.fetch(cnd);
            xm_inf xf = xmInfService.fetch(id);
            bill = xmBillService.fetchLinks(bill, "gypay");
            req.setAttribute("obj", xf);
            req.setAttribute("bill", bill);
        } else {
            req.setAttribute("obj", null);
        }
    }

    @At("/xminfdetail/?")
    @Ok("beetl:/platform/xm/person/xminfdetail.html")
    @RequiresPermissions("platform.xm.person")
    public void xminfdetail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", xmInfService.fetch(Cnd.where("id", "=", id)));
        } else {
            req.setAttribute("obj", null);
        }
    }

    // 雇员提交任务
    @At("/xmcompletedcommit")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(type = "xm",tag = "雇员提交任务结算", msg = "${args[0]},${args[1]}")
    public Object commit(
            @Param("id") String xminfid,
            @Param("gypayid") String gypayid,
            HttpServletRequest req) {
        try {
            if(Lang.isEmpty(gyPayService.fetch(gypayid))) return Result.error("收款方式不存在");
            if(xmFacadeService.checkXmFinal(xminfid,gypayid)){
                return Result.success("system.success");
            }
        } catch (Exception e) {
            log.error("雇员提交任务失败，任务编号:"+xminfid,e);
        }
        return Result.error("system.error");
    }


    // 项目信息加载
    @At("/xminflist")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object xminflist() {
        String gyid =  shiroUtil.getCurrentGyid();
        List<xm_inf> xm_infs = xmInfService.query(Cnd.where("gyid", "=", gyid));
        Map<String, String> obj = new HashMap<>();
        for (xm_inf inf : xm_infs) {
            xm_task task = xmTaskService.fetch(inf.getXmtaskid());
            String taskname = task.getTaskname();
            obj.put(taskname, inf.getId());
        }
        return obj;
    }
}
