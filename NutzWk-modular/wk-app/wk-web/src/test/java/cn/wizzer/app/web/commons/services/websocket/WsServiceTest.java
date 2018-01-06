package cn.wizzer.app.web.commons.services.websocket;

import cn.wizzer.app.TestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */
public class WsServiceTest extends TestBase{
    @Test
    public void send_job_notify() throws Exception {
        ioc.get(WsService.class).send_job_notify("home","gyb");
    }

}