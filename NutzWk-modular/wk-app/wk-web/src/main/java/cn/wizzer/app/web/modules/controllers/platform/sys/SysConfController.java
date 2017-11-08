package cn.wizzer.app.web.modules.controllers.platform.sys;

import cn.wizzer.app.sys.modules.models.Sys_config;
import cn.wizzer.app.sys.modules.services.SysConfigService;
import cn.wizzer.app.web.commons.base.Globals;
import cn.wizzer.app.web.commons.slog.annotation.SLog;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.page.datatable.DataTableColumn;
import cn.wizzer.framework.page.datatable.DataTableOrder;
import cn.wizzer.framework.rabbit.RabbitMessage;
import cn.wizzer.framework.rabbit.RabbitProducer;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wizzer on 2016/6/28.
 */
@IocBean
@At("/platform/sys/conf")
public class SysConfController {
    private static final Log log = Logs.get();
    @Inject
    private SysConfigService configService;
    @Inject
    private RabbitProducer rabbitProducer;

    @At("")
    @Ok("beetl:/platform/sys/conf/index.html")
    @RequiresPermissions("sys.manager.conf")
    public void index() {

    }

    @At
    @Ok("beetl:/platform/sys/conf/add.html")
    @RequiresPermissions("sys.manager.conf")
    public void add() {

    }

    @At
    @Ok("json")
    @RequiresPermissions("sys.manager.conf.add")
    @SLog(tag = "添加参数", msg = "${args[0].configKey}:${args[0].configValue}")
    public Object addDo(@Param("..") Sys_config conf) {
        try {
            if (configService.insert(conf) != null) {
                Globals.initSysConfig(configService.dao());
                if (Globals.RabbitMQEnabled) {
                    String exchange = "fanoutExchange";
                    String routeKey = "sysconfig";
                    RabbitMessage msg = new RabbitMessage(exchange, routeKey, new NutMap());
                    rabbitProducer.sendMessage(msg);
                }
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit/?")
    @Ok("beetl:/platform/sys/conf/edit.html")
    @RequiresPermissions("sys.manager.conf")
    public Object edit(String id) {
        return configService.fetch(id);
    }

    @At
    @Ok("json")
    @RequiresPermissions("sys.manager.conf.edit")
    @SLog(tag = "修改参数", msg = "${args[0].configKey}:${args[0].configValue}")
    public Object editDo(@Param("..") Sys_config conf) {
        try {
            if (configService.updateIgnoreNull(conf) > 0) {
                Globals.initSysConfig(configService.dao());
                if (Globals.RabbitMQEnabled) {
                    String exchange = "fanoutExchange";
                    String routeKey = "sysconfig";
                    RabbitMessage msg = new RabbitMessage(exchange, routeKey, new NutMap());
                    rabbitProducer.sendMessage(msg);
                }
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("sys.manager.conf.delete")
    @SLog(tag = "删除参数", msg = "参数:${args[0]}")
    public Object delete(String configKey) {
        try {
            if (Strings.sBlank(configKey).startsWith("App")) {
                return Result.error("系统参数不可删除");
            }
            if (configService.delete(configKey) > 0) {
                Globals.initSysConfig(configService.dao());
                if (Globals.RabbitMQEnabled) {
                    String exchange = "fanoutExchange";
                    String routeKey = "sysconfig";
                    RabbitMessage msg = new RabbitMessage(exchange, routeKey, new NutMap());
                    rabbitProducer.sendMessage(msg);
                }
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At
    @Ok("json:full")
    @RequiresPermissions("sys.manager.conf")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        return configService.data(length, start, draw, order, columns, cnd, null);
    }
}
