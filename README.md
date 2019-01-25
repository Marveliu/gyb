面向在校大学生的分布式短期雇佣任务管理系统，基于[NutzWK](https://github.com/Wizzercn/NutzWk)，主要实现：

- 基于`Dubbo与Zookeeper`的微服务治理
- 基于`RabbitMq`的消息推送服务
- 基于`Shiro`的用户角色权限管理
- 基于`Quartz`的任务调度服务

# 环境依赖

- JDK 8 181 + 或 OpenJDK 11 +
- Maven 3.5.3 +
- Redis 4.0.8 +
- MySql 5.7 + 或 MariaDB 10.3.10 +
- Zookeeper 3.4.11 +

# 技术选型

- 微服务框架：Nutzboot
- 分布式框架：Dubbo、Zookeeper、Sentinel
- 安全框架：Shiro
- 任务调度：Quartz
- 数据库连接池：Druid
- 缓存框架：Redis、Ehcache、Wkcache
- 订阅发布：Redis
- 消息队列-Rabbitmq

# 模块说明

| 名称                 | 介绍                                         |
| -------------------- | -------------------------------------------- |
| gyb-nb-dubbo-sys     | 基础服务，提供用户权限管理基础服务           |
| gyb-nb-dubbo-msg:    | 提供邮件传输等消息消费服务，需要进行邮箱配置 |
| gyb-nb-dubbo-library | 任务，技能信息管理                           |
| gyb-nb-dubbo-gy      | 雇员认证，雇员基础等数据管理                 |
| gyb-nb-dubbo-xm      | 提供雇佣交易服务，维护阶段性任务流程。       |
| gyb-nb-dubbo-web     | 提供Web界面管理                              |
| gyb-nb-task          | 基于quartz进行自定任务调度                   |
| gyb-nb-cms           | CMS服务                                      |
| gyb-nb-web-api       | API支持                                      |

![系统架构图](.README_images/2c7c454d.png)

建议启用`DubboOps`，[项目](https://github.com/apache/incubator-dubbo-ops)。

![Dubbo看板](http://marveliu-md.oss-cn-beijing.aliyuncs.com/md/2019-01-24-151052.png)

# 使用说明

- 确保 `MySql、Redis、Zookeeper` 默认配置并已启动好[`application.properties` 可更改配置项]。
- MySql 创建名为 `gyb_nb` 的空数据库，项目启动时会自动建表，同时初始化数据。
- 项目根目录执行 `mvn clean install -Dmaven.test.skip=true`
- 在单个NB模块下执行 `mvn compile nutzboot:run` 运行或 `mvn package nutzboot:shade` 生成可执行jar包
- 在项目根目录执行 `mvn -Dnutzboot.dst=E:/dst clean package nutzboot:shade` 将所有可运行jar包生成到指定位置

- 可选择使用运行参数 `-Dnutz.profiles.active=prod` 加载 application-prod.properties 配置文件
- 正常启动后访问 `http://127.0.0.1:8080/sysadmin` 用户名 superadmin 密码 1

## 脚本

`bin`目录下包括基于maven构建和apidoc插件的相关脚本(需要在根目录下执行)，主要内容如下:

- 构建：`sh ./bin/build.sh`

```shell
# jardata shade打包
.
├── gyb-nb-service-cms.jar
├── gyb-nb-service-gy.jar
├── gyb-nb-service-library.jar
├── gyb-nb-service-msg.jar
├── gyb-nb-service-sys.jar
├── gyb-nb-service-xm.jar
├── gyb-nb-task.jar
├── gyb-nb-web-api.jar
└── gyb-nb-web-platform.jar
```

- 启动停止：`sh ./bin/gyb.sh start|stop`

- 单个服务启停：`sh ./bin/jar.sh 1`，1对应gyb-nb-service-sys，具体看`sh`。
- api生成：`sh ./bin/apidoc.sh`，主要生成gyb-nb-web-api的文档，相关配置设置见`apidoc.json`
- api文档ftp上传：`sh ./bin/apisync.sh`，脚本中可以自行设置服务器相关信息。

在启用过程之中，建议按照需要进行启动，且按照上表的模块顺序进行启动。

## 注意

如果出现`java.lang.SecurityException: JCE cannot authenticate the provider BC`，编辑文件 `${JAVA_HOME}/jre/lib/security/java.security`，在9下面添加 `security.provider.10=org.bouncycastle.jce.provider.BouncyCastleProvider`

拷贝 `bin/bcprov-jdk16-140.jar` 到 `${JAVA_HOME}/jre/lib/ext` 目录下。

## 界面

![p1](http://marveliu-md.oss-cn-beijing.aliyuncs.com/md/2019-01-25-032621.png)

![界面](http://marveliu-md.oss-cn-beijing.aliyuncs.com/md/2019-01-25-032409.png)

![p3](http://marveliu-md.oss-cn-beijing.aliyuncs.com/md/2019-01-25-032533.png)

