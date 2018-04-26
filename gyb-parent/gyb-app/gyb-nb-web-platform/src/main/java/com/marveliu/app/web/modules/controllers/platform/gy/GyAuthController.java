package com.marveliu.app.web.modules.controllers.platform.gy;
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

import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.gy.GyAuthSubService;
import com.marveliu.framework.services.gy.GyFacadeService;
import com.marveliu.framework.services.gy.GyInfSubService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Marveliu
 * @since 26/04/2018
 **/

@IocBean
@At("/platform/gy/auth")
public class GyAuthController{
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private GyAuthSubService gyAuthSubService;
    @Inject
    @Reference
    private GyInfSubService gyInfSubService;
    @Inject
    @Reference
    private GyFacadeService gyFacadeService;
    @Inject
    @Reference
    private Dao dao;

    @At("")
    @Ok("beetl:/platform/gy/auth/index.html")
    @RequiresPermissions("platform.gy.auth")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gy.auth")
    public Object data(
            @Param("gyid") String gyid,
            @Param("realname") String realname,
            @Param("status") String status,
            @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();

        if(!Strings.isBlank(gyid)){
            cnd.and("gyid", "=", gyid);
        }
        if(!Strings.isBlank(realname)){
            cnd.and("realname", "like", "%" + realname + "%");
        }
        if(!Strings.isBlank(status) && !"3".equals(status) && Strings.isBlank(realname) && Strings.isBlank(gyid)){
            cnd.and("status", "=", status);
        }
        return gyAuthSubService.data(length, start, draw, order, columns, cnd, null);
    }


    @At("/add")
    @Ok("beetl:/platform/gy/auth/add.html")
    @RequiresPermissions("platform.gy.auth")
    public void add() {

    }


    @At("/detail/?")
    @Ok("beetl:/platform/gy/auth/detail.html")
    @RequiresPermissions("platform.gy.auth")
    public void detail(String gyid, HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        cnd.and("gyid","=",gyid);
        if (!Strings.isBlank(gyid)) {
            req.setAttribute("obj", gyAuthSubService.fetch(cnd));
        }else{
            req.setAttribute("obj", null);
        }
    }


    @At("/setGyAuthStatus")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.edit")
    @SLog(type = "gy", tag = "雇员身份审批", msg = "${req.getAttribute('flag')}")
    public Object setGyAuthStatus(
            @Param("gyid") String gyid,
            @Param("flag") boolean flag,
            @Param("note") String note,
            HttpServletRequest req) {
        try {
            if (gyAuthSubService.setStatus(gyid, flag,note)) {
                return Result.success("雇员编号" + gyid + "身份认证状态:" +flag);
            }
            return Result.error("system.error");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }
}