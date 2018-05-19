package com.marveliu.app.web.commons.utils;
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

/**
 * @author Marveliu
 * @since 15/05/2018
 **/

import com.marveliu.app.web.commons.base.Globals;
import com.marveliu.framework.model.library.lib_skill;
import com.marveliu.framework.services.library.LibSkillService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 自用状态码，封装在数据字典
 *
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */

@IocBean
public class StatusCodeUtil
{

    @Inject
    @Reference
    private LibSkillService libSkillService;

    public static String bind(String code){
        return  Globals.MyDict.get(code);
    }


    public static String test(){
        return  "fuckyou";
    }


    public String GyAuthStatusMean(int status){
        String code = "gyau" + status;
        return  Globals.MyDict.get(code);
    }

    public String SexStatusMean(int status){
        String code = "sex" + status;
        return  Globals.MyDict.get(code);
    }

    public String StulevelStatusMean(int status){
        String code = "stulevel" + status;
        return  Globals.MyDict.get(code);
    }


    public String TwoStatusMean(int status){
        String code = "twostatu"+ status;
        return  Globals.MyDict.get(code);
    }

    public String getSkillNameById(String id){
        lib_skill skill = libSkillService.fetch(id);
        return skill.getName();
    }
}
