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

import com.marveliu.app.commons.filter.ApiSignFilter;
import com.marveliu.app.commons.utils.TokenUtil;
import com.marveliu.framework.model.base.Result;
import org.nutz.integration.jedis.RedisService;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Marveliu
 * @since 09/05/2018
 **/

@IocBean
@At("/open/api/token")
@Filters({@By(type = ApiSignFilter.class)})
public class ApiTokenController {
    private final static Log log = Logs.get();
    @Inject
    private PropertiesProxy conf;
    @Inject
    private TokenUtil tokenUtil;
    @Inject
    private RedisService redisService;

    @POST
    @At("/get")
    @Ok("json")
    public Object get(@Param("appid") String appid) {
        try {
            NutMap resmap = new NutMap();
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.HOUR, +2);//设置token有效期为2小时
            date = c.getTime();
            resmap.addv("expires", 7200);//同时把有效期秒数传递给客户端
            resmap.addv("token", tokenUtil.generateToken(date, appid));
            return Result.success("获取token成功", resmap);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return Result.error(-1, "获取token失败");
        }
    }
}
