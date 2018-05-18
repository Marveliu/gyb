package com.marveliu.app.msg.modules.services.impl;

import com.marveliu.app.msg.commons.core.DubboRpcMsgMainLauncher;
import com.rabbitmq.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;
import static org.nutz.integration.rabbitmq.aop.RabbitmqMethodInterceptor.channel;

@IocBean
@RunWith(NbJUnit4Runner.class)
public class RabbitTestServiceTest {

    private Log log = Logs.getLog(RabbitTestServiceTest.class);

    @Inject
    private RabbitTestService rabbitTestService;


    @Test
    public void testBasicPublish() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);    // 5672
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 新建一个长连接
        Connection connection = factory.newConnection();
        // 创建一个通道(一个轻量级的连接)
        Channel channel = connection.createChannel();
        // 声明一个队列
        String QUEUE_NAME = "hello";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息到队列中
        String message = "Hello RabbitMQ!";
        // 注意：exchange如果不需要写成空字符串，routingKey和队列名称保持一致
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("Producer Send a message:" + message);
        // 关闭资源
        channel.close();
        connection.close();
    }

    @Test
    public void testBasicConsumer() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);    // 5672
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 新建一个长连接
        Connection connection = factory.newConnection();
        // 创建一个通道(一个轻量级的连接)
        Channel channel = connection.createChannel();
        // 声明一个队列
        String QUEUE_NAME = "hello";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Consumer Wating Receive Message");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [C] Received '" + message + "'");
            }
        };
        // 订阅消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }


    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(DubboRpcMsgMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("com.marveliu");
        return nb;
    }
}