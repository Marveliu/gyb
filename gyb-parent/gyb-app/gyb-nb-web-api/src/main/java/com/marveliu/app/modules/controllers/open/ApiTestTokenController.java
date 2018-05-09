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

import com.marveliu.app.commons.filter.ApiTokenFilter;
import com.marveliu.framework.model.base.Result;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;

/**
 * @author Marveliu
 * @since 09/05/2018
 **/


@IocBean
@At("/open/api/test")
@Filters({@By(type = ApiTokenFilter.class)})
public class ApiTestTokenController {
    private final static Log log = Logs.get();

    @At("/test1")
    @Ok("json")
    @POST
    public Object test1(@Param("openid") String openid) {
        return Result.success("执行成功", "openid:::" + openid);
    }
}
