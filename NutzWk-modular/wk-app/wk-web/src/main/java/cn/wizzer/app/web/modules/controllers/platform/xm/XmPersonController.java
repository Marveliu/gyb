package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.web.commons.services.xm.XmService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.NumberUtil;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.*;
import cn.wizzer.app.xm.modules.services.*;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@IocBean
@At("/platform/xm/person")
public class XmPersonController {
    private static final Log log = Logs.get();
    @Inject
    private XmApplyService xmApplyService;
    @Inject
    private XmFeedbackService xmFeedbackService;
    @Inject
    private XmInfService xmInfService;
    @Inject
    private XmTaskService xmTaskService;
    @Inject
    private XmService xmService;
    @Inject
    private XmBillService xmBillService;
    @Inject
    private NumberUtil numberUtil;

    // @Inject
    // private  V_XmInfService v_xmInfService;


    /**
     * 用户提交项目申请
     *
     * @param xmtaskid
     * @return
     */
    @At("/apply")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_apply", msg = "")
    public Object apply(
            @Param("xmtaskid") String xmtaskid) {
        try {
            String gyid = UserInfUtil.getCurrentGyid();

            if(null == xmtaskid){
                return Result.error("请选择申请的项目");
            }
            if(null!= xmApplyService.fetch(Cnd.where("gyid","=",gyid).and("xmtaskid","=",xmtaskid)))
            {
                return Result.error("你已经申请过了！");
            }
            xm_apply apply = new xm_apply();
            apply.setXmtaskid(xmtaskid);
            apply.setGyid(gyid);
            apply.setStatus(0);
            apply.setAt((int) (System.currentTimeMillis() / 1000));
            xmApplyService.insert(apply);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    /**
    * @function: 项目申请总览
    * @param:
    * @return:
    * @note:
    */
    @At("/applyindex")
    @Ok("beetl:/platform/xm/person/xmapply.html")
    @RequiresPermissions("platform.xm.person.applyindex")
    public void applyindex() {
    }

    /**
    * @function: 查看个人项目申请的记录
    * @param:
    * @return:
    * @note:
    */
    @At("/applydata")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object applydata(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        String gyid = UserInfUtil.getCurrentGyid();

        //查询雇员
        Cnd cnd = Cnd.NEW();
        cnd.and("gyid","=",gyid);
        //高级筛选
        return xmApplyService.data(length, start, draw, order, columns, cnd, null);
    }


    /**
    * @function: 个人开始的项目
    * @param:
    * @return:
    * @note:
    */
    @At("/personxm")
    @Ok("beetl:/platform/xm/person/xmfeedback.html")
    @RequiresPermissions("platform.xm.person")
    public void psersonxm() {
    }

    /**
    * @function: 加载个人已经开始的项目信息
    * @param:
    * @return:
    * @note:
    */
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
        String gyid = StringUtil.getGyid();

        if("".equals(xminfid)){
            cnd.and("gyid","=",gyid);
        }else{
            cnd.and("xminfid","=",xminfid);
        }
        cnd.desc("at");

        return xmFeedbackService.data(length, start, draw, order, columns, cnd, null);
    }


    @At("/feedbackdetail/?")
    @Ok("beetl:/platform/xm/feedback/detail.html")
    @RequiresPermissions("platform.xm.person")
    public void feedbackdetail(int id, HttpServletRequest req) {
        xm_feedback xfd = xmFeedbackService.fetch(id);
        String gyid = StringUtil.getGyid();
        if(xmService.checkGyForXm(xfd.getXminfid(),gyid)){
            if (id != 0) {
                req.setAttribute("obj", xmFeedbackService.fetch(Cnd.where("id","=",id)));
            }else{
                req.setAttribute("obj", null);
            }
        }
    }


    /**
    * @function: 添加反馈页面
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackadd/?")
    @Ok("beetl:/platform/xm/person/xmfeedbackadd.html")
    @RequiresPermissions("platform.xm.person")
    public void feedbackadd(String id,HttpServletRequest req) {
        String gyid = StringUtil.getGyid();
        Cnd cnd = Cnd.NEW();
        cnd.and("xminfid","=",id).and("gyid","=",gyid);
        // 验证当前用户是否为该项目雇佣人
        // TODO: 2018/1/13 0013 统一验证权限，并且设置权限访问错误界面
        if(xmService.checkGyForXm(id,gyid)){
            // 当前项目的反馈次数
            int count = xmFeedbackService.count(cnd);
            //设置父id
            if(count != 0){
                List<xm_feedback> xfd = xmFeedbackService.query(cnd.desc("at"));
                req.setAttribute("xmfeedbackparentid",xfd.get(0).getId());
            }
            req.setAttribute("count",count);
            req.setAttribute("xmid",id);
            req.setAttribute("taskname",  xmInfService.fetch(id).getTaskname());
        }
    }

    /**
     * 添加反馈操作
     * @param xmFeedback
     * @param req
     * @return
     */
    @At("/feedbackaddDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_feedback", msg = "")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object feedbackaddDo(@Param("..")xm_feedback xmFeedback, HttpServletRequest req) {
        String gyid = StringUtil.getGyid();
        if(xmService.checkGyForXm(xmFeedback.getXminfid(),gyid)) {
            try {
                xmFeedback.setCode(numberUtil.XfdIdGenerator(xmFeedbackService.getXfdCount(xmFeedback.getXminfid()), xmFeedback.getXminfid()));
                xmFeedback.setGyid(StringUtil.getGyid());
                xmFeedback.setOpBy(StringUtil.getGyid());
                xmFeedback.setOpAt((int) (System.currentTimeMillis() / 1000));
                xmFeedback.setReply(" ");
                xmFeedback.setAt((int) (System.currentTimeMillis() / 1000));
                xmFeedbackService.insert(xmFeedback);
                return Result.success("system.success");
            } catch (Exception e) {
                log.debug(e);
                return Result.error("system.error");
            }
        }
        return Result.error("system.error");
    }
    /**
    * @function: 添加反馈检查
    * @param:
    * @return:
    * @note:
    */
    @At("/addcheck/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object addcheck(String id,HttpServletRequest req) {
        //检查最新的反馈提交状态，如果未完结则不允许提交反馈
        String gyid = StringUtil.getGyid();

        //初次提交
        int count = xmFeedbackService.count(Cnd.where("xminfid","=",id));
        if(count == 0 ){
            return Result.success("action permmit!");
        }
        //检查上一次提交状态
        List<xm_feedback> xfd = xmFeedbackService.query(Cnd.where("xminfid","=",id).desc("at"));
        if(xfd.get(0).getStatus()<3){
            return Result.error("上次反馈未完结");
        }else if(xfd.get(0).getStatus() == 4){
            return Result.error("项目已经完结，无法添加反馈！");
        }else{
            return  Result.success("允许添加");
        }
    }


    /**
    * @function: 确认提交
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackcommit/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_feedback", msg = "")
    public Object feedbackcommit(
            @Param("id") String id) {
        try {
            xmFeedbackService.update(org.nutz.dao.Chain.make("Status",1),Cnd.where("id","=",id));
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    /**
    * @function: 能否修改检查
    * @param:
    * @return:
    * @note:
    */
    @At("/modifycheck/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object modifycheck(int id,HttpServletRequest req) {
        //检查feedback的审核状态，如果已经提交则雇员无法再修改或者删除
        xm_feedback xfd = xmFeedbackService.fetch(id);
        if (xfd.getStatus() == 0) {
            return Result.success("system.success");
        }
        return Result.error("当前反馈状态不允许修改哦！");
    }


    /**
     * 项目跟踪修改界面
     * @param id
     * @param req
     */
    @At("/feedbackedit/?")
    @Ok("beetl:/platform/xm/person/xmfeedbackedit.html")
    @RequiresPermissions("platform.xm.person")
    public void edit(int id,HttpServletRequest req) {
        xm_feedback xfd = xmFeedbackService.fetch(id);
        String gyid = StringUtil.getGyid();
        if(xmService.checkGyForXm(xfd.getXminfid(),gyid)){
            req.setAttribute("obj",xfd);
        }
        return;
    }

    /**
    * @function: 项目跟踪修改操作
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackeditDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_feedback", msg = "")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object feedbackeditDo(@Param("..")xm_feedback xmFeedback, HttpServletRequest req) {
        try {
            xmFeedback.setOpBy(StringUtil.getUid());
            xmFeedback.setOpAt((int) (System.currentTimeMillis() / 1000));
            xmFeedbackService.updateIgnoreNull(xmFeedback);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At
    @Ok("beetl:/platform//xm/person/xmcompleted.html")
    @RequiresPermissions("platform.xm.person")
    public void xmcompleted() {

    }

    @At
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object xmcompleteddata(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        String gyid = StringUtil.getGyid();

        cnd.and("status","=",2);
        cnd.and("gyid","=",gyid);

        return xmInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/xmcompleteddetail/?")
    @Ok("beetl:/platform/xm/person/xmcompleteddetail.html")
    @RequiresPermissions("platform.xm.person")
    public void xmcompleteddetail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            Cnd cnd = Cnd.where("xminfid","=",id);
            xm_bill bill = xmBillService.fetch(cnd);
            xm_inf  xf = xmInfService.fetch(id);
            bill = xmBillService.fetchLinks(bill,"gypay");
            req.setAttribute("obj", xf);
            req.setAttribute("bill",bill);
        }else{
            req.setAttribute("obj", null);
        }
    }

    @At("/xminfdetail/?")
    @Ok("beetl:/platform/xm/person/xminfdetail.html")
    @RequiresPermissions("platform.xm.person")
    public void xminfdetail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", xmInfService.fetch(Cnd.where("id","=",id)));
        }else{
            req.setAttribute("obj", null);
        }
    }

    @At("/xmcompletedcommit")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xmcompletedcommit", msg = "")
    public Object commit(
            @Param("id") String xminfid,
            @Param("gypayid") String gypayid,
            HttpServletRequest req) {
        try {
            int at =  (int) (System.currentTimeMillis() / 1000);
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    //账单转入财务，更新账单时间
                    xmBillService.update(org.nutz.dao.Chain.make("status",2).add("realgypayid",gypayid).add("at",at),Cnd.where("xminfid","=",xminfid));
                    //项目完结
                    xmInfService.update(org.nutz.dao.Chain.make("status",3),Cnd.where("id","=",xminfid));
                }
            });

            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    /**
    * @function: 项目信息加载
    * @param:
    * @return:
    * @note:
    */
    @At("/xminflist")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object xminflist() {
        String gyid = UserInfUtil.getCurrentGyid();
        //<name,id>
        List<xm_inf> xm_infs = xmInfService.query(Cnd.where("gyid","=",gyid));
        Map<String, String> obj = new HashMap<>();
        for(xm_inf inf:xm_infs){
            xm_task task = xmTaskService.fetch(inf.getXmtaskid());
            String taskname = task.getTaskname();
            obj.put(taskname,inf.getId());
        }
        return obj;
    }



}
