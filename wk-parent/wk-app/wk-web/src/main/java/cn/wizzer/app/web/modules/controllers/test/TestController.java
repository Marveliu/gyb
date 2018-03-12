package cn.wizzer.app.web.modules.controllers.test;


import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;

@IocBean
@At("/test")
public class TestController {

    /**
     * 查看指定界面的样式
     * @param viewpath
     * @param req
     * @return
     */
    @At("/view/?")
    @Ok("re:beetl:/platform/sys/home.html")
    public String edit(String viewpath,HttpServletRequest req) {
        return "beetl:"+viewpath;
    }
}
