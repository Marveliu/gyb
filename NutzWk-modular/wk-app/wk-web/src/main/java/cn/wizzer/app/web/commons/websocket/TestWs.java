package cn.wizzer.app.web.commons.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * testws
 *
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */

@ServerEndpoint(value = "/websocket1")
public class TestWs {
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        System.out.print(1);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    }
}
