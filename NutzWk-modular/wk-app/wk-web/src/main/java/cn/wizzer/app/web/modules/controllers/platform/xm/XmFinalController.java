package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.gy.modules.models.gy_pay;
import cn.wizzer.app.gy.modules.services.GyPayService;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.app.web.commons.util.UserInfUtil;
import cn.wizzer.app.xm.modules.models.v_xminf;
import cn.wizzer.app.xm.modules.models.xm_evaluation;
import cn.wizzer.app.xm.modules.services.*;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.*;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@At("/platform/xm/final")
public class XmFinalController {
    private static final Log log = Logs.get();

    @Inject
    private XmInfService xmInfService;

    @Inject
    private XmEvaluationService xmEvaluationService;

    @Inject
    private XmBillService xmBillService;

    @Inject
    private GyPayService gyPayService;

    @Inject
    private V_XmInfService v_xmInfService;

    @At("")
    @Ok("beetl:/platform//xm/final/index.html")
    @RequiresPermissions("platform.xm.final")
    public void index( HttpServletRequest req) {
        int total = 0;
        int doing = 0;
        int done = 0;
        int finish = 0;

        total =  xmInfService.count();
        doing = xmInfService.count(Cnd.where("status","=",0));
        done = xmInfService.count(Cnd.where("status","=",1));
        finish = xmInfService.count(Cnd.where("status","=",2));

        req.setAttribute("total",total);
        req.setAttribute("doing", doing);
        req.setAttribute("final", done);
        req.setAttribute("finish", finish);
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.final")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        cnd.and("status","=",1);
        return v_xmInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/detail/?")
    @Ok("beetl:/platform/xm/final/detail.html")
    @RequiresPermissions("platform.xm.final")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", v_xmInfService.fetch(Cnd.where("id","=",id)));
        }else{
            req.setAttribute("obj", null);
        }
    }

    @At("/item/?")
    @Ok("beetl:/platform/xm/final/item.html")
    @RequiresPermissions("platform.xm.final")
    public void item(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", v_xmInfService.fetch(Cnd.where("id","=",id)));
        }else{
            req.setAttribute("obj", null);
        }
    }

    @At("/commit")
    @Ok("json")
    @RequiresPermissions("platform.xm.final")
    @SLog(tag = "xmfinal", msg = "")
    public Object commit(
            @Param("id") String id,
            @Param("grade") float grade,
            @Param("evanote")String evanote,
            @Param("paysum")float paysum,
            @Param("billnote")String billnote,
            HttpServletRequest req) {
        try {
            String gzid = UserInfUtil.getCurrentGzid();
            v_xminf xmf = v_xmInfService.fetch(Cnd.where("id","=",id));
            int at =  (int) (System.currentTimeMillis() / 1000);
                    Trans.exec(new Atom() {
                @Override
                public void run() {
                    //评价
                    xm_evaluation eva = new xm_evaluation();
                    eva.setOpBy(gzid);
                    eva.setOpAt(at);
                    eva.setXminfid(id);
                    eva.setGrade(grade);
                    eva.setNote(evanote);
                    xmEvaluationService.insert(eva);

                    //账单
                    gy_pay pay = gyPayService.getFirstPay(xmf.getGyid());
                    xmBillService.update(org.nutz.dao.Chain.make("status",1).add("gypayid",pay.getId()).add("note",billnote).add("at",at),Cnd.where("xminfid","=",id));

                    //项目状态
                    xmInfService.update(org.nutz.dao.Chain.make("status",2),Cnd.where("id","=",id));
                }
            });
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


}
