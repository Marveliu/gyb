package com.marveliu.app.modules.controllers.open;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.commons.slog.annotation.SLog;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.services.sys.SysUserService;
import com.marveliu.framework.util.Toolkit;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

/**
 * @author Marveliu
 * @since 15/05/2018
 **/

@IocBean
@At("/open/api/sys")
public class ApiSysController {

    private final static Log log = Logs.get();

    @Inject
    @Reference
    private SysUserService sysUserService;

    /**
     * 验证邮箱
     * @param token
     * @param userId
     * @return
     */
    @At("/email/checkActiveMail")
    @SLog(type = "sys",tag = "邮箱验证",param = true,result = true)
    @Ok("json")
    public Object checkActiveMail(
           @Param("token") String token,
           @Param("userId") String userId) {
        if (Strings.isBlank(token)) return Result.error("请不要直接访问这个链接!!!");
        if (token.length() < 10) return Result.error("非法token");
        try {
            token = Toolkit._3DES_decode(sysUserService.fetch(userId).getSalt().getBytes(), Toolkit.hexstr2bytearray(token));
            if (Lang.isEmpty(token)) return Result.error("非法token");
            String[] tmp = token.split(",", 2);
            if (tmp.length != 2 || tmp[0].length() == 0 || tmp[1].length() == 0) return Result.error("非法token");
            long time = Long.parseLong(tmp[1]);
            if (System.currentTimeMillis() - time > 10 * 60 * 1000) return Result.error("该验证链接已经超时");
            Cnd cnd = Cnd.where("id", "=", userId);
            if (1 ==  sysUserService.update(Chain.make("emailChecked",true),cnd)) {
                return Result.success("验证成功");
            }
            return Result.error("验证失败!!请重新验证!!");
        } catch (Throwable e) {
            log.debug("检查token时出错", e);
        }
        return Result.error("非法token");
    }

}
