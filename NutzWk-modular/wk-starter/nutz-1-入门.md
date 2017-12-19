---
title: Nutz-1-入门
date: 2017-10-31 15:44:41
tags: nutz
categories: java开源框架学习
---

# Nutz学习笔记

> 公司的业务代码压在我的身上，然而作死的性格又犯了，暑假的时候偷懒用jeecg撸出了毕业设计，但是遗憾的是，jeecg框架本身又许多的坑，虽然他很强大，在线代码生成的功能很强大，但是考虑到我只是撸一个Demo，之后的开发可能会留下深坑，而且，他的界面还是太丑了:)，我选择试一下Nutz这个新坑..

# 开源的Nutz项目



1. [*nutz*](https://github.com/nutzam/nutz)：轻量级的实现了SSH框架90%以上的功能，有时间一定要**看源码**。
2. [*Nutz*Wk](https://github.com/Wizzercn/NutzWk)：企业框架，在Nutz框架基础上搭建了建议的企业架构，可以节省很多的时间。
3. [*nutzmore*](https://github.com/nutzam/nutzmore)：一些扩展的插件。
4. [Rk_Cms](https://github.com/Rekoe/Rk_Cms)：基于Nutz写的CMS系统，但是我没跑出来。
5. [*NUTZ*-ONEKEY](https://github.com/Kerbores/NUTZ-ONEKEY)：企业开发脚手架。

昨天我大致了浏览了Nutz社区的文档，感觉还是很轻量的，但是我这个SSH框架没有玩好的菜逼，心里十分愧疚，这一段时间我会在完成自身公司项目的同时，进行自我学习的跟进！

## NutzWk项目源码解读

> 在阅读了一个文档的基础上进行工程项目的解读，需要我掌握工程项目的基本结构，以及主要功能点的实现，话又说回来，不都是对数据库花式的增删查改码~


1. wk-app：项目文件
2. wk-framework：wandal写的框架 
3. wk-starter：
4. wk-code ：代码生成器
5. wk-wiki：项目说明

### Pom.xml:

​	注册了两个modules

```
<modules>
    <module>wk-framework</module>
    <module>wk-app</module>
</modules>
```
​	编码格式：UTF-8
```
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
```
​	插件引用：快照版插件
```
<repositories>
    <repository>
        <id>nutzcn-snapshots</id>
        <url>https://jfrog.nutz.cn/artifactory/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

​	<build>引用maven的设置

> IDEA 的pom.xml值得我好好的深入

### Wk-app

> 很白痴的问题？为什么web.xml是web启动的核心？[web.xml配置详解](http://blog.csdn.net/guihaijinfen/article/details/8363839)

Src/Main/WEB-INF/Web.xml



##### Shiro

> Shiro水还是比较深的，可以深入的研究一下[Shiro教程](http://wiki.jikexueyuan.com/project/shiro/)

- Shiro 拦截器：`web.xml` 配置ShiroFilter拦截器
- Shiro 配置文件：`shiro.ini` 配置了shiro缓存、cookie等之外，还配置了登录路径及作用范围(url)，以及url对应的验证拦截器(如管理平台的PlatformAuthenticationFilter，将表单对象转为 shiro的token对象，captchaParam设置验证码表单参数名)和权限实现类(如管理平台的 PlatformAuthorizingRealm，验证token登录是否成功以及加载对应的菜单和权限)
- Shiro 过滤器：通过`nutzwk-mvc-chain.json` 配置整个MVC框架URL请求的动作链，其中 NutShiroProcessor 拦截shiro的注解方法并根据shiro抛出的异常，判断是否登录/是否具有权限，并根据是否Ajax请求，返回对应的错误消息；

```xml
    <display-name>NutzWk</display-name>

    <!-- Shiro -->
    <listener>
        <listener-class>org.apache.shiro.web.env.EnvironmentLoaderListener</listener-class>
    </listener>
    <filter>
        <filter-name>ShiroFilter</filter-name>
        <filter-class>org.apache.shiro.web.servlet.ShiroFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>ShiroFilter</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>
```

##### Custom Route

Custom Route自己写了一个Filter，核心的语句如下，但是我还没有**看出什么**。

```java
RouteFilter implements Filter

Sys_route route = Globals.RouteMap.get(Strings.sNull(req2.getRequestURI()).replace(Globals.AppBase, ""));
 
```

```
    <!-- Custom Route -->
    <filter>
        <filter-name>route</filter-name>
        <filter-class>cn.wizzer.app.web.commons.filter.RouteFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>route</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

##### Nutz Mvc

控制器初始化规定了主模块，过滤规则，不包括项目（exclusions），和匹配的模式。

```
    <!-- Nutz Mvc -->
    <filter>
        <filter-name>nutz</filter-name>
        <filter-class>org.nutz.mvc.NutFilter</filter-class>
        <init-param>
            <param-name>modules</param-name>
            <param-value>cn.wizzer.app.web.commons.core.Module</param-value>
        </init-param>
        <init-param>
            <param-name>ignore</param-name>
            <param-value>^(.+[.])(jsp|png|gif|jpg|js|css|jspx|jpeg|html|mp3|mp4|ico)$</param-value>
        </init-param>
        <init-param>
            <param-name>exclusions</param-name>
            <param-value>/assets/*,/druid/*,/upload/*,/apidoc/*</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>nutz</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>
```

主模块

```
@Modules(scanPackage = true, packages = "cn.wizzer")
@Ok("json:full")
@Fail("http:500")
@IocBy(type = ComboIocProvider.class, args = {"*json", "config/ioc/", "*anno", "cn.wizzer", "*jedis", "*tx", "*quartz", "*async", "*rabbitmq", "*wkcache"})
@Localization(value = "locales/", defaultLocalizationKey = "zh_CN")
@Encoding(input = "UTF-8", output = "UTF-8")
@Views({BeetlViewMaker.class, PdfViewMaker.class})
@SetupBy(value = Setup.class)
@ChainBy(args = "config/chain/nutzwk-mvc-chain.json")
@SessionBy(ShiroSessionProvider.class)
public class Module {
}
```

##### druid

[Druid说明文档](https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)

```
    <!-- druid -->
    <servlet>
        <servlet-name>DruidStatView</servlet-name>
        <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
        <init-param>
            <param-name>allow</param-name>
            <param-value>127.0.0.1</param-value>
        </init-param>
        <init-param>
            <param-name>exclusions</param-name>
            <param-value>*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,/assets/*,/upload/*,/apidoc/*</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>DruidStatView</servlet-name>
        <url-pattern>/druid/*</url-pattern>
    </servlet-mapping>
```
```
    <error-page>
        <error-code>403</error-code>
        <location>/WEB-INF/error/403.html</location>
    </error-page>
    <error-page>
        <error-code>404</error-code>
        <location>/WEB-INF/error/404.html</location>
    </error-page>
</web-app>
```
## 从Modules开始

- 
  @Modules(scanPackage = true, packages = "cn.wizzer")，扫描并自动注册子模块。
- @Ok("json:full")：成功这返回json:full
- @Fail("http:500")：失败返回
- @IocBy(type = ComboIocProvider.class, args = {"*json", "config/ioc/", "*anno", "cn.wizzer", "*jedis", "*tx", "*quartz", "*async", "*rabbitmq", "*wkcache"})：设置应用所采用的 Ioc 容器，**args不是很懂**。
- @Localization(value = "locales/", defaultLocalizationKey = "zh_CN")：国际化。
- @Encoding(input = "UTF-8", output = "UTF-8")：编码。
- @Views({BeetlViewMaker.class, PdfViewMaker.class})：视图的任务就是将入口函数的返回值（一个Java对象）渲染到 HTTP 响应流中，**两个返回值？**
- @SetupBy(value = Setup.class)：应用启动以及关闭时的额外处理，设置为setup.class。
- @ChainBy(args = "config/chain/nutzwk-mvc-chain.json")：整个 Nutz.Mvc 的应用，必须有且只能有一个动作链工厂。在**主模块**上，你可以声明你自己的 动作链工厂（通过 `@ChainBy` 注解）。当然，如果你没有声明这个注解，Nutz.Mvc 会 采用默认的动作链工厂实现类(**org.nutz.mvc.impl.NutActionChainMaker**)。
- @SessionBy(ShiroSessionProvider.class)：自定义session。

Modules中设置了很多有趣的注解，是Nutz框架的启动核心。

### Ioc和Dao

>  Dao和Ioc具体的知识需要新的理解

```json
var ioc = {
      conf : {
         type : "org.nutz.ioc.impl.PropertiesProxy",
         fields : {
            paths : ["config/custom/"]
         }
      },
       dataSource : {
         factory : "$conf#make",
         args : ["com.alibaba.druid.pool.DruidDataSource", "db."],
           type : "com.alibaba.druid.pool.DruidDataSource",
           events : {
              create : "init",
               depose : 'close'
           }
       },
      dao : {
         type : "org.nutz.dao.impl.NutDao",
         args : [{refer:"dataSource"}],
         fields : {
            executor : {refer:"cacheExecutor"}
         }
      },
      cacheExecutor : {
         type : "org.nutz.plugins.cache.dao.CachedNutDaoExecutor",
         fields : {
            cacheProvider : {refer:"cacheProvider"},
            cachedTableNames : ["sys_user", "sys_role", "sys_menu"]
         }
      },
      cacheProvider : {
         type : "org.nutz.plugins.cache.dao.impl.provider.EhcacheDaoCacheProvider",
         fields : {
            cacheManager : {refer:"cacheManager"} // 引用ehcache.json中定义的CacheManager
         },
         events : {
            create : "init"
         }
      }
};
```

1. conf
2. dataSource
3. dao
4. cacheExecutor
5. cacheProvider

### Setup.Class

自定义Setup继承org.nutz.mvc.Setup。

```
public class Setup implements org.nutz.mvc.Setup {
```

三个静态变量：**log,connection,channel**

```
private static final Log log = Logs.get();
private static Connection rabbitmq_conn;
private static Channel rabbitmq_channel;
```

Init

**NutConfig config是怎么来的？**，生成Ioc,Dao,

```java
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
```

cn.wizzer.app.web.commons.base.Globals从NutConfig中加载了项目配置项。

```java
//项目路径
public static String AppRoot = "";
//项目目录
public static String AppBase = "";
//项目名称
public static String AppName = "NutzWk 开发框架";
//项目短名称
public static String AppShrotName = "NutzWk";
//项目域名
public static String AppDomain = "127.0.0.1";
//文件上传路径
public static String AppUploadPath = "/upload";
// 是否启用了队列
public static boolean RabbitMQEnabled = false;
//系统自定义参数
public static Map<String, String> MyConfig = new HashMap<>();
//自定义路由
public static Map<String, Sys_route> RouteMap = new HashMap<>();
//微信map
public static Map<String, WxApi2Impl> WxMap=new HashMap<>();
```

### 接客流程预定

Nutz.Mvc 统一采用动作链机制来处理每一个 HTTP 请求。整个 Nutz.Mvc 的应用，必须有且只能有一个动作链工厂，如果你没有声明这个注解，Nutz.Mvc 会 采用默认的动作链工厂实现类(**org.nutz.mvc.impl.NutActionChainMaker**)。

> [Nutz动作链](https://nutzam.com/core/mvc/action_chain.html)



默认动作链:default chains

请求属性更新->编码->模块处理->过滤器->适配器->方法反射->view

**这里的一些源码有意思：**org.nutz.mvc.impl.processor.ModuleProcessor

```
{
    "default" : {
        ps : [
            "org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
            "org.nutz.mvc.impl.processor.EncodingProcessor",
            "org.nutz.mvc.impl.processor.ModuleProcessor",
            "org.nutz.mvc.impl.processor.ActionFiltersProcessor",
            "org.nutz.mvc.impl.processor.AdaptorProcessor",
            "org.nutz.mvc.impl.processor.MethodInvokeProcessor",
            "org.nutz.mvc.impl.processor.ViewProcessor"
        ],
        error : 'org.nutz.mvc.impl.processor.FailProcessor'
    }
}
```

NutzWk chains

- [GlobalsSettingProcessor](03.02.01.GlobalsSettingProcessor.md) 将全局变量、常用工具类、国际化语言对象输出到视图
- [LogTimeProcessor](03.02.02.LogTimeProcessor.md) 打印请求的响应耗时
- [XssSqlFilterProcessor](03.02.03.XssSqlFilterProcessor.md) 过滤SQL注入和跨站攻击关键词
- [NutShiroProcessor](../02.Shiro/02.02.Settings.md) Shiro权限拦截，在`权限体系`里有过说明

```json
var chain={
   "default" : {
      "ps" : [
           "cn.wizzer.app.web.commons.processor.LogTimeProcessor",
           "cn.wizzer.app.web.commons.processor.GlobalsSettingProcessor",
           "org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
            "org.nutz.mvc.impl.processor.EncodingProcessor",
            "org.nutz.mvc.impl.processor.ModuleProcessor",
              "cn.wizzer.app.web.commons.processor.NutShiroProcessor",
              "cn.wizzer.app.web.commons.processor.XssSqlFilterProcessor",
            "org.nutz.mvc.impl.processor.ActionFiltersProcessor",
            "org.nutz.mvc.impl.processor.AdaptorProcessor",
            "org.nutz.mvc.impl.processor.MethodInvokeProcessor",
           "org.nutz.mvc.impl.processor.ViewProcessor"
      ],
      "error" : 'org.nutz.mvc.impl.processor.FailProcessor'
   }
};
```

##### LogTimeProcessor

try{}final{}，在自身的动作链执行完毕之后进行Log登记。

```java
public class LogTimeProcessor extends AbstractProcessor {

    private static final Log log = Logs.get();

    public void process(ActionContext ac) throws Throwable {
        Stopwatch sw = Stopwatch.begin();
        try {
            doNext(ac);
        } finally {
            sw.stop();
            if (log.isDebugEnabled()) {
                HttpServletRequest req = ac.getRequest();
                log.debugf("[%-4s]URI=%s %sms", req.getMethod(), req.getRequestURI(), sw.getDuration());
            }
        }
    }
}
```

##### GlobalsSettingProcessor

在ActionContext中设置项目的属性。

```
public class GlobalsSettingProcessor extends AbstractProcessor {

   @SuppressWarnings("rawtypes")
   public void process(ActionContext ac) throws Throwable {
      ac.getRequest().setAttribute("AppRoot", Globals.AppRoot);
      ac.getRequest().setAttribute("AppBase", Globals.AppBase);
      ac.getRequest().setAttribute("AppName", Globals.AppName);
      ac.getRequest().setAttribute("AppDomain", Globals.AppDomain);
      ac.getRequest().setAttribute("AppShrotName", Globals.AppShrotName);
      ac.getRequest().setAttribute("shiro", Mvcs.ctx().getDefaultIoc().get(ShiroUtil.class));
      ac.getRequest().setAttribute("date", Mvcs.ctx().getDefaultIoc().get(DateUtil.class));
      ac.getRequest().setAttribute("string", Mvcs.ctx().getDefaultIoc().get(StringUtil.class));
      // 如果url中有语言属性则设置
      String lang=ac.getRequest().getParameter("lang");
      if (!Strings.isEmpty(lang)) {
         Mvcs.setLocalizationKey(lang);
      }else{
         // Mvcs.getLocalizationKey()  1.r.56 版本是null,所以要做两次判断, 1.r.57已修复为默认值 Nutz:Fix issue 1072
         lang=Strings.isBlank(Mvcs.getLocalizationKey())?Mvcs.getDefaultLocalizationKey():Mvcs.getLocalizationKey();
      }
      ac.getRequest().setAttribute("lang", lang);
      doNext(ac);
   }
}
```

##### 其他processor

NutShiroProcessor，XssSqlFilterProcessor。

### URL映射

对于任意一个请求:

- **http://** **www.myapp.com** **/app** **/module** **/action** **.suffix**

Nutz.Mvc 匹配的时候，只会关心这个部分：

- **/module** **/action**

它对 **.suffix** 不敏感，匹配之前，它会把 **.suffix** 切去。之后，它会通过注解 '@At' 寻找合适的入口函数，

>  **注:** 使用不带参数的@At()注解, 默认会使用方法名/类名的小写做为入口地址!

```
主模块 - @At("/a")
   子模块 - @At("/b")
       入口函数 - @At("/c")
```

## 系统模块分析

- 微信
- CMS
- 系统

> 先以系统模块分析为例子

### 以登陆为例

> URL:/platform/login

映射到@At("")注解的方法上，在某一个入口函数上声明了一个空的过滤器数组@Filters，那么，这个入口函数将不会应用任何过滤器。

```
@At("")
@Ok("re")
@Filters
public String login(HttpServletRequest req, HttpSession session) {
```
Shiro操作
```
    Subject subject = SecurityUtils.getSubject();
    if (subject.isAuthenticated()) {
        return "redirect:/platform/home";
    } else {
        try {
            HashMap<String, Object> map = RSAUtil.getKeys();
            //生成公钥和私钥
            RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
            //模
            String publicKeyModulus = publicKey.getModulus().toString(16);
            //公钥指数
            String publicKeyExponent = publicKey.getPublicExponent().toString(16);
            //私钥指数
            req.setAttribute("publicKeyExponent", publicKeyExponent);
            req.setAttribute("publicKeyModulus", publicKeyModulus);
            session.setAttribute("platformPrivateKey", privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
```
[BeetL集成](http://ibeetl.com/guide/#beetl_4_9_beetl:/platform/sys/login.html)，Nutz's View：类型有

- JSP － 采用 JSP 模板输出网页
- Redirect － 客户端重定向
- Forward － 服务器端中转
- Json － 将对象输出成 Json 字符串
- void - 什么都不做
- Raw - 二进制输出,图片输出,文件下载等

```
        return "beetl:/platform/sys/login.html";
    }
}
```

### 密码验证



```java
@At("/doLogin")
@Ok("json")
@Filters(@By(type = PlatformAuthenticationFilter.class))
public Object doLogin(@Attr("loginToken") AuthenticationToken token, HttpServletRequest req, HttpSession session) {
    int errCount = 0;
    try {
        //输错三次显示验证码窗口
        errCount = NumberUtils.toInt(Strings.sNull(SecurityUtils.getSubject().getSession(true).getAttribute("platformErrCount")));
        Subject subject = SecurityUtils.getSubject();
        ThreadContext.bind(subject);
        subject.login(token);
        Sys_user user = (Sys_user) subject.getPrincipal();
        int count = user.getLoginCount() == null ? 0 : user.getLoginCount();
        userService.update(Chain.make("loginIp", user.getLoginIp()).add("loginAt", (int) (System.currentTimeMillis() / 1000))
                        .add("loginCount", count + 1).add("isOnline", true)
                , Cnd.where("id", "=", user.getId()));
        Sys_log sysLog = new Sys_log();
        sysLog.setType("info");
        sysLog.setTag("用户登陆");
        sysLog.setSrc(this.getClass().getName() + "#doLogin");
        sysLog.setMsg("成功登录系统！");
        sysLog.setIp(StringUtil.getRemoteAddr());
        sysLog.setOpBy(user.getId());
        sysLog.setOpAt((int) (System.currentTimeMillis() / 1000));
        sysLog.setUsername(user.getUsername());
        sLogService.async(sysLog);
        return Result.success("login.success");
    } catch (CaptchaIncorrectException e) {
        //自定义的验证码错误异常
        return Result.error(1, "login.error.captcha");
    } catch (CaptchaEmptyException e) {
        //验证码为空
        return Result.error(2, "login.error.captcha");
    } catch (LockedAccountException e) {
        return Result.error(3, "login.error.locked");
    } catch (UnknownAccountException e) {
        errCount++;
        SecurityUtils.getSubject().getSession(true).setAttribute("platformErrCount", errCount);
        return Result.error(4, "login.error.user");
    } catch (AuthenticationException e) {
        errCount++;
        SecurityUtils.getSubject().getSession(true).setAttribute("platformErrCount", errCount);
        return Result.error(5, "login.error.user");
    } catch (Exception e) {
        errCount++;
        SecurityUtils.getSubject().getSession(true).setAttribute("platformErrCount", errCount);
        return Result.error(6, "login.error.system");
    }
}
```