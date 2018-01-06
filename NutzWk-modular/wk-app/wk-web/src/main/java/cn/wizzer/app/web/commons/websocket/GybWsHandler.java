package cn.wizzer.app.web.commons.websocket;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.plugins.mvc.websocket.handler.SimpleWsHandler;

/**
 * handler
 *
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */

@IocBean
public class GybWsHandler extends SimpleWsHandler {
    private Dao dao;

    public GybWsHandler(String prefix, Dao dao) {
        super(prefix);
        this.dao = dao;
    }

    public void init() {
        super.init(); // 必须调用超类的init,除非直接实现WsHandler接口
        if (httpSession != null)
            httpSession.setAttribute("wsid", session.getId()); // 其他业务代码只需要从HttpSession取出wsid,即可调用AbstractWsEndpoint的api发送消息
    }

    /**
     * 消息传递处理
     * @param message
     */
    public void onMessage(String message) {
        try {
            NutMap msg = Json.fromJson(NutMap.class, message);
            String action = msg.getString("action");
            if (Strings.isBlank(action))
                return;
            String room = msg.getString("room");
            switch (action) {
                case "join":
                    join(room);
                    break;
                case "left":
                    left(room);
                    break;
                case "send":
                    // 处理发送过来的消息
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
