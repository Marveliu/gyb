package cn.wizzer.app.web.modules.controllers.platform.xm;

import cn.wizzer.app.xm.modules.services.V_XmInfService;
import cn.wizzer.app.xm.modules.services.XmInfService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At("/platform/xm/final")
public class XmFinalController {
    private static final Log log = Logs.get();

    @Inject
    private XmInfService xmInfService;

    @Inject
    private V_XmInfService v_xmInfService;

    @At("")
    @Ok("beetl:/platform//xm/final/index.html")
    @RequiresPermissions("platform.xm.final")
    public void index() {
    }

}
