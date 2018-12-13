package com.marveliu.app.commons.utils;
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

import org.nutz.lang.Lang;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Marveliu
 * @since 09/05/2018
 **/

public class SignUtil {
    public static String createSign(String appkey, Map<String, Object> params) {
        Map<String, Object> map = MapUtil.sortMapByKey(params);
        StringBuffer sb = new StringBuffer();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String k = it.next();
            String v = (String) map.get(k);
            if (null != v && !"".equals(v)
                    && !"sign".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("appkey=" + appkey);
        String sign = Lang.md5(sb.toString());
        return sign;
    }
}