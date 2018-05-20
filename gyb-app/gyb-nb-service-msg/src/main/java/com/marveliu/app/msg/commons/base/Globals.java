package com.marveliu.app.msg.commons.base;
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

import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.model.sys.Sys_task;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.email.EmailService;
import com.marveliu.framework.services.sys.SysTaskService;
import com.marveliu.framework.services.task.TaskPlatformService;
import com.marveliu.framework.util.ConfigUtil;
import com.rabbitmq.client.*;
import org.nutz.boot.NbApp;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.IOException;
import java.util.List;

import static org.nutz.integration.rabbitmq.aop.RabbitmqMethodInterceptor.channel;

/**
 * @author Marveliu
 * @since 10/05/2018
 **/

@IocBean(create = "init")
public class Globals {
    private static final Log log = Logs.get();

    @Inject
    private EmailService emailService;

    @Inject
    ConnectionFactory rabbitmq_cf;


    public void init() {
        try {
            initGyChannel();
            initXmChannel();
            initSysChannel();
        } catch (Exception e) {

        }
    }


    public void initGyChannel() throws Exception {
        // 创建一个通道(一个轻量级的连接)
        Channel channel = rabbitmq_cf.newConnection().createChannel();
        //每次从队列获取的数量
        channel.basicQos(1);
        // 声明一个队列
        String QUEUE_NAME = "gy";
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        log.info("Consumer Wating Receive Message begin!");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                try {
                    Sys_msg sysMsg = Lang.fromBytes(body, Sys_msg.class);
                    Object obj = Json.fromJson(Class.forName(sysMsg.getTmsgclass()), sysMsg.getMsg());
                    if (sysMsg.getType() == ConfigUtil.SYS_MSG_TYPE_EMAIL) {
                        emailService.sendHtmlTemplateByTemplateName(sysMsg.getRevaccount(), (TMsg) obj);
                    }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    log.info("Msg Done!");
                } catch (Exception e) {
                    System.out.println(e);
                } finally {

                }
            }
        };
        boolean autoAck = false;
        // 订阅消息
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

    public void initXmChannel() throws Exception {
        // 创建一个通道(一个轻量级的连接)
        Channel channel = rabbitmq_cf.newConnection().createChannel();
        //每次从队列获取的数量
        channel.basicQos(1);
        // 声明一个队列
        String QUEUE_NAME = "xm";
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        log.info("Consumer Wating Receive Message begin!");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                try {
                    Sys_msg sysMsg = Lang.fromBytes(body, Sys_msg.class);
                    Object obj = Json.fromJson(Class.forName(sysMsg.getTmsgclass()), sysMsg.getMsg());
                    if (sysMsg.getType() == ConfigUtil.SYS_MSG_TYPE_EMAIL) {
                        emailService.sendHtmlTemplateByTemplateName(sysMsg.getRevaccount(), (TMsg) obj);
                    }
                    // todo: 没有消费也会删除
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    log.info("Msg Done!");
                } catch (Exception e) {
                    System.out.println(e);
                } finally {

                }
            }
        };
        boolean autoAck = false;
        // 订阅消息
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

    public void initSysChannel() throws Exception {
        // 创建一个通道(一个轻量级的连接)
        Channel channel = rabbitmq_cf.newConnection().createChannel();
        //每次从队列获取的数量
        channel.basicQos(1);
        // 声明一个队列
        String QUEUE_NAME = "sys";
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        log.info("Consumer Wating Receive Message begin!");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                try {
                    Sys_msg sysMsg = Lang.fromBytes(body, Sys_msg.class);
                    Object obj = Json.fromJson(Class.forName(sysMsg.getTmsgclass()), sysMsg.getMsg());
                    if (sysMsg.getType() == ConfigUtil.SYS_MSG_TYPE_EMAIL) {
                        emailService.sendHtmlTemplateByTemplateName(sysMsg.getRevaccount(), (TMsg) obj);
                    }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    log.info("Msg Done!");
                } catch (Exception e) {
                    System.out.println(e);
                } finally {

                }
            }
        };
        boolean autoAck = false;
        // 订阅消息
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

}

