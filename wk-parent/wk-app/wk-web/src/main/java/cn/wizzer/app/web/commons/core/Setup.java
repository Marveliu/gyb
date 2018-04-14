package cn.wizzer.app.web.commons.core;

import cn.wizzer.app.sys.modules.models.*;
import cn.wizzer.app.web.commons.base.Globals;
import cn.wizzer.app.web.commons.plugin.IPlugin;
import cn.wizzer.app.web.commons.plugin.PluginMaster;
import cn.wizzer.framework.ig.RedisIdGenerator;
import com.rabbitmq.client.*;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.*;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.el.opt.custom.CustomMake;
import org.nutz.integration.jedis.JedisAgent;
import org.nutz.integration.quartz.QuartzJob;
import org.nutz.integration.quartz.QuartzManager;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.Json;
import org.nutz.lang.*;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.quartz.Scheduler;
import redis.clients.jedis.Jedis;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by wizzer on 2016/6/21.
 */
public class Setup implements org.nutz.mvc.Setup {
    private static final Log log = Logs.get();
    private static Connection rabbitmq_conn;
    private static Channel rabbitmq_channel;

    public void init(NutConfig config) {
        try {
            // 环境检查
            if (!Charset.defaultCharset().name().equalsIgnoreCase(Encoding.UTF8)) {
                log.warn("This project must run in UTF-8, pls add -Dfile.encoding=UTF-8 to JAVA_OPTS");
            }
            Ioc ioc = config.getIoc();
            Dao dao = ioc.get(Dao.class);
            // 初始化redis实现的id生成器
            CustomMake.me().register("ig", ioc.get(RedisIdGenerator.class));
            // 初始化数据表
            initSysData(config, dao);
            // 初始化系统变量
            initSysSetting(config, dao);
            // 初始化定时任务
            initSysTask(config, dao);
            // 初始化自定义路由
            initSysRoute(config, dao);
            // 初始化热插拔插件
            initSysPlugin(config, dao);
            // 初始化rabbitmq
            //initRabbit(config, dao);
            // 初始化ig缓存
            //initRedisIg(ioc.get(JedisAgent.class), ioc.get(PropertiesProxy.class, "conf"), dao);
            // 检查一下Ehcache CacheManager 是否正常
            CacheManager cacheManager = ioc.get(CacheManager.class);
            log.debug("Ehcache CacheManager = " + cacheManager);
            log.info("\n _  _ _   _ _____ ______      ___  __\n" +
                    "| \\| | | | |_   _|_  /\\ \\    / / |/ /\n" +
                    "| .` | |_| | | |  / /  \\ \\/\\/ /| ' < \n" +
                    "|_|\\_|\\___/  |_| /___|  \\_/\\_/ |_|\\_\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当项目启动的时候把表主键加载到redis缓存中
     */
    private void initRedisIg(JedisAgent jedisAgent, PropertiesProxy conf, Dao dao) {
        long a = System.currentTimeMillis();
        try (Jedis jedis = jedisAgent.getResource()) {
            Sql sql;
            if ("mysql".equalsIgnoreCase(dao.getJdbcExpert().getDatabaseType())) {
                sql = Sqls.create("SELECT table_name FROM information_schema.columns WHERE table_schema='" + conf.get("db.name", "") + "' AND column_name='id'");
            } else {
                //oracle mssql该怎么写呢,等你来添加...
                log.info("wait for you ...");
                return;
            }
            sql.setCallback(Sqls.callback.strs());
            dao.execute(sql);
            List<String> tableNameList = sql.getList(String.class);
            for (String tableName : tableNameList) {
                List<Record> list = dao.query(tableName, Cnd.NEW().desc("id"), new Pager().setPageSize(1).setPageNumber(1));
                if (list.size() > 0) {
                    String id = list.get(0).getString("id");
                    if (Strings.isMatch(Pattern.compile("^.*[\\d]{16}$"), id)) {
                        String ym = id.substring(id.length() - 16, id.length() - 10);
                        if (Strings.isBlank(jedis.get("ig:" + tableName.toUpperCase() + ym))) {
                            jedis.set("ig:" + tableName.toUpperCase() + ym, String.valueOf(NumberUtils.toLong(id.substring(id.length() - 10, id.length()), 1)));
                        }
                    }
                }
            }
        }
        long b = System.currentTimeMillis();
        log.info("init redis ig time::" + (b - a) + "ms");
    }

    /**
     * 初始化队列,用于集群部署时的数据更新
     */
    private void initRabbit(NutConfig config, Dao dao) {
        try {
            String queue = R.UU32(), topicQueue = "topicQueue";
            ConnectionFactory factory = config.getIoc().get(ConnectionFactory.class, "rabbitmq_cf");
            log.debug("RabbitMQ:::" + factory.getHost());
            rabbitmq_conn = factory.newConnection();
            rabbitmq_channel = rabbitmq_conn.createChannel();
            rabbitmq_channel.queueDeclare(queue, true, true, false, null);
            rabbitmq_channel.queueDeclare(topicQueue, true, false, false, null);
            rabbitmq_channel.exchangeDeclare("topicExchange", BuiltinExchangeType.TOPIC, true);
            rabbitmq_channel.exchangeDeclare("fanoutExchange", BuiltinExchangeType.FANOUT, true);
            rabbitmq_channel.queueBind(queue, "fanoutExchange", "");
            rabbitmq_channel.queueBind(topicQueue, "topicExchange", "topic.#");
            rabbitmq_channel.basicConsume(queue, false, "myConsumerTagFanout",
                    new DefaultConsumer(rabbitmq_channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException {
                            String routingKey = envelope.getRoutingKey();
                            String exchange = envelope.getExchange();
                            NutMap params = Lang.fromBytes(body, NutMap.class);
                            log.debug("RabbitMQ fanoutExchange=" + exchange + ",routingKey=" + routingKey + ",params=" + Json.toJson(params));
                            long deliveryTag = envelope.getDeliveryTag();
                            switch (exchange) {
                                case "fanoutExchange"://广播模式,每个消费者都会消费
                                    switch (routingKey) {
                                        case "sysconfig":
                                            Globals.initSysConfig(dao);
                                            break;
                                        case "sysroute":
                                            Globals.initRoute(dao);
                                            break;
                                        case "wxtoken":
                                            Globals.WxMap.clear();
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                            }
                            // (process the message components here ...)
                            rabbitmq_channel.basicAck(deliveryTag, false);
                        }
                    });
            rabbitmq_channel.basicConsume(topicQueue, false, "myConsumerTagTopic",
                    new DefaultConsumer(rabbitmq_channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException {
                            String routingKey = envelope.getRoutingKey();
                            String exchange = envelope.getExchange();
                            NutMap params = Lang.fromBytes(body, NutMap.class);
                            log.debug("RabbitMQ topicExchange=" + exchange + ",routingKey=" + routingKey + ",params=" + Json.toJson(params));
                            long deliveryTag = envelope.getDeliveryTag();
                            switch (exchange) {
                                case "topicExchange"://主题模式,只需一个消费者消费
                                    switch (routingKey) {
                                        case "topic.test.me":
                                            log.debug("topic.test.me.......");
                                            break;
                                    }
                                    break;
                            }
                            // (process the message components here ...)
                            rabbitmq_channel.basicAck(deliveryTag, false);
                        }
                    });
            Globals.RabbitMQEnabled = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化热插拔插件
     *
     * @param config
     * @param dao
     */
    private void initSysPlugin(NutConfig config, Dao dao) {
        try {
            PluginMaster pluginMaster = config.getIoc().get(PluginMaster.class);
            List<Sys_plugin> list = dao.query(Sys_plugin.class, Cnd.where("disabled", "=", false));
            for (Sys_plugin sysPlugin : list) {
                String name = sysPlugin.getPath().substring(sysPlugin.getPath().indexOf(".")).toLowerCase();
                File file = new File(Globals.AppRoot + sysPlugin.getPath());
                String[] p = new String[]{};
                IPlugin plugin;
                if (".jar".equals(name)) {
                    plugin = pluginMaster.buildFromJar(file, sysPlugin.getClassName());
                } else {
                    byte[] buf = Files.readBytes(file);
                    plugin = pluginMaster.build(sysPlugin.getClassName(), buf);
                }
                if (!Strings.isBlank(sysPlugin.getArgs())) {
                    p = org.apache.commons.lang3.StringUtils.split(sysPlugin.getArgs(), ",");
                }
                pluginMaster.register(sysPlugin.getCode(), plugin, p);
            }
        } catch (Exception e) {
            log.debug("plugin load error", e);
        }
    }

    /**
     * 初始化自定义路由
     *
     * @param config
     * @param dao
     */
    private void initSysRoute(NutConfig config, Dao dao) {
        if (0 == dao.count(Sys_route.class)) {
            //路由示例
            Sys_route route = new Sys_route();
            route.setDisabled(false);
            route.setUrl("/sysadmin");
            route.setToUrl("/platform/login");
            route.setType("hide");
            dao.insert(route);
        }
        Globals.initRoute(dao);
    }

    /**
     * 初始化定时任务
     *
     * @param config
     * @param dao
     */
    private void initSysTask(NutConfig config, Dao dao) {
        if (0 == dao.count(Sys_task.class)) {
            //执行Quartz SQL脚本
            String dbType = dao.getJdbcExpert().getDatabaseType();
            log.debug("dbType:::" + dbType);
            FileSqlManager fmq = new FileSqlManager("quartz/" + dbType.toLowerCase() + ".sql");
            List<Sql> sqlListq = fmq.createCombo(fmq.keys());
            Sql[] sqlsq = sqlListq.toArray(new Sql[sqlListq.size()]);
            for (Sql sql : sqlsq) {
                dao.execute(sql);
            }
            //定时任务示例
            Sys_task task = new Sys_task();
            task.setDisabled(true);
            task.setName("测试任务");
            task.setJobClass("cn.wizzer.app.web.commons.quartz.job.TestJob");
            task.setCron("*/5 * * * * ?");
            task.setData("{\"hi\":\"Wechat:wizzer | send red packets of support,thank u\"}");
            task.setNote("微信号：wizzer | 欢迎发送红包以示支持，多谢。。");
            dao.insert(task);
        }
        QuartzManager quartzManager = config.getIoc().get(QuartzManager.class);
        quartzManager.clear();//启动时清除任务(不影响集群任务)
        List<Sys_task> taskList = dao.query(Sys_task.class, Cnd.where("disabled", "=", false));
        for (Sys_task sysTask : taskList) {
            try {
                QuartzJob qj = new QuartzJob();
                qj.setJobName(sysTask.getId());
                qj.setJobGroup(sysTask.getId());
                qj.setClassName(sysTask.getJobClass());
                qj.setCron(sysTask.getCron());
                qj.setComment(sysTask.getNote());
                qj.setDataMap(sysTask.getData());
                quartzManager.add(qj);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 初始化数据库
     *
     * @param config
     * @param dao
     */
    private void initSysData(NutConfig config, Dao dao) {


        Daos.createTablesInPackage(dao, "cn.wizzer.app", false);

        //pojo到表结构的迁移   
        Daos.migration(dao, "cn.wizzer.app.gy", true, false, false);
        Daos.migration(dao, "cn.wizzer.app.gy", true, false, false);
        Daos.migration(dao, "cn.wizzer.app.sys", true, false, false);
        Daos.migration(dao, "cn.wizzer.app.xm", true, false, false);

            //执行SQL脚本 微信和CMS部分sql
            // FileSqlManager fm = new FileSqlManager("db/");
            // List<Sql> sqlList = fm.createCombo(fm.keys());
            // Sql[] sqls = sqlList.toArray(new Sql[sqlList.size()]);
            // for (Sql sql : sqls) {
            //     dao.execute(sql);
            // }
    }

    /**
     * 初始化系统变量
     *
     * @param config
     * @param dao
     */
    private void initSysSetting(NutConfig config, Dao dao) {
        Globals.AppRoot = Strings.sNull(config.getAppRoot());//项目路径
        Globals.AppBase = Strings.sNull(config.getServletContext().getContextPath());//部署名
        Globals.initSysConfig(dao);
    }

    public void destroy(NutConfig config) {
        try {
            if (rabbitmq_channel != null)
                rabbitmq_channel.close();
            if (rabbitmq_conn != null)
                rabbitmq_conn.close();
        } catch (Exception e) {

        }
        // 非mysql数据库,或多webapp共享mysql驱动的话,以下语句删掉
        try {
            Mirror.me(Class.forName("com.mysql.jdbc.AbandonedConnectionCleanupThread")).invoke(null, "shutdown");
        } catch (Throwable e) {
        }
        // 解决quartz有时候无法停止的问题
        try {
            config.getIoc().get(Scheduler.class).shutdown(true);
        } catch (Exception e) {
        }
        // 解决com.alibaba.druid.proxy.DruidDriver和com.mysql.jdbc.Driver在reload时报warning的问题
        // 多webapp共享mysql驱动的话,以下语句删掉
        Enumeration<Driver> en = DriverManager.getDrivers();
        while (en.hasMoreElements()) {
            try {
                Driver driver = en.nextElement();
                String className = driver.getClass().getName();
                log.debug("deregisterDriver: " + className);
                DriverManager.deregisterDriver(driver);
            } catch (Exception e) {
            }
        }
        try {
            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("com.alibaba.druid:type=MockDriver");
            if (mbeanServer.isRegistered(objectName))
                mbeanServer.unregisterMBean(objectName);
            objectName = new ObjectName("com.alibaba.druid:type=DruidDriver");
            if (mbeanServer.isRegistered(objectName))
                mbeanServer.unregisterMBean(objectName);
        } catch (Exception ex) {
        }
    }
}
