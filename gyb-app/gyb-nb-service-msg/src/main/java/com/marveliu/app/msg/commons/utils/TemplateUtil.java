package com.marveliu.app.msg.commons.utils;
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

import com.marveliu.framework.services.msg.TMsg;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.nutz.boot.AppContext;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author Marveliu
 * @since 14/05/2018
 **/

@IocBean
public class TemplateUtil {

    private final static Log log = Logs.get();
    private HashMap<String, String> dataMap = new HashMap<>();


    public TemplateUtil() {
    }


    public Template buildTemplate(TMsg tMsg) {
        StringBuilder path = new StringBuilder();
        try {
            addDate(tMsg);
            Template t = AppContext.getDefault().getIoc().get(GroupTemplate.class).getTemplate(tMsg.getTemplatePath());
            t.binding(dataMap);
            return t;
        } catch (Exception e) {
            log.error("buildTemplate error", e);
        }
        return null;
    }

    private void addDate(String key, String value) {
        this.dataMap.put(key, value);
    }


    private Boolean addDate(TMsg msg) {
        Field[] fields = msg.getClass().getDeclaredFields();
        Field[] basefields = msg.getClass().getSuperclass().getDeclaredFields();
        try {
            addDate(fields, msg);
            addDate(basefields, msg);
            return true;
        } catch (Exception e) {
            log.error("添加模板信息失败");
        }
        return true;
    }

    private void addDate(Field[] fs, Object obj) {
        for (int j = 0; j < fs.length; j++) {
            fs[j].setAccessible(true);
            String name = fs[j].getType().getName();
            // String type
            if (name.equals(java.lang.String.class.getName())) {
                try {
                    String K = fs[j].getName();
                    String V = fs[j].get(obj).toString();
                    addDate(K, V);
                } catch (IllegalArgumentException e) {
                    log.error(e);
                } catch (IllegalAccessException e) {
                    log.error(e);
                }
            }
        }
    }

    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

    private void setDataMap(HashMap<String, String> dataMap) {
        this.dataMap = dataMap;
    }

}
