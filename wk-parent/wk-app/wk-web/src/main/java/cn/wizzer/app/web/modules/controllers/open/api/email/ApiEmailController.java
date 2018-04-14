package cn.wizzer.app.web.modules.controllers.open.api.email;

import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.base.Globals;
import cn.wizzer.app.web.commons.services.email.EmailService;
import cn.wizzer.app.web.commons.services.email.EmailTask;
import cn.wizzer.app.web.commons.services.email.EmailThreadPool;
import cn.wizzer.app.web.commons.services.email.task.TemplateEmailTask;
import cn.wizzer.app.web.commons.services.gy.GyService;
import cn.wizzer.app.web.commons.services.websocket.WsService;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.util.StringUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.WebAppResourceLoader;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.dao.Cnd;
import org.nutz.lang.util.NutMap;
import cn.wizzer.app.web.commons.util.Toolkit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 邮件Api
 *
 * @Author Marveliu
 * @Create 2018/1/3 0003.
 *
 */

@IocBean
@At("/open/api/email")
public class ApiEmailController {

    private static final Log log = Logs.get();

    @Inject
    private EmailService emailService;
    @Inject
    private SysUserService sysUserService;
    @Inject
    private GyService gyService;
    @Inject
    private EmailThreadPool emailThreadPool;
    @Inject
    private Dao dao;


    /**
     * 发送激活邮件
     * @param userId,req
     * @return
     */
    @At("/activeMail")
    @Ok("json")
    @POST
    public Object activeMail(
           @Param("userId") String  userId,HttpServletRequest req) {
        NutMap re = new NutMap();
        Sys_user user = sysUserService.fetch(userId);
        String token = String.format("%s,%s", user.getEmail(), System.currentTimeMillis());
        token = Toolkit._3DES_encode(user.getSalt().getBytes(), token.getBytes());
        //String url = req.getRequestURL() + "?token=" + token+"&userid=" + userId;

         String url = Globals.AppDomain + "/open/api/email/activeMailCheck?token=" + token +"&userId=" + userId;
        // String html = "您好！" + StringUtil.getUsername()+",请访问链接激活邮箱！"+
        //         "<br>" +
        //         "<div>如果无法点击,请拷贝一下链接到浏览器中打开<p/>验证链接 %s</div>";
        //
        // html = String.format(html, url, url);

        try {
            // boolean ok = emailService.sendHtmlTemplate(user.getEmail(),"雇佣帮账号邮箱激活","register");
            // boolean ok = emailService.send(
            //         user.getEmail(), "雇佣帮账号邮箱激活", html.toString());
            // if (ok) {
            //     return Result.success("邮件发送成功，请注意查收！");
            // }


            WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/template/email/register.html");
            t.binding("username",user.getLoginname());
            t.binding("url",url);
            t.binding("email",user.getEmail());
            // 直接传递函数
            EmailTask task = new TemplateEmailTask(user.getEmail(),"雇佣帮邮箱激活邮件",emailService,t);
            //task.run();
            // 如果失败websocket发送给用户
            emailThreadPool.send(task);
            return Result.success("邮件已经发送！");
        } catch (Throwable e) {
            log.debug("发送邮件失败", e);
        }
        return Result.error("邮件发送失败，请稍后再试！");
    }

    /**
     * 验证激活邮件
     * @param token
     * @param
     * @return
     */
    @Filters
    @At("/activeMailCheck")
    @GET
    @Ok("re:beetl:/platform/sys/home.html")
    public String activeMailCheck(@Param("token") String token,
                                     @Param("userId") String userId,
                                     HttpServletRequest request) {

        String Msg = null;
        if (Strings.isBlank(token)) {
            Msg= "请不要直接访问这个链接!!!";
        }
        if (token.length() < 10) {
            Msg ="非法token";
        }
        try {
            token = Toolkit._3DES_decode(sysUserService.fetch(userId).getSalt().getBytes(), Toolkit.hexstr2bytearray(token));
            if (token == null){
                Msg ="非法token";
            }
            String[] tmp = token.split(",", 2);
            if (tmp.length != 2 || tmp[0].length() == 0 || tmp[1].length() == 0){
                Msg ="非法token";
            }
            long time = Long.parseLong(tmp[1]);
            if (System.currentTimeMillis() - time > 10 * 60 * 1000) {
                Msg ="链接超时";
            }
            Cnd cnd = Cnd.where("id", "=", userId);
            int re = dao.update(Sys_user.class, org.nutz.dao.Chain.make("emailChecked", true), cnd);
            if (re == 1) {
                NutMap msg = new NutMap("action", "refresh").setv("notify", "邮件已经激活");
                gyService.sendMsgByUid(userId,msg);
                Msg ="邮箱验证成功";
            }
        } catch (Throwable e) {
            log.debug("检查token时出错", e);
        }
        request.setAttribute("Msg",Msg);
        return "beetl:/public/redirect.html";
    }

    /**
     * 发送密码重置邮件
     * @param userId,req
     * @return
     */
    @At("/lostPassword")
    @Ok("json")
    @POST
    public Object lostPassword(
            @Param("userId") String  userId,
            HttpServletRequest req) {
        NutMap re = new NutMap();
        Sys_user user = sysUserService.fetch(userId);
        String token = String.format("%s,%s", user.getEmail(), System.currentTimeMillis());
        token = Toolkit._3DES_encode(user.getSalt().getBytes(), token.getBytes());
        String url = Globals.AppDomain + "/open/api/email/lostPasswordCheck?token=" + token+"&userId=" + userId;

        try {
            WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("/template/email/password.html");
            t.binding("username",user.getLoginname());
            t.binding("url",url);
            t.binding("email",user.getEmail());
            // 直接传递函数
            EmailTask task = new TemplateEmailTask(user.getEmail(),"雇佣帮密码找回邮件",emailService,t);
            //task.run();
            // 如果失败websocket发送给用户
            emailThreadPool.send(task);
            return Result.success("邮件已经发送！");
        } catch (Throwable e) {
            log.debug("发送邮件失败", e);
        }
        return Result.error("邮件发送失败，请稍后再试！");
    }


    /**
     * 重置密码
     * @param token
     * @param
     * @return
     */
    @Filters
    @At("/lostPasswordCheck")
    @GET
    @Ok("re:beetl:/platform/sys/home.html")
    public String lostPassword(
            @Param("token") String token,
            @Param("userId") String userId,
            HttpServletRequest request) {

        String Msg = null;
        if (Strings.isBlank(token)) {
            Msg= "请不要直接访问这个链接!!!";
        }
        if (token.length() < 10) {
            Msg ="非法token";
        }
        try {
            token = Toolkit._3DES_decode(sysUserService.fetch(userId).getSalt().getBytes(), Toolkit.hexstr2bytearray(token));
            if (token == null){
                Msg ="非法token";
            }
            String[] tmp = token.split(",", 2);
            if (tmp.length != 2 || tmp[0].length() == 0 || tmp[1].length() == 0){
                Msg ="非法token";
            }
            long time = Long.parseLong(tmp[1]);
            if (System.currentTimeMillis() - time > 10 * 60 * 1000) {
                Msg ="链接超时";
            }
            Cnd cnd = Cnd.where("id", "=", userId);
            // 密码修改
            String pwd = sysUserService.resetPassword(userId);
            Msg="密码重置为:"+pwd+",请及时修改！";
        } catch (Throwable e) {
            log.debug("检查token时出错", e);
        }
        request.setAttribute("Msg",Msg);
        return "beetl:/public/redirect.html";
    }



}
