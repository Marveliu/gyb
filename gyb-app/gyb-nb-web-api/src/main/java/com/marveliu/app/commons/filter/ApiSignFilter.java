package com.marveliu.app.commons.filter;
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

/**
 * @author Marveliu
 * @since 09/05/2018
 **/

import com.marveliu.app.commons.utils.SignUtil;
import com.marveliu.framework.model.base.Result;
import org.nutz.integration.jedis.RedisService;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.UTF8JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class ApiSignFilter implements ActionFilter {
    private static final Log log = Logs.get();

    public View match(ActionContext context) {
        try {
            PropertiesProxy conf = context.getIoc().get(PropertiesProxy.class, "conf");
            RedisService redisService = context.getIoc().get(RedisService.class);
            String appid_sys = conf.get("apitoken.appid", "");
            String appkey_sys = conf.get("apitoken.appkey", "");
            Map<String, Object> paramMap = getParameterMap(context.getRequest());
            String appid = Strings.sNull(paramMap.get("appid"));
            String sign = Strings.sNull(paramMap.get("sign"));
            String timestamp = Strings.sNull(paramMap.get("timestamp"));
            String nonce = Strings.sNull(paramMap.get("nonce"));
            if (!appid_sys.equals(appid)) {
                return new UTF8JsonView(JsonFormat.compact()).setData(Result.error(1, "appid不正确"));
            }
            if (Times.getTS() - Long.valueOf(timestamp) > 60 * 1000) {//时间戳相差大于1分钟则为无效的
                return new UTF8JsonView(JsonFormat.compact()).setData(Result.error(2, "timestamp不正确"));
            }
            String nonceCache = redisService.get("api_sign_nonce:" + appid + "_" + nonce);
            if (Strings.isNotBlank(nonceCache)) {//如果一分钟内nonce是重复的则为无效,让nonce只能使用一次
                return new UTF8JsonView(JsonFormat.compact()).setData(Result.error(3, "nonce不正确"));

            }
            if (!SignUtil.createSign(appkey_sys, paramMap).equalsIgnoreCase(sign)) {
                return new UTF8JsonView(JsonFormat.compact()).setData(Result.error(4, "sign签名不正确"));
            }
            //nonce保存到缓存
            redisService.set("api_sign_nonce:" + appid + "_" + nonce, nonce);
            redisService.expire("api_sign_nonce:" + appid + "_" + nonce, 60);
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new UTF8JsonView(JsonFormat.compact()).setData(Result.error(-1, "系统异常"));
        }
    }

    private Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String paramName = names.nextElement();
            map.put(paramName, request.getParameter(paramName));
        }
        return map;
    }
}