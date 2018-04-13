package cn.wizzer.app.web.modules.controllers.open.email;

import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.base.Globals;
import cn.wizzer.app.web.commons.services.email.EmailService;
import cn.wizzer.app.web.commons.services.email.EmailServiceImpl.EmailServiceImpl;
import cn.wizzer.app.web.commons.services.email.EmailTask;
import cn.wizzer.app.web.commons.services.email.EmailThreadPool;
import cn.wizzer.app.web.commons.services.email.task.TemplateEmailTask;
import cn.wizzer.app.web.commons.services.gy.GyService;
import cn.wizzer.app.web.commons.util.Toolkit;
import cn.wizzer.app.web.modules.controllers.open.api.websocket.ApiWebsocketController;
import cn.wizzer.app.web.modules.controllers.open.websocket.WebsocketController;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.WebAppResourceLoader;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 邮箱内部api
 *
 * @Author Marveliu
 * @Create 2018/1/8 0008.
 */

@IocBean
public class EmailController {

    private static  final Log log = Logs.get();

    @Inject
    private SysUserService sysUserService;
    @Inject
    private GyService gyService;
    @Inject
    private EmailService emailService;
    @Inject
    private EmailThreadPool emailThreadPool;
    @Inject
    private WebsocketController websocketController;
    @Inject
    private Dao dao;


    /**
     * 多线程给userid发送激活邮件
     * @param userId
     * @return
     */
    public Object activeMail(String userId) {
        NutMap re = new NutMap();

        // token生成
        Sys_user user = sysUserService.fetch(userId);
        String token = String.format("%s,%s", user.getEmail(), System.currentTimeMillis());
        token = Toolkit._3DES_encode(user.getSalt().getBytes(), token.getBytes());
        // String url = req.getRequestURL() + "?token=" + token+ "&userId=" + userId;
        String url = Globals.AppDomain + "?token=" + token +"&userId=" + userId;

        String html = "<div>如果无法点击,请拷贝一下链接到浏览器中打开<p/>验证链接 %s</div>";
        html = String.format(html, url, url);

        try {
            WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/template/email/register.html");
            t.binding("username",user.getLoginname());
            t.binding("url",url);
            t.binding("email",user.getEmail());
            // 直接传递函数
            EmailTask task = new TemplateEmailTask(user.getEmail(),"测试",emailService,t);
            // 如果失败websocket发送给用户
            emailThreadPool.send(task);
            // todo：应该是上层进行确定，确定用户的邮箱存在
            // boolean ok = emailService.send(user.getEmail(),"测试",html);
            // if (!ok) {
            //     return re.setv("ok", false).setv("msg", "发送失败");
            // }
            log.debug("toke:"+token);
        } catch (Throwable e) {
            log.debug("发送邮件失败", e);
            re.setv("ok", false).setv("msg", "发送失败");
        }
        return re.setv("ok", true);
    }


    /**
     * 验证邮箱测试，正式使用过程之中在openapi之中
     * @param token
     * @param userId
     * @return
     */
    public String activeMailCallback(String token,String userId) {
        if (Strings.isBlank(token)) {
            return "请不要直接访问这个链接!!!";
        }
        if (token.length() < 10) {
            return "非法token";
        }
        try {
            token = Toolkit._3DES_decode(sysUserService.fetch(userId).getSalt().getBytes(), Toolkit.hexstr2bytearray(token));
            if (token == null)
                return "非法token";
            String[] tmp = token.split(",", 2);
            if (tmp.length != 2 || tmp[0].length() == 0 || tmp[1].length() == 0)
                return "非法token";
            long time = Long.parseLong(tmp[1]);
            if (System.currentTimeMillis() - time > 10 * 60 * 1000) {
                return "该验证链接已经超时";
            }
            Cnd cnd = Cnd.where("id", "=", userId);
            // 注意和shiro不一致的问题
            int re = dao.update(Sys_user.class, org.nutz.dao.Chain.make("emailChecked", true), cnd);
            websocketController.sendUserMsgByWsid(userId,new NutMap().setv("msg","hello"));
            if (re == 1) {
                return "验证成功";
            }
            return "验证失败!!请重新验证!!";
        } catch (Throwable e) {
            log.debug("检查token时出错", e);
            return "非法token";
        }
    }


}
