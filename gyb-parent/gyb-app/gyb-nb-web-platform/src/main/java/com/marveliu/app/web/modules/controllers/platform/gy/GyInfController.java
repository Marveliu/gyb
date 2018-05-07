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

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.sys.Sys_user;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.gy.GyFacadeService;
import com.marveliu.framework.services.gy.GyInfService;
import com.marveliu.framework.services.sys.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

@IocBean
@At("/platform/gy/inf")
public class GyInfController {
    private static final Log log = Logs.get();

    @Inject
    @Reference
    private GyInfService gyInfService;

    @Inject
    @Reference
    private GyFacadeService gyFacadeService;

    @Inject
    @Reference
    private SysUserService userService;

    @At("")
    @Ok("beetl:/platform/gy/inf/index.html")
    @RequiresPermissions("platform.gy.inf")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf")
    public Object data(
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();

        return gyInfService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/edit/?")
    @Ok("beetl:/platform/gy/inf/edit.html")
    @RequiresPermissions("platform.gy.inf")
    public void edit(String id, HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        Object test = gyInfService.fetch(cnd.and("gyid", "=", id));
        req.setAttribute("obj", test);
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.edit")
    @SLog(tag = "gz_inf", msg = "${args[0].id}")
    public Object editDo(
            @Param("..") gy_inf gyInf,
            @Param("birthdayat") String birthday,
            @Param("regYearat") String regyear,
            HttpServletRequest req) {
        try {
            String userid = gyInf.getUserid();
            Sys_user user = userService.fetch(userid);
            //修改邮箱
            user.setEmail(gyInf.getEmail());
            user.setOpBy(StringUtil.getPlatformUid());
            user.setOpAt((Long) (System.currentTimeMillis() / 1000));
            userService.updateIgnoreNull(user);
            //修改雇员信息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int birthdayat = (int) (sdf.parse(birthday).getTime() / 1000);
            int regyearat = (int) (sdf.parse(regyear).getTime() / 1000);
            gyInf.setRegYear(regyearat);
            gyInf.setRegYear(regyearat);
            gyInf.setOpBy(StringUtil.getPlatformUid());
            gyInf.setOpAt((Long) (System.currentTimeMillis() / 1000));
            gyInfService.updateIgnoreNull(gyInf);
            return Result.success("system.success");

        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/detail/?")
    @Ok("beetl:/platform/gy/inf/detail.html")
    @RequiresPermissions("platform.gy.inf")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            Cnd cnd = Cnd.NEW();
            gy_inf gy_inf = gyInfService.fetch(cnd.and("gyid","=",id));
            req.setAttribute("obj", gy_inf);
        }else{
            req.setAttribute("obj", null);
        }
    }

    @At("/setGy4/?")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.edit")
    @SLog(type = "gy", tag = "通过正式雇员", msg = "${req.getAttribute('id')}")
    public Object setGy4(String id, HttpServletRequest req) {
        try {
            String gyid = gyInfService.getGyByUserId(id).getGyid();
            if (gyFacadeService.updateGyRoleByGyid(gyid, "gy4")) {
                req.setAttribute("id", gyid);
                // 设置雇员为允许接单的状态
                gyInfService.update(Chain.make("status", 1), Cnd.where("userid", "=", id));
                return Result.success("system.success");
            } else {
                return Result.error("system.error");
            }
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/setGyStatus")
    @Ok("json")
    @RequiresPermissions("platform.gy.inf.edit")
    @SLog(type = "gy", tag = "修改雇员状态", msg = "雇员编号：${args[0]},状态：${args[1]}")
    public Object setGyStatus(
            @Param("gyid") String gyid,
            @Param("flag") boolean flag,
            HttpServletRequest req) {
        try {
            if (gyInfService.setGyStatus(gyid, flag)) {
                return Result.success("雇员编号" + gyid + "启用状态:" +flag);
            }
            return Result.error("system.error");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }
}