package cn.wizzer.app.web.modules.controllers.open.api.email;

import cn.wizzer.app.sys.modules.models.Sys_user;
import cn.wizzer.app.sys.modules.services.SysUserService;
import cn.wizzer.app.web.commons.services.email.EmailService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.dao.Cnd;
import org.nutz.lang.util.NutMap;
import cn.wizzer.app.web.commons.util.Toolkit;

import javax.servlet.http.HttpServletRequest;
/**
 * 邮件激活，验证
 *
 * @Author Marveliu
 * @Create 2018/1/3 0003.
 */

@IocBean
@At("/open/api/email")
public class EmailController {

    private static final Log log = Logs.get();

    @Inject
    private EmailService emailService;
    @Inject
    private SysUserService sysUserService;
    @Inject
    private Dao dao;


    /**
     * 发送激活邮件
     * @param userId
     * @param req
     * @return
     */
    @At("/active/mail")
    @POST
    public Object activeMail(int userId, HttpServletRequest req) {
        NutMap re = new NutMap();
        Sys_user user = sysUserService.fetch(userId);
        String token = String.format("%s,%s,%s", user.getEmail(), System.currentTimeMillis());
        token = Toolkit._3DES_encode(user.getSalt().getBytes(), token.getBytes());
        String url = req.getRequestURL() + "?token=" + token;
        String html = "<div>如果无法点击,请拷贝一下链接到浏览器中打开<p/>验证链接 %s</div>";
        html = String.format(html, url, url);
        try {
            boolean ok = emailService.send(user.getEmail(), "XXX 验证邮件 by 雇佣帮", html);
            if (!ok) {
                return re.setv("ok", false).setv("msg", "发送失败");
            }
        } catch (Throwable e) {
            log.debug("发送邮件失败", e);
            return re.setv("ok", false).setv("msg", "发送失败");
        }
        return re.setv("ok", true);
    }

    /**
     * 验证激活邮件
     * @param token
     * @param
     * @return
     */
    @Filters
    @At("/active/mail")
    @GET
    @Ok("raw") // 为了简单起见,这里直接显示验证结果就好了
    public String activeMailCallback(@Param("token") String token,
                                     @Param("userId") String id) {
        if (Strings.isBlank(token)) {
            return "请不要直接访问这个链接!!!";
        }
        if (token.length() < 10) {
            return "非法token";
        }
        try {
            token = Toolkit._3DES_decode(sysUserService.fetch(id).getSalt().getBytes(), Toolkit.hexstr2bytearray(token));
            if (token == null)
                return "非法token";
            String[] tmp = token.split(",", 3);
            if (tmp.length != 3 || tmp[0].length() == 0 || tmp[1].length() == 0 || tmp[2].length() == 0)
                return "非法token";
            long time = Long.parseLong(tmp[2]);
            if (System.currentTimeMillis() - time > 10 * 60 * 1000) {
                return "该验证链接已经超时";
            }
            int userId = Integer.parseInt(tmp[0]);
            Cnd cnd = Cnd.where("userId", "=", userId).and("email", "=", tmp[1]);
            int re = dao.update(Sys_user.class, org.nutz.dao.Chain.make("emailChecked", true), cnd);
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
