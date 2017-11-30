package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.*;
import cn.wizzer.app.xm.modules.services.*;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.DateUtil;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

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
    /**
    * @function: 受理用户提交任务书的申请
    * @param: 申请人编号，任务书编号
    * @return: 是否已经申请
    * @note:
    */
    @At("apply")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_apply", msg = "")
    public Object apply(
            @Param("xmtaskid") String xmtaskid) {
        try {
            String gyid = UserInfUtil.getCurrentGyid();
            if(null!= xmApplyService.fetch(Cnd.where("gyid","=",gyid).and("xmtaskid","=",xmtaskid)))
            {
                return Result.error("你已经申请过了！");
            }
            xm_apply apply = new xm_apply();
            apply.setXmtaskid(xmtaskid);
            apply.setGyid(gyid);
            apply.setStatus(0);
            apply.setAt(DateUtil.getTime(DateUtil.getDate()));
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
    @Ok("beetl:/platform/xm/person/apply.html")
    @RequiresPermissions("platform.xm.person.applyindex")
    public void applyindex() {
    }

    /**
    * @function: 查看各人项目申请的记录
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
    @At("/psersonxm")
    @Ok("beetl:/platform/xm/person/personxm.html")
    @RequiresPermissions("platform.xm.person")
    public void psersonxm() {
    }

    /**
    * @function: 加载个人开始项目信息
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackdata")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object feedbackdata(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        String gyid = UserInfUtil.getCurrentGyid();
        return xmFeedbackService.data(length, start, draw, order, columns, cnd, null);
    }


    /**
    * @function: 添加反馈页面
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackadd")
    @Ok("beetl:/platform/xm/person/addfeedback.html")
    @RequiresPermissions("platform.xm.person")
    public void feedbackadd() {

    }

    /**
    * @function: 添加反馈
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackaddDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_feedback", msg = "")
    public Object feedbackaddDo(@Param("..")xm_feedback xmFeedback, HttpServletRequest req) {
        try {
            String gyid = UserInfUtil.getCurrentGyid();

            xmFeedback.setAt((int) (System.currentTimeMillis() / 1000));
            xmFeedback.setGyid(gyid);
            xmFeedback.setStatus(0);;

            int count = xmFeedbackService.count(Cnd.where("gyid","=",gyid));

            if(count != 0){
                //设置父id
                List<xm_feedback> xfd = xmFeedbackService.query(Cnd.where("gyid","=",gyid).desc("at"));
                xmFeedback.setParentid(xfd.get(0).getId());
            }
            xmFeedbackService.insert(xmFeedback);
            return Result.success("system.success");

        } catch (Exception e) {
            return Result.error("system.error");
        }
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
        String gyid = UserInfUtil.getCurrentGyid();

        //初次提交
        int count = xmFeedbackService.count(Cnd.where("gyid","=",gyid));
        if(count == 0 ){
            return Result.success("action permmit!");
        }
        //检查上一次提交状态

        xm_feedback xfd = xmFeedbackService.query(Cnd.where("id","=",id).desc("at")).get(0);
        if(xfd.getStatus()<3){
            return Result.error("上次反馈为完结");
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
    @At("feedbackcommit")
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
    public Object modifycheck(String id,HttpServletRequest req) {
        //检查feedback的审核状态，如果已经提交则雇员无法再修改或者删除
        xm_feedback xfd = xmFeedbackService.fetch(id);
        if (xfd.getStatus() != 0) {
            return Result.success("system.success");
        }
        return Result.error("你已经确认提交！");
    }

    /**
    * @function: 修改页面
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackedit/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public void edit(String id,HttpServletRequest req) {
        req.setAttribute("obj", xmFeedbackService.fetch(id));
    }

    /**
    * @function: 修改
    * @param:
    * @return:
    * @note:
    */
    @At("/feedbackeditDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    @SLog(tag = "xm_feedback", msg = "")
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

    /**
    * @function: 项目信息加载
    * @param:
    * @return:
    * @note:
    */
    @At("xminflist")
    @Ok("json")
    @RequiresPermissions("platform.xm.person")
    public Object tree(@Param("pid") String pid) {
        String gyid = UserInfUtil.getCurrentGyid();

        //<name,id>
        List<xm_inf> xm_infs = xmInfService.query(Cnd.where("gyid","=",gyid));
        Map<String, String> obj = new HashMap<>();
        for(xm_inf inf:xm_infs){
            //要改试图
            obj.put(xmTaskService.fetch(inf.getXmtaskid()).getTaskname() ,inf.getId());
        }
        return obj;
    }


}
