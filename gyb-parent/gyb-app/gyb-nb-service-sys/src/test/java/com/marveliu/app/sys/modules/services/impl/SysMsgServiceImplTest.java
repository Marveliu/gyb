package com.marveliu.app.sys.modules.services.impl;

import com.marveliu.app.sys.commons.core.DubboRpcSysMainLauncher;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.tmsg.RegTMsg;
import com.marveliu.framework.services.sys.SysMsgService;
import com.marveliu.framework.services.sys.SysUserService;
import com.marveliu.framework.util.ConfigUtil;
import com.marveliu.framework.util.Toolkit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import static org.junit.Assert.*;

@IocBean
@RunWith(NbJUnit4Runner.class)
public class SysMsgServiceImplTest {


    private final static String UID = "7e77f453dc364c4d8c8fa4413092335f";

    @Inject
    private SysMsgService sysMsgService;
    @Inject
    private SysUserService sysUserService;

    @Test
    public void pushMsg() {
        Sys_user sysUser = sysUserService.fetch(UID);
        Sys_msg sysMsg = new Sys_msg();
        String token = String.format("%s,%s", sysUser.getEmail(), System.currentTimeMillis());
        token = Toolkit._3DES_encode(sysUser.getSalt().getBytes(), token.getBytes());
        String url = ConfigUtil.AppDomain + "/open/api/sys/email/checkActiveMail?token=" + token +"&userId=" + sysUser.getId();
        System.out.println("url:"+url);
        TMsg tMsg = new RegTMsg(sysUser.getUsername(),url);
        sysMsg.setRevid(sysUser.getId());
        sysMsg.setRevaccount(sysUser.getEmail());
        sysMsg.setMsg(Json.toJson(tMsg));
        sysMsg.setType(ConfigUtil.SYS_MSG_TYPE_EMAIL);
        sysMsg.setTag(ConfigUtil.SYS_MSG_TAG_GY);
        sysMsg.setTmsgclass(tMsg.getTMsgClass());
        assertEquals(true,sysMsgService.pushMsg(sysMsg));
    }

    // 测试类可提供public的static的createNbApp方法,用于定制当前测试类所需要的NbApp对象.
    // 测试类带@IocBean或不带@IocBean,本规则一样生效
    // 若不提供,默认使用当前测试类作为MainLauncher.
    // 也可以自定义NbJUnit4Runner, 继承NbJUnit4Runner并覆盖其createNbApp方法
    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(DubboRpcSysMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("com.marveliu");
        return nb;
    }
}