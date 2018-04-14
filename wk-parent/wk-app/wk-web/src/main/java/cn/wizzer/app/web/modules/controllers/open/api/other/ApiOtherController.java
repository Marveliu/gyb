package cn.wizzer.app.web.modules.controllers.open.api.other;
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

import cn.wizzer.app.web.commons.base.Globals;
import cn.wizzer.app.web.commons.services.qrcode.QRCodeFactory;
import cn.wizzer.framework.base.Result;
import cn.wizzer.framework.util.DateUtil;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.random.R;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CrossOriginFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marveliu
 * @since 14/04/2018
 **/


@IocBean
@At("/open/api/other")
public class ApiOtherController {

    /**
     * @api {post} /open/api/other/getQrcode          获得二维码
     * @apiGroup Other
     * @apiVersion 1.0.0
     * @apiPermission 无
     * @apiParamExample {json} 示例
     * POST open/api/other/getQrcode?content=刘尚楠真帅
     * @apiSuccess {String}  url    生成二维码的地址
     * @apiSuccessExample {json} 示例
     * HTTP/1.1 200 OK
    {
    "url": "ruihua/upload/image/qrimg/20180225/rpk4pm52gqhu5pa2gslrqq9gu4.jpg"
    }
     * @apiError (失败) {number} code 不等于0
     * @apiError (失败) {string} msg 错误文字描述
     * @apiErrorExample {json} 示例
     * HTTP/1.1 200 OK
     * {
     * "code": 1,
     * "msg": "fail"
     * }
     */
    @At
    @Ok("json")
    @Filters(@By(type = CrossOriginFilter.class))
    public Object getQrcode(
            @Param("content") String con,
            HttpServletRequest req) {

        String content = con;
        String logUri = null;
        String p = Globals.AppRoot;
        String f = Globals.AppUploadPath + "/image/qrimg/" + DateUtil.format(new Date(), "yyyyMMdd");
        File file = new File(p + f);

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdirs();
        }

        String fileurl = f + "/" + R.UU32() + ".jpg";

        String outFileUri = p + fileurl;
        int[] size = new int[]{430, 430};
        String format = "jpg";
        try {
            new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri, size);
            Map map = new HashMap();
            map.put("url",fileurl);
            return map;
        } catch (Exception e) {
            return Result.error("fail");
        }
    }
}
