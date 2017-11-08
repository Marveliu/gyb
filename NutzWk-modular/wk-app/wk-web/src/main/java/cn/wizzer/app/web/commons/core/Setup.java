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
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.Record;
import org.nutz.dao.entity.annotation.Table;
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
import org.nutz.resource.Scans;
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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
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
        // 若必要的数据表不存在，则初始化数据库
        if (0 == dao.count(Sys_user.class)) {
            //初始化配置表
            Sys_config conf = new Sys_config();
            conf.setConfigKey("AppName");
            conf.setConfigValue("NutzWk 开发框架");
            conf.setNote("系统名称");
            dao.insert(conf);
            conf = new Sys_config();
            conf.setConfigKey("AppShrotName");
            conf.setConfigValue("NutzWk");
            conf.setNote("系统短名称");
            dao.insert(conf);
            conf = new Sys_config();
            conf.setConfigKey("AppDomain");
            conf.setConfigValue("127.0.0.1");
            conf.setNote("系统域名");
            dao.insert(conf);
            conf = new Sys_config();
            conf.setConfigKey("AppUploadPath");
            conf.setConfigValue("/upload");
            conf.setNote("文件上传文件夹");
            dao.insert(conf);
            //初始化单位
            Sys_unit unit = new Sys_unit();
            unit.setPath("0001");
            unit.setName("系统管理");
            unit.setAliasName("System");
            unit.setLocation(0);
            unit.setAddress("银河-太阳系-地球");
            unit.setEmail("wizzer@qq.com");
            unit.setTelephone("");
            unit.setHasChildren(false);
            unit.setParentId("");
            unit.setWebsite("http://www.wizzer.cn");
            Sys_unit dbunit = dao.insert(unit);
            //初始化菜单
            List<Sys_menu> menuList = new ArrayList<Sys_menu>();
            Sys_menu menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001");
            menu.setName("系统");
            menu.setNote("系统");
            menu.setAliasName("System");
            menu.setIcon("");
            menu.setLocation(0);
            menu.setHref("");
            menu.setTarget("");
            menu.setIsShow(true);
            menu.setHasChildren(true);
            menu.setParentId("");
            menu.setType("menu");
            menu.setPermission("sys");
            Sys_menu m0 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("00010001");
            menu.setName("系统管理");
            menu.setNote("系统管理");
            menu.setAliasName("Manager");
            menu.setIcon("ti-settings");
            menu.setLocation(0);
            menu.setHref("");
            menu.setTarget("");
            menu.setIsShow(true);
            menu.setHasChildren(true);
            menu.setParentId(m0.getId());
            menu.setType("menu");
            menu.setPermission("sys.manager");
            Sys_menu m1 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010001");
            menu.setName("单位管理");
            menu.setAliasName("Unit");
            menu.setLocation(0);
            menu.setHref("/platform/sys/unit");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.unit");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m2 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100010001");
            menu.setName("添加单位");
            menu.setAliasName("Add");
            menu.setLocation(0);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.unit.add");
            menu.setParentId(m2.getId());
            menu.setType("data");
            Sys_menu m21 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100010002");
            menu.setName("修改单位");
            menu.setAliasName("Edit");
            menu.setLocation(0);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.unit.edit");
            menu.setParentId(m2.getId());
            menu.setType("data");
            Sys_menu m22 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100010003");
            menu.setName("删除单位");
            menu.setAliasName("Delete");
            menu.setLocation(0);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.unit.delete");
            menu.setParentId(m2.getId());
            menu.setType("data");
            Sys_menu m23 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010002");
            menu.setName("用户管理");
            menu.setAliasName("User");
            menu.setLocation(0);
            menu.setHref("/platform/sys/user");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.user");
            menu.setHasChildren(false);
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m3 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100020001");
            menu.setName("添加用户");
            menu.setAliasName("Add");
            menu.setLocation(0);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.user.add");
            menu.setParentId(m3.getId());
            menu.setType("data");
            Sys_menu m31 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100020002");
            menu.setName("修改用户");
            menu.setAliasName("Edit");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.user.edit");
            menu.setParentId(m3.getId());
            menu.setType("data");
            Sys_menu m32 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100020003");
            menu.setName("删除用户");
            menu.setAliasName("Delete");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.user.delete");
            menu.setParentId(m3.getId());
            menu.setType("data");
            Sys_menu m33 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010003");
            menu.setName("角色管理");
            menu.setAliasName("Role");
            menu.setLocation(0);
            menu.setHref("/platform/sys/role");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.role");
            menu.setTarget("data-pjax");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m4 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100030001");
            menu.setName("添加角色");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.role.add");
            menu.setParentId(m4.getId());
            menu.setType("data");
            Sys_menu m41 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100030002");
            menu.setName("修改角色");
            menu.setAliasName("Edit");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.role.edit");
            menu.setParentId(m4.getId());
            menu.setType("data");
            Sys_menu m42 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100030003");
            menu.setName("删除角色");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.role.delete");
            menu.setParentId(m4.getId());
            menu.setType("data");
            Sys_menu m43 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100030004");
            menu.setName("分配菜单");
            menu.setAliasName("SetMenu");
            menu.setLocation(4);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.role.menu");
            menu.setParentId(m4.getId());
            menu.setType("data");
            Sys_menu m44 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100030005");
            menu.setName("分配用户");
            menu.setAliasName("SetUser");
            menu.setLocation(5);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.role.user");
            menu.setParentId(m4.getId());
            menu.setType("data");
            Sys_menu m45 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010004");
            menu.setName("菜单管理");
            menu.setAliasName("Menu");
            menu.setLocation(0);
            menu.setHref("/platform/sys/menu");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.menu");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m5 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100040001");
            menu.setName("添加菜单");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.menu.add");
            menu.setParentId(m5.getId());
            menu.setType("data");
            Sys_menu m51 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100040002");
            menu.setName("修改菜单");
            menu.setAliasName("Edit");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.menu.edit");
            menu.setParentId(m5.getId());
            menu.setType("data");
            Sys_menu m52 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100040003");
            menu.setName("删除菜单");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.menu.delete");
            menu.setParentId(m5.getId());
            menu.setType("data");
            Sys_menu m53 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010005");
            menu.setName("系统参数");
            menu.setAliasName("Param");
            menu.setLocation(0);
            menu.setHref("/platform/sys/conf");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.conf");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m6 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100050001");
            menu.setName("添加参数");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.conf.add");
            menu.setParentId(m6.getId());
            menu.setType("data");
            Sys_menu m61 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100050002");
            menu.setName("修改参数");
            menu.setAliasName("Edit");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.conf.edit");
            menu.setParentId(m6.getId());
            menu.setType("data");
            Sys_menu m62 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100050003");
            menu.setName("删除参数");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.conf.delete");
            menu.setParentId(m6.getId());
            menu.setType("data");
            Sys_menu m63 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010006");
            menu.setName("日志管理");
            menu.setAliasName("Log");
            menu.setLocation(0);
            menu.setHref("/platform/sys/log");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.log");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m7 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100060001");
            menu.setName("清除日志");
            menu.setAliasName("Delete");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.log.delete");
            menu.setParentId(m7.getId());
            menu.setType("data");
            Sys_menu m71 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010007");
            menu.setName("定时任务");
            menu.setAliasName("Task");
            menu.setLocation(0);
            menu.setHref("/platform/sys/task");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.task");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m8 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100070001");
            menu.setName("添加任务");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.task.add");
            menu.setParentId(m8.getId());
            menu.setType("data");
            Sys_menu m81 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100070002");
            menu.setName("修改任务");
            menu.setAliasName("Edit");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.task.edit");
            menu.setParentId(m8.getId());
            menu.setType("data");
            Sys_menu m82 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100070003");
            menu.setName("删除任务");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.task.delete");
            menu.setParentId(m8.getId());
            menu.setType("data");
            Sys_menu m83 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010008");
            menu.setName("自定义路由");
            menu.setAliasName("Route");
            menu.setLocation(0);
            menu.setHref("/platform/sys/route");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.route");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu m9 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100080001");
            menu.setName("添加路由");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.route.add");
            menu.setParentId(m9.getId());
            menu.setType("data");
            Sys_menu m91 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100080002");
            menu.setName("修改路由");
            menu.setAliasName("Edit");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.route.edit");
            menu.setParentId(m9.getId());
            menu.setType("data");
            Sys_menu m92 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100080003");
            menu.setName("删除路由");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.route.delete");
            menu.setParentId(m9.getId());
            menu.setType("data");
            Sys_menu m93 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010009");
            menu.setName("应用管理");
            menu.setAliasName("App");
            menu.setLocation(0);
            menu.setHref("/platform/sys/api");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.api");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu mm1 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100090001");
            menu.setName("添加应用");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.api.add");
            menu.setParentId(mm1.getId());
            menu.setType("data");
            Sys_menu mm2 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100090002");
            menu.setName("修改应用");
            menu.setAliasName("Edit");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.api.edit");
            menu.setParentId(mm1.getId());
            menu.setType("data");
            Sys_menu mm3 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100090003");
            menu.setName("删除应用");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.api.delete");
            menu.setParentId(mm1.getId());
            menu.setType("data");
            Sys_menu mm4 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010010");
            menu.setName("数据字典");
            menu.setAliasName("Dict");
            menu.setLocation(0);
            menu.setHref("/platform/sys/dict");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.dict");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu d = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100100001");
            menu.setName("添加字典");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.dict.add");
            menu.setParentId(d.getId());
            menu.setType("data");
            Sys_menu d1 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100100002");
            menu.setName("修改字典");
            menu.setAliasName("Edit");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.dict.edit");
            menu.setParentId(d.getId());
            menu.setType("data");
            Sys_menu d2 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100100003");
            menu.setName("删除字典");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.dict.delete");
            menu.setParentId(d.getId());
            menu.setType("data");
            Sys_menu d3 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("000100010011");
            menu.setName("插件管理");
            menu.setAliasName("Plugin");
            menu.setLocation(0);
            menu.setHref("/platform/sys/plugin");
            menu.setTarget("data-pjax");
            menu.setIsShow(true);
            menu.setPermission("sys.manager.plugin");
            menu.setParentId(m1.getId());
            menu.setType("menu");
            Sys_menu p = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100110001");
            menu.setName("添加插件");
            menu.setAliasName("Add");
            menu.setLocation(1);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.plugin.add");
            menu.setParentId(p.getId());
            menu.setType("data");
            Sys_menu p1 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100110002");
            menu.setName("启用禁用");
            menu.setAliasName("Update");
            menu.setLocation(2);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.plugin.update");
            menu.setParentId(p.getId());
            menu.setType("data");
            Sys_menu p2 = dao.insert(menu);
            menu = new Sys_menu();
            menu.setDisabled(false);
            menu.setPath("0001000100110003");
            menu.setName("删除插件");
            menu.setAliasName("Delete");
            menu.setLocation(3);
            menu.setIsShow(false);
            menu.setPermission("sys.manager.plugin.delete");
            menu.setParentId(p.getId());
            menu.setType("data");
            Sys_menu p3 = dao.insert(menu);
            //初始化角色
            Sys_role role = new Sys_role();
            role.setName("公共角色");
            role.setCode("public");
            role.setAliasName("Public");
            role.setNote("All user has role");
            role.setUnitid("");
            role.setDisabled(false);
            dao.insert(role);
            role = new Sys_role();
            role.setName("系统管理员");
            role.setCode("sysadmin");
            role.setAliasName("Sysadmin");
            role.setNote("System Admin");
            role.setUnitid("");
            role.setMenus(menuList);
            role.setDisabled(false);
            Sys_role dbrole = dao.insert(role);
            //初始化用户
            Sys_user user = new Sys_user();
            user.setLoginname("superadmin");
            user.setUsername("超级管理员");
            user.setOpAt(1466571305);
            RandomNumberGenerator rng = new SecureRandomNumberGenerator();
            String salt = rng.nextBytes().toBase64();
            String hashedPasswordBase64 = new Sha256Hash("1", salt, 1024).toBase64();
            user.setSalt(salt);
            user.setPassword(hashedPasswordBase64);
            user.setLoginIp("127.0.0.1");
            user.setLoginAt(0);
            user.setLoginCount(0);
            user.setEmail("wizzer@qq.com");
            user.setLoginTheme("palette.css");
            user.setLoginBoxed(false);
            user.setLoginScroll(false);
            user.setLoginSidebar(false);
            user.setLoginPjax(true);
            user.setUnitid(dbunit.getId());
            Sys_user dbuser = dao.insert(user);
            //不同的插入数据方式(安全)
            dao.insert("sys_user_unit", Chain.make("userId", dbuser.getId()).add("unitId", dbunit.getId()));
            dao.insert("sys_user_role", Chain.make("userId", dbuser.getId()).add("roleId", dbrole.getId()));
            //执行SQL脚本
            FileSqlManager fm = new FileSqlManager("db/");
            List<Sql> sqlList = fm.createCombo(fm.keys());
            Sql[] sqls = sqlList.toArray(new Sql[sqlList.size()]);
            for (Sql sql : sqls) {
                dao.execute(sql);
            }
            //菜单关联到角色
            dao.execute(Sqls.create("INSERT INTO sys_role_menu(roleId,menuId) SELECT @roleId,id FROM sys_menu").setParam("roleId", dbrole.getId()));

        }
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
