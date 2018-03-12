package cn.wizzer.app.web.modules.controllers.open.api.websocket;

import cn.wizzer.app.web.commons.services.gy.GyService;
import cn.wizzer.app.web.commons.services.websocket.WsService;
import cn.wizzer.framework.base.Result;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * websocket的一些api
 *
 * @Author Marveliu
 * @Create 2018/1/7 0007.
 */

@IocBean
@At("/open/api/websocket")
public class ApiWebsocketController {


    @Inject
    private WsService wsService;
    @Inject
    private GyService gyService;
    private final static Log log = Logs.get();

    @At
    @Ok("json")
    @POST
    public Object broadcast(
            @Param("msg") String msg,
            @Param("room") String room,
            HttpServletRequest req) {
        try {
            log.debug("广播msg::"+msg );
            wsService.broadcast_msg(msg,room);
            return Result.success("ok");
        } catch (Exception e) {
            return Result.error("fail");
        }
    }

    @At
    @Ok("json")
    @POST
    public Object sendMsgByWsid(

            @Param("gyid") String gyid,
            @Param("msg") NutMap msg,
            HttpServletRequest req) {
        try {
            log.debug("私发msg::"+msg );
            gyService.sendMsgByGyid(gyid,msg);
            return Result.success("ok");
        } catch (Exception e) {
            return Result.error("fail");
        }
    }

}
