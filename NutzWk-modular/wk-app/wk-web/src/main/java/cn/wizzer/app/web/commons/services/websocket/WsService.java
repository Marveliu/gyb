package cn.wizzer.app.web.commons.services.websocket;

import cn.wizzer.app.web.commons.websocket.GybWebsocket;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Each;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;

import javax.websocket.Session;

/**
 * websocket服务集成
 *
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */

@IocBean
public class WsService {

    // 在Service或Module中,通过ioc注入上述的MyWebsocket
    @Inject
    protected GybWebsocket gybWebsocket;


    // 按业务需要,调用myWebsocket提供的各种api
    public void send_job_notify(String room, final String from) {
        // 通过each方法变量房间内的会话
        gybWebsocket.each(room, new Each<Session>() {
            public void invoke(int index, Session ele, int length) {
                // 逐个会话发送消息
                gybWebsocket.sendJson(ele.getId(), new NutMap("action", "layer").setv("notify", "你有新的待办事宜,请查看收件箱 from=" + from));
            }
        });
    }

}
