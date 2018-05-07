package com.marveliu.app.msg.modules.services.impl;
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

import com.alibaba.dubbo.config.annotation.Service;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.IOException;

import static org.nutz.integration.rabbitmq.aop.RabbitmqMethodInterceptor.channel;

/**
 * @author Marveliu
 * @since 23/04/2018
 **/

@IocBean
public class RabbitTestService {

    private static Log log = Logs.getLog(RabbitTestService.class);

    @Aop("rabbitmq") // 会自动管理Connection/Channel的开启和关闭.
    public void publish(String routingKey, byte[] body) throws Exception {
        channel().basicPublish("", routingKey, null, body);
    }

    @Aop("rabbitmq")
    public void recive() throws Exception {
        // 消费者无法建立queue
        channel().basicConsume("hello", new DefaultConsumer(channel()) {
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       BasicProperties properties,
                                       byte[] body)
                    throws IOException {

                log.info(body);
            }
        });
    }
}