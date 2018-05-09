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

import com.marveliu.app.commons.utils.TokenUtil;
import com.marveliu.framework.model.base.Result;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.UTF8JsonView;

/**
 * JWT Token拦截器
 *
 * Created by wizzer on 2016/8/11.
 */
public class ApiTokenFilter implements ActionFilter {
    private static final Log log = Logs.get();

    public View match(ActionContext context) {
        try {
            String appid = Strings.sNull(context.getRequest().getParameter("appid"));
            String token = Strings.sNull(context.getRequest().getParameter("token"));
            if (!context.getIoc().get(TokenUtil.class).verifyToken(appid, token)) {
                return new UTF8JsonView(JsonFormat.compact()).setData(Result.error(-2, "token失效,请重新获取"));
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new UTF8JsonView(JsonFormat.compact()).setData(Result.error(-1, "系统异常"));
        }
    }
}