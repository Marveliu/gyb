package cn.wizzer.app.web.modules.controllers.open.admin;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.*;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.impl.NutLoading;

/**
 * Created by 89792 on 2017/11/12 0012.
 */


@IocBean
@At("/open/admin")
public class GoldfingerController {
    @Inject
    private Dao dao;

    private static final Log log = Logs.get();

    @At("/getUrlMapping")
    @Ok("json")
    //public Map<String, Sys_route>
    public UrlMapping updateRoute() {
        NutConfig config = Mvcs.getNutConfig();
        //重新加载
        NutLoading load = (NutLoading) config.createLoading();
        load.load(config);
        // url映射
        UrlMapping urlmap = config.getUrlMapping();
        return  config.getUrlMapping();
    }

    @At("/hello")
    @Ok("json")
    //public Map<String, Sys_route>
    public String hello() {
        return  "hello world!";
    }
}