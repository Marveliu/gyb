package com.marveliu.app.sys.commons.base;
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

import com.marveliu.framework.services.sys.SysTaskService;
import com.marveliu.framework.util.ConfigUtil;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import static org.nutz.integration.rabbitmq.aop.RabbitmqMethodInterceptor.channel;


/**
 * @author Marveliu
 * @since 17/05/2018
 **/


@IocBean(create = "init")
public class Globals {
    private static final Log log = Logs.get();

    public void init() {
        initMqChannel();
    }

    // 初始化Channel队列
    @Aop("rabbitmq")
    public  void initMqChannel(){
        try {
            channel().queueDeclare(ConfigUtil.SYS_MSG_TAG_GY, true, false, false, null);
            channel().queueDeclare(ConfigUtil.SYS_MSG_TAG_SYS, true, false, false, null);
            channel().queueDeclare(ConfigUtil.SYS_MSG_TAG_XM, true, false, false, null);
        }catch (Exception e){
            log.error(e);
        }
    }
}