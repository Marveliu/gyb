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
import com.marveliu.app.web.commons.utils.ShiroUtil;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.xm.xm_inf;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.sys.SysUserinfService;
import com.marveliu.framework.services.xm.XmInfService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import java.util.List;

/**
 * @author Marveliu
 * @since 07/05/2018
 **/

@IocBean
@At("/platform/xm/inf")
public class XmInfController{
    private static final Log log = Logs.get();

    @Inject
    @Reference
    private XmInfService xmInfService;

    @Inject
    @Reference
    private SysUserinfService sysUserinfService;


    @Inject
    private ShiroUtil shiroUtil;

    @At("")
    @Ok("beetl:/platform/xm/inf/index.html")
    @RequiresPermissions("platform.xm.inf")
    public void index() {
    }


    @At("/data")
    @Ok("json")
    @RequiresPermissions("platform.xm.inf")
    public Object data(
            @Param("length") int length,
            @Param("start") int start,
            @Param("status") int status,
            @Param("draw") int draw,
            @Param("::order") List<DataTableOrder> order,
            @Param("::columns") List<DataTableColumn> columns) {

        Cnd cnd = Cnd.NEW();
        //todo: judge gy gz (cnd)

        return xmInfService.data(length, start, draw, order, columns, cnd, null);
    }


    @At("/detail/?")
    @Ok("beetl:/platform/xm/inf/detail.html")
    @RequiresPermissions("platform.code.inf")
    public void detail(String id, HttpServletRequest req) {
        if (!Strings.isBlank(id)) {
            req.setAttribute("obj", xmInfService.fetch(id));
        }else{
            req.setAttribute("obj", null);
        }
    }


    /**
     * 查询项目列表
     * @param xminfname
     * @param req
     * @return
     */
    @At("/xminflist")
    @Ok("json")
    @RequiresPermissions("platform.xm.inf")
    public Object xminflist(
            @Param("xmname") String xminfname,
            @Param("status") int status,
            HttpServletRequest req
    ){
        Cnd cnd = Cnd.NEW();
        if(!shiroUtil.isSuper()){
            cnd.and("author","=",sysUserinfService.getSysuserinfid(StringUtil.getPlatformUid()));
        }
        if(null !=xminfname && !xminfname.isEmpty()){
            cnd.and("taskname","like","%"+xminfname+"%");
        }else {
            if(status != 6){
                cnd.and("status","=",status);
            }
        }
        //查询视图
        List<xm_inf> xmInfList = xmInfService.query(cnd.desc("at"));
        return xmInfList;
    }

}
