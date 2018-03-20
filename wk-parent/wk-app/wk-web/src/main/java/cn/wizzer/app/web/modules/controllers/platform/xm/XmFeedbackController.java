package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.v_xminf;
import cn.wizzer.app.xm.modules.models.xm_feedback;
import cn.wizzer.app.xm.modules.models.xm_inf;
import cn.wizzer.app.xm.modules.models.xm_task;
import cn.wizzer.app.xm.modules.services.*;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.*;
import org.nutz.dao.Chain;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.WhaleFilter;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.view.UTF8JsonView;
import org.nutz.mvc.view.ViewWrapper;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@IocBean
@At("/platform/xm/feedback")
public class XmFeedbackController{
    private static final Log log = Logs.get();

    @Inject
    private XmFeedbackService xmFeedbackService;

    @Inject
    private XmTaskService xmTaskService;

    @Inject
    private XmInfService xmInfService;

    @Inject
    private Dao dao;

    @Inject
    private V_XmInfService v_xmInfService;

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
            @Param("xmid") String xminfid,
            @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
        String sysuserid = StringUtil.getSysuserid();
        if("".equals(xminfid)){
            //默认查看该雇员经理下面所有的项目反馈
            cnd.and("authorrealname","like","%" + authorname + "%");
        }else{
            //查看指定id
            cnd.and("xminfid","=",xminfid);
        }
    	return xmFeedbackService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/xm/feedback/add.html")
    @RequiresPermissions("platform.xm.feedback")
    public void add() {

    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.add")
    @SLog(tag = "xm_feedback", msg = "${args[0].id}")
    public Object addDo(@Param("..")xm_feedback xmFeedback, HttpServletRequest req) {
		try {
			xmFeedbackService.insert(xmFeedback);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/feedback/edit.html")
    @RequiresPermissions("platform.xm.feedback")
    public View edit(int id, HttpServletRequest req) {
        String author = xmFeedbackService.fetch(Cnd.where("id","=",id)).getAuthor();
        String sysuserid = StringUtil.getSysuserid();
        if(author.equals(sysuserid)){
            req.setAttribute("obj", xmFeedbackService.fetch(id));
        }else {
            return new ViewWrapper(new UTF8JsonView(), "It is not your bussiness!");
        }
        return null;
    }

    // 项目审核
    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.edit")
    @SLog(tag = "xm_feedback", msg = "")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(
            @Param("final") boolean iffinal,
            @Param("nextcommitat") String nextcommit,
            @Param("..")xm_feedback xmFeedback, HttpServletRequest req) {
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            xm_feedback xfd = xmFeedbackService.fetch(xmFeedback.getId());
            xfd.setStatus(2);
            if(iffinal){
                //反馈完结
                xfd.setStatus(4);
                dao.update(xm_inf.class, Chain.make("status",1),Cnd.where("id","=",xfd.getXminfid()));
            }
            int nextcommitat = (int) (sdf.parse(nextcommit).getTime() / 1000);
            xfd.setReply(xmFeedback.getReply());
            xfd.setOpBy(StringUtil.getSysuserid());
            xfd.setOpAt((int) (System.currentTimeMillis() / 1000));
            xfd.setNextcommit(nextcommitat);
			xmFeedbackService.updateIgnoreNull(xfd);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.delete")
    @SLog(tag = "xm_feedback", msg = "${req.getAttribute('id')}")
    public Object delete(String id, @Param("ids")  String[] ids, HttpServletRequest req) {
		try {
			if(ids!=null&&ids.length>0){
				xmFeedbackService.delete(ids);
    			req.setAttribute("id", org.apache.shiro.util.StringUtils.toString(ids));
			}else{
				xmFeedbackService.delete(id);
    			req.setAttribute("id", id);
			}
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/feedback/detail.html")
    @RequiresPermissions("platform.xm.feedback")
	public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            Cnd cnd = Cnd.NEW();
            cnd.and("code","=",id);
//            String sysuserid = StringUtil.getSysuserid();
//            Subject subject = SecurityUtils.getSubject();
//            if(!subject.isPermitted("platform.xm.feedback.manager")){
//                cnd.and("author","=",sysuserid);
//            }
            xm_feedback feedback = xmFeedbackService.fetch(cnd);
            req.setAttribute("obj", feedback);
        }else{
            req.setAttribute("obj", null);
        }
    }

    /**
     * @function: 查询当前雇员经理负责的所有项目
     * @param:
     * @return:
     * @note:
     */
    @At("/xminflist")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback")
    public Object xminflist(){

        String sysuserid = StringUtil.getSysuserid();

        //查询视图
        List<v_xminf> vxminfs = v_xmInfService.query(Cnd.where("author","=",sysuserid));
        Map<String, String> obj = new HashMap<>();

        for(v_xminf inf:vxminfs){
            String taskname = inf.getTaskname();
            obj.put(taskname,inf.getId());
        }

        return obj;
    }


    /**
     * 审核提交
     * @param id
     * @return
     */
    @At("/feedbackcommit/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.feedback.edit")
    @SLog(tag = "xm_feedback", msg = "")
    public Object feedbackcommit(
            @Param("id") int id) {
        try {
            Trans.exec(new Atom() {
                @Override
                public void run() {
                    //修改feedback
                    xmFeedbackService.update(org.nutz.dao.Chain.make("Status",3),Cnd.where("id","=",id));
                    //更新项目信息
                    xmInfService.update(org.nutz.dao.Chain.make("Status",4),Cnd.where("id","=",xmFeedbackService.fetch(id).getXminfid()));
                }
            });

            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    public Object checkstatus(int id){
        xm_feedback xfd = xmFeedbackService.fetch(id);
        if(xfd.getStatus() < 2){
            return Result.error("system.error");
        }else {
            return true;
        }
    }
}
