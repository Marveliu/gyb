package cn.wizzer.app.web.commons.websocket;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.plugins.mvc.websocket.AbstractWsEndpoint;
import org.nutz.plugins.mvc.websocket.NutWsConfigurator;
import org.nutz.plugins.mvc.websocket.WsHandler;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;


// ServerEndpoint是websocket的必备注解, value是映射路径, configurator是配置类.
@ServerEndpoint(value = "/websocket", configurator=NutWsConfigurator.class)
@IocBean // 使用NutWsConfigurator的必备条件
public class GybWebsocket extends AbstractWsEndpoint {

    public static Map<String,Session> onlineUsers = new HashMap<String,Session>();  //在线雇员列表
    private String username;	                                                    //当前会话的用户名
    private int id;	                                                                //当前用户id

    private static final Log log = Logs.get();

    @Inject
    Dao dao;
    public WsHandler createHandler(Session session, EndpointConfig config) {
        GybWsHandler handler = new GybWsHandler(roomPrefix, dao);
        handler.setRoomProvider(roomProvider);
        handler.setSession(session);
        return handler;
    }


}


//     AbstractWsChatEndpoint 是单例的，是websocket的入口，维护全局的信息，接管open，error，close
//     handler 类是多例的每个ws连接对应一个，接管onMessage 专注于信息的路由发送
//     provider类主要是用于缓存全局的长连接信息，以供handler类能够方便检索、路由的


