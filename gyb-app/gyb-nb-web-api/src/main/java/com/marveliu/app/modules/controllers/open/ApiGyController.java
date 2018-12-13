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
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.services.gy.GyAuthService;
import com.marveliu.framework.services.gy.GyFacadeService;
import com.marveliu.framework.services.gy.GyInfService;
import com.marveliu.framework.services.gy.GyPayService;
import com.marveliu.framework.services.sys.SysUserService;
import com.marveliu.framework.util.DateUtil;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * API:雇员相关
 *
 * @author Marveliu
 * @since 16/05/2018
 **/


@IocBean
@At("/open/api/gy")
// @Filters({@By(type = ApiSignFilter.class)})
public class ApiGyController {

    @Inject
    @Reference
    private GyInfService gyInfService;
    @Inject
    @Reference
    private GyAuthService gyAuthService;
    @Inject
    @Reference
    private SysUserService sysuserService;
    @Inject
    @Reference
    private GyPayService gyPayService;

    @Inject
    @Reference
    private GyFacadeService gyFacadeService;


}
