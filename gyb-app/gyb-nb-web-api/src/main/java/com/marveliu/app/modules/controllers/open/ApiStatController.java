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
import com.marveliu.app.commons.filter.ApiSignFilter;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.services.xm.XmApplyService;
import com.marveliu.framework.services.xm.XmBillService;
import com.marveliu.framework.services.xm.XmFeedbackService;
import com.marveliu.framework.services.xm.XmInfService;
import com.marveliu.framework.util.ConfigUtil;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CrossOriginFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现统计信息
 *
 * @author Marveliu
 * @since 15/05/2018
 **/

@IocBean
@At("/open/api/stat")
// @Filters({@By(type = ApiSignFilter.class)})
public class ApiStatController {


    @Inject
    @Reference
    private XmInfService xmInfService;

    @Inject
    @Reference
    private XmApplyService xmApplyService;

    @Inject
    @Reference
    private XmBillService xmBillService;

    @Inject
    @Reference
    private XmFeedbackService xmFeedbackService;


    // @At("/xminf")
    // @Ok("json")
    // @Filters(@By(type=CrossOriginFilter.class))
    // public Object getXmStat(@Param("gyid") String gyid) {
    //     Cnd cnd = Cnd.NEW();
    //     if(!Strings.isEmpty(gyid)){
    //         cnd.where("gyid","=",gyid);
    //     }
    //     Map<String,Integer> data = new HashMap<>();
    //     // data.put("申请中",xmApplyService.count(cnd));
    //     data.put("进行中",xmInfService.count(Cnd.where("gyid","=",gyid).and("status","=",ConfigUtil.XM_INF_DOING)));
    //     data.put("待结算",xmInfService.count(Cnd.where("gyid","=",gyid).and("status","=",ConfigUtil.XM_INF_CHECKING)));
    //     data.put("已完成",xmInfService.count(Cnd.where("gyid","=",gyid).and("status",">",ConfigUtil.XM_INF_CHECKING)));
    //     return Result.success().addData(data);
    // }
}
