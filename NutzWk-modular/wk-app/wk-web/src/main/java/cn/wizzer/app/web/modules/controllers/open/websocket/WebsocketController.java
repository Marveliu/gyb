package cn.wizzer.app.web.modules.controllers.open.websocket;

import cn.wizzer.app.web.commons.services.gy.GyService;
import cn.wizzer.app.web.commons.services.websocket.WsService;
import cn.wizzer.framework.base.Result;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
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
public class WebsocketController {


    @Inject
    private WsService wsService;
    @Inject
    private GyService gyService;
    private final static Log log = Logs.get();

    public Object broadcast(
            String msg,
            String room) {
        try {
            log.debug("广播msg::"+msg );
            wsService.broadcast_msg(msg,room);
            return Result.success("ok");
        } catch (Exception e) {
            return Result.error("fail");
        }
    }

    public Object sendUserMsgByWsid(
            String msg,
            String userid) {
        try {
            log.debug("私发msg::"+msg );
            gyService.sendMsgByUid(userid,msg);
            return Result.success("ok");
        } catch (Exception e) {
            return Result.error("fail");
        }
    }




}
