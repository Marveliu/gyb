package com.marveliu.app.web.modules.controllers.platform.xm;
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
import com.marveliu.app.web.commons.utils.ShiroUtil;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.xm.xm_bill;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.sys.SysUserinfService;
import com.marveliu.framework.services.xm.XmBillService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Marveliu
 * @since 08/05/2018
 **/

@IocBean
@At("/platform/xm/bill")
public class XmBillController{
    private static final Log log = Logs.get();

    @Inject
    @Reference
    private XmBillService xmBillService;

    @Inject
    @Reference
    private SysUserinfService sysUserinfService;

    @Inject
    private ShiroUtil shiroUtil;

    @At("")
    @Ok("beetl:/platform/xm/bill/index.html")
    @RequiresPermissions("platform.xm.bill")
    public void index() {
    }

    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.bill")
    public Object data(
            @Param("xminfid") String xminfid,
            @Param("status") int status,
            @Param("length") int length,
            @Param("start") int start,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        if(Strings.isEmpty(xminfid)){
            if(status!= 5){
                cnd.and("status","=",status);
            }
        }else {
            cnd.and("xminfid","=",xminfid);
        }
        return xmBillService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/edit/?")
    @Ok("beetl:/platform/xm/bill/edit.html")
    @RequiresPermissions("platform.xm.bill.edit")
    public void edit(String id,HttpServletRequest req) {
        req.setAttribute("obj", xmBillService.fetch(id));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("platform.xm.bill.edit")
    @SLog(tag = "xm_bill", msg = "${args[0].id}")
    public Object editDo(@Param("..")xm_bill xmBill, HttpServletRequest req) {
        try {
            xmBill.setOpBy(StringUtil.getPlatformUid());
            xmBillService.updateIgnoreNull(xmBill);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/detail/?")
    @Ok("beetl:/platform/xm/bill/detail.html")
    @RequiresPermissions("platform.xm.bill")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            xm_bill bill = xmBillService.fetch(id);
            bill = xmBillService.fetchLinks(bill,"realgypay");
            req.setAttribute("obj", bill);
        }else{
            req.setAttribute("obj", null);
        }
    }

    @At("/check/?")
    @Ok("json")
    @RequiresPermissions("platform.xm.bill")
    @SLog(type = "xm",tag = "账单支付确认", msg = "${req.getAttribute('id')}")
    public Object check(String id, HttpServletRequest req) {
        try {
            String sysuerid = StringUtil.getPlatformUid();
            if(!Strings.isBlank(id)){
                if(xmBillService.commitXmbill(id,StringUtil.getPlatformUid()))
                {
                    req.setAttribute("id",id);
                    return Result.success("system.success");
                }
            }
        } catch (Exception e) {
            log.error("确认支付失败",e);
        }
        return Result.error("system.error");
    }



}