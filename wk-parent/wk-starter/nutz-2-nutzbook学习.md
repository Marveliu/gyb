---
title: Nutz-2-nutzbook学习
date: 2017-11-02 17:36:28
tags: [nutz,nutzbook]
categories: java开源框架学习
---



> 在实现Nutzbook源码的过程之中遇到的比较又意思的地方
>
> Log4j可以通过java程序动态设置，该方式明显缺点是：如果需要修改日志输出级别等信息，则必须修改java文件，然后重新编译，很是麻烦；
>
> log4j也可以通过配置文件的方式进行设置，目前支持两种格式的配置文件：
>
> •xml文件
> •properties文件（推荐）

# 资源扫描系统的Log

## 把这部分的log摘取出来. 以org.nutz.resource下的log为准

```
2015-03-30 10:49:49,383 org.nutz.resource.Scans.<init>(Scans.java:484) DEBUG - Locations for Scans:
[JarResourceLocation [jarPath=D:\nutzbook\apache-tomcat-8.0.20\bin\bootstrap.jar], JarResourceLocation [jarPath=D:\nutzbook\apache-tomcat-8.0.20\bin\tomcat-juli.jar], JarResourceLocation [jarPath=D:/nutzbook/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/nutzbook/WEB-INF/lib/nutz-1.b.52.jar], FileSystemResourceLocation [root=D:\nutzbook\eclipse], FileSystemResourceLocation [root=D:\nutzbook\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\nutzbook\WEB-INF\classes]]
2015-03-30 10:49:49,510 org.nutz.resource.Scans.init(Scans.java:75) DEBUG - Locations for Scans:
[JarResourceLocation [jarPath=D:\nutzbook\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\nutzbook\WEB-INF\lib\nutz-1.b.52.jar], JarResourceLocation [jarPath=D:\nutzbook\apache-tomcat-8.0.20\bin\bootstrap.jar], JarResourceLocation [jarPath=D:\nutzbook\apache-tomcat-8.0.20\bin\tomcat-juli.jar], JarResourceLocation [jarPath=D:\nutzbook\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\nutzbook\WEB-INF\lib\log4j-1.2.17.jar], JarResourceLocation [jarPath=D:\nutzbook\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\nutzbook\WEB-INF\lib\mysql-connector-java-5.1.34.jar], JarResourceLocation [jarPath=D:/nutzbook/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/nutzbook/WEB-INF/lib/nutz-1.b.52.jar], FileSystemResourceLocation [root=D:\nutzbook\eclipse], JarResourceLocation [jarPath=D:\nutzbook\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\nutzbook\WEB-INF\lib\druid-1.0.13.jar], FileSystemResourceLocation [root=D:\nutzbook\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\nutzbook\WEB-INF\classes]]
2015-03-30 10:49:49,618 org.nutz.resource.Scans.scan(Scans.java:228) DEBUG - Found 1 resource by src( ioc/ ) , regex( ^(.+[.])(js|json)$ )
2015-03-30 10:49:49,625 org.nutz.resource.Scans.scan(Scans.java:228) DEBUG - Found 4 resource by src( net/wendal/nutzbook/ ) , regex( ^.+[.]class$ )
10:49:50,070 org.nutz.resource.Scans.scan(Scans.java:228) DEBUG - Found 4 resource by src( net/wendal/nutzbook/ ) , regex( ^.+[.]class$ )

```

## 分析每条的含义

- 第一条是非Mvc环境下的Scans初始化的日志, 因为Scans类在加载时会先进行自我初始化
- 第二条,是设置ServletContext后,Scans类重新初始化,可以看到找到了Web环境下的classes目录和相关的jar包
- 第三条,是Ioc扫描js配置文件时输出的日志
  - `DEBUG - Found 1 resource by src( ioc/ ) , regex( ^(.+[.])(js|json)$ )` 代表在ioc目录下找到1个匹配的文件. 注意,这里的"ioc/"是指classpath下的ioc目录,这点非常重要
- 第四条,是@Modules注解所配置的scanPackage=true所触发的入口类扫描. 强调再强调, 注解只是配置信息,自身无代码操作.注解的实际作用,是取决与读取注解的代码如何理解这个注解,而跟这个注解具体的名字,属性是没有相关性的.
- 第五条,是Ioc扫描注解配置的log.

## 需要明白的几个点

- Modules跟Ioc扫描注解,是2个不同的动作, 因为Nutz中的Mvc与Ioc是分离的, 一个Module类并不强制为一个Ioc对象,反之也是.
- 注解只是配置信息. 如果没有代码去读取注解中的配置信息,这个注解是不会有意义的.

# 容器重要信息的Log

## 把相关的log给揪出来

```
2015-03-30 10:49:49,525 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:55) INFO  - Nutz Version : 1.r.59
2015-03-30 10:49:49,525 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:56) INFO  - Nutz.Mvc[nutz] is initializing ...
2015-03-30 10:49:49,525 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:60) DEBUG - Web Container Information:
2015-03-30 10:49:49,526 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:61) DEBUG -  - Default Charset : UTF-8
2015-03-30 10:49:49,526 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:62) DEBUG -  - Current . path  : D:\nutzbook\eclipse\.
2015-03-30 10:49:49,526 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:63) DEBUG -  - Java Version    : 1.8.0_112
2015-03-30 10:49:49,526 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:64) DEBUG -  - File separator  : \
2015-03-30 10:49:49,527 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:65) DEBUG -  - Timezone        : Asia/Shanghai
2015-03-30 10:49:49,527 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:66) DEBUG -  - OS              : Windows 7 amd64
2015-03-30 10:49:49,527 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:67) DEBUG -  - ServerInfo      : Apache Tomcat/8.0.20
2015-03-30 10:49:49,528 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:68) DEBUG -  - Servlet API     : 3.1
2015-03-30 10:49:49,528 org.nutz.mvc.impl.NutLoading.load(NutLoading.java:70) DEBUG -  - ContextPath     : /nutzbook
2015-03-30 10:49:49,529 org.nutz.mvc.impl.NutLoading.createContext(NutLoading.java:217) DEBUG - >> app.root = D:/nutzbook/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/nutzbook

```

## 一一解剖

- 第一条, Nutz版本信息, 一般来说,默认回答问题都是按最新版来考虑
- 第二条, 注意中括号里面值,这个值就是web.xml中NutFilter的名字
- 第三条, 就是个抬头
- 第四条, 默认编码,非常非常非常非常重要,请按之前的准备章节的配置,这里必然是UTF-8,不然,请检查人品,不行就充值一下吧
- 第五条, 当前.路径,也就是当前工作目录, 通常配错log4j文件输出路径的时候,就可以到这里找了
- 第六条, Java的版本号, 有时候系统装了N个JDK,这里就能看出来到底用了啥
- 第七条, 文件路径分隔符,拼路径的时候有用
- 第八条, 操作系统的信息
- 第九条, Web容器的信息, `Apache Tomcat/8.0.20` 多明白的信息,哈哈
- 第十条, Servelt API的版本号,一般依赖于Web容器的版本
- 第十一条, ContentPath, 也就是在${base}所指代的值,如果是ROOT应用,这里就是空字符串
- 第十二条, 该web项目在tomcat中的真实路径,有时候说某某资源找不到,到这个目录下确认之

## 为啥显示这些信息?

日志的好处在于观察日志的变化就可以明白程序中的状态.

记得在反馈那小节提到的内容不? "完整的Nutz日志信息", 这些信息都有助于筛选,重现特定的问题.

# Ioc初始化的Log

## Ioc初始化的log全部揪出来,哈哈

```
2015-03-30 10:49:49,603 org.nutz.mvc.impl.NutLoading.createIoc(NutLoading.java:354) DEBUG - @IocBy(type=org.nutz.mvc.ioc.provider.ComboIocProvider, args=["*js", "ioc/", "*anno", "net.wendal.nutzbook", "*tx"])
2015-03-30 10:49:49,618 org.nutz.resource.Scans.scan(Scans.java:228) DEBUG - Found 1 resource by src( ioc/ ) , regex( ^(.+[.])(js|json)$ )
2015-03-30 10:49:49,618 org.nutz.ioc.loader.json.JsonLoader.<init>(JsonLoader.java:44) DEBUG - loading ioc js config from [dao.js]
2015-03-30 10:49:49,622 org.nutz.ioc.loader.json.JsonLoader.<init>(JsonLoader.java:52) DEBUG - Loaded 2 bean define from path=[ioc/] --> [dataSource, dao]
2015-03-30 10:49:49,625 org.nutz.resource.Scans.scan(Scans.java:228) DEBUG - Found 4 resource by src( net/wendal/nutzbook/ ) , regex( ^.+[.]class$ )
2015-03-30 10:49:49,638 org.nutz.ioc.loader.annotation.AnnotationIocLoader.addClass(AnnotationIocLoader.java:83) DEBUG - Found a Class with Ioc-Annotation : class net.wendal.nutzbook.module.UserModule
2015-03-30 10:49:49,652 org.nutz.ioc.loader.annotation.AnnotationIocLoader.<init>(AnnotationIocLoader.java:60) INFO  - Scan complete ! Found 1 classes in 1 base-packages!
beans = ["userModule"]
2015-03-30 10:49:49,652 org.nutz.ioc.loader.json.JsonLoader.<init>(JsonLoader.java:36) DEBUG - Loaded 5 bean define from reader --
[txREPEATABLE_READ, txSERIALIZABLE, txNONE, txREAD_UNCOMMITTED, txREAD_COMMITTED]
```

## 来,把它宰了

- 第一条, 输出IocBy的具体配置,根本就是完全重现实际配置
- 第二条, 是资源扫描的log,但同时也是`"*js","ioc/"` 所对应的日志. 含义是在classpath下的ioc目录找到1个js/json文件
- 第三条, JsonLoader读取dao.js的前置输出, 如果js出错了,下一条log就会变成XXX的json报错信息
- 第四条, JsonLoader读取dao.js的后置输出, 解析完成后的, 读取到了2个ioc bean定义
- 第五条, 也是资源扫描的log,但同时也是`"*anno", "net.wendal.nutzbook"`所对应的日志, 含义是扫描net.wendal.nutzbook及子package的类
- 第六条, AnnotationIocLoader开始对扫描得到的类进行解析,寻找带@IocBean注解的类,这里就是找到的第一个类UserModule
- 第七条, AnnotationIocLoader结束处理的总结陈词, 说在N个根package中找到了M个注解ioc的定义.
- 最后一条, 看上去是JsonLoader的输出,实际上是`*tx`所对应的TransIocLoader的输出, TransIocLoader类代理了一个JsonLoader实例. 这个配置也把Aop事务的相关配置给加载了. 以前的做法是单独写个trans.js来声明.

# URL映射关系的Log

## 这个log比较多

```
2015-03-30 10:49:49,679 org.nutz.mvc.impl.Loadings.scanModules(Loadings.java:98) DEBUG - module class location 'file:/D:/nutzbook/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/nutzbook/WEB-INF/classes/net/wendal/nutzbook/MainModule.class'
2015-03-30 10:49:49,679 org.nutz.mvc.impl.Loadings.scanModuleInPackage(Loadings.java:126) DEBUG -  > scan 'net.wendal.nutzbook'
2015-03-30 10:49:49,681 org.nutz.resource.Scans.scan(Scans.java:228) DEBUG - Found 4 resource by src( net/wendal/nutzbook/ ) , regex( ^.+[.]class$ )
2015-03-30 10:49:49,682 org.nutz.mvc.impl.Loadings.checkModule(Loadings.java:140) DEBUG -    >> add 'net.wendal.nutzbook.module.UserModule'
2015-03-30 10:49:49,698 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/'                    >> UserModule.index(...)          : void       | @Ok(jsp:jsp.user.list) @Fail(http:500) | by 1 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,714 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/add'                 >> UserModule.add(...)            : Object     | @Ok(json:{locked:'password|salt',ignoreNull:true}) @Fail(http:500) | by 1 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,715 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/count'               >> UserModule.count(...)          : int        | @Ok(json:{locked:'password|salt',ignoreNull:true}) @Fail(http:500) | by 1 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,716 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/update'              >> UserModule.update(...)         : Object     | @Ok(json:{locked:'password|salt',ignoreNull:true}) @Fail(http:500) | by 1 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,720 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/delete'              >> UserModule.delete(...)         : Object     | @Ok(json:{locked:'password|salt',ignoreNull:true}) @Fail(http:500) | by 1 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,724 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/query'               >> UserModule.query(...)          : Object     | @Ok(json:{locked:'password|salt',ignoreNull:true}) @Fail(http:500) | by 1 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,726 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/login'               >> UserModule.login(...)          : Object     | @Ok(json:{locked:'password|salt',ignoreNull:true}) @Fail(http:500) | by 0 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,727 org.nutz.mvc.impl.UrlMappingImpl.printActionMapping(UrlMappingImpl.java:130) DEBUG -    '/user/logout'              >> UserModule.logout(...)         : void       | @Ok(>>:/ ) @Fail(http:500) | by 1 Filters | (I:UTF-8/O:UTF-8)
2015-03-30 10:49:49,728 org.nutz.mvc.impl.NutLoading.evalUrlMapping(NutLoading.java:203) INFO  - Found 8 module methods

```

## 来研究研究一下这堆log

- 第一条, 显示MainModule所在的实际路径, 这是解决Maven下的扫描出错的bug的日志
- 第二条, 显示正在扫描的package的路径
- 第三条, 资源扫描的log, 均为MainModule中`@Modules(scanPackage=true)`所触发的扫描行为. 这里所得到4个类,但不等于就是4个模块, 带`@At`入口方法的类,才会成为模块类
- 第四条开始, 就是各入口方法的信息了, 现在挑第一条进行完整讲解
  - `'/user/'`是映射的完整的路径. 类路径+方法路径
  - `void` 入口方法的返回值类型. 如果你配置了@Ok("json")然后返回值是String,它就是警告你.
  - `UserModule.index(...)` 映射的类名及方法名
  - `@Ok(jsp:jsp.user.list)` @Ok的配置,这里的含义是jsp视图的jsp.user.list路径.
  - `@Fail(http:500)` @Fail的配置,这是入口方法或适配器抛出异常才会执行的配置
  - `by 1 Filters` @Filters的配置,这里并不会给出完整的,不然真的很长很长. 请留意一下login方法时0个Filters.
  - `(I:UTF-8/O:UTF-8)` 最后是编码设置,影响的是POST时的body编码,注意啊,URL的编码是不会受影响的,那不是Servlet API标准里面可以设置的内容.

# Ioc获取对象的Log

## 前面说了Ioc初始化的log,这里说的是Ioc.get产生的log, 也就是从Ioc容器获取bean的log

```
2015-03-30 10:49:49,729 org.nutz.mvc.impl.NutLoading.evalSetup(NutLoading.java:253) INFO  - Setup application...
2015-03-30 10:49:49,729 org.nutz.ioc.impl.NutIoc.get(NutIoc.java:144) DEBUG - Get 'dao'<interface org.nutz.dao.Dao>
2015-03-30 10:49:49,730 org.nutz.ioc.impl.NutIoc.get(NutIoc.java:166) DEBUG -      >> Load definition
2015-03-30 10:49:49,732 org.nutz.ioc.loader.map.MapLoader.load(MapLoader.java:67) DEBUG - Loading define for name=dao
2015-03-30 10:49:49,736 org.nutz.ioc.loader.combo.ComboIocLoader.load(ComboIocLoader.java:137) DEBUG - Found IocObject(dao) in IocLoader(JsonLoader@1168025266)
2015-03-30 10:49:49,737 org.nutz.ioc.impl.NutIoc.get(NutIoc.java:193) DEBUG -      >> Make...'dao'<interface org.nutz.dao.Dao>
2015-03-30 10:49:49,750 org.nutz.ioc.aop.impl.DefaultMirrorFactory.getMirror(DefaultMirrorFactory.java:82) DEBUG - class org.nutz.dao.impl.NutDao , no config to enable AOP for this type.
2015-03-30 10:49:49,750 org.nutz.ioc.impl.ScopeContext.save(ScopeContext.java:59) DEBUG - Save object 'dao' to [app] 
2015-03-30 10:49:49,753 org.nutz.ioc.impl.NutIoc.get(NutIoc.java:144) DEBUG - Get 'dataSource'<>
2015-03-30 10:49:49,753 org.nutz.ioc.impl.NutIoc.get(NutIoc.java:166) DEBUG -      >> Load definition
2015-03-30 10:49:49,753 org.nutz.ioc.loader.map.MapLoader.load(MapLoader.java:67) DEBUG - Loading define for name=dataSource
2015-03-30 10:49:49,758 org.nutz.ioc.loader.combo.ComboIocLoader.load(ComboIocLoader.java:137) DEBUG - Found IocObject(dataSource) in IocLoader(JsonLoader@1168025266)
2015-03-30 10:49:49,758 org.nutz.ioc.impl.NutIoc.get(NutIoc.java:193) DEBUG -      >> Make...'dataSource'<>
2015-03-30 10:49:49,777 org.nutz.ioc.aop.impl.DefaultMirrorFactory.getMirror(DefaultMirrorFactory.java:82) DEBUG - class com.alibaba.druid.pool.DruidDataSource , no config to enable AOP for this type.
2015-03-30 10:49:49,777 org.nutz.ioc.impl.ScopeContext.save(ScopeContext.java:59) DEBUG - Save object 'dataSource' to [app] 
2015-03-30 10:49:49,811 com.alibaba.druid.pool.DruidDataSource.init(DruidDataSource.java:669) INFO  - {dataSource-1} inited

```

## 庖丁解牛

### 导致这些log的代码,其实就一句,在MainSetup里面.

```
Dao dao = ioc.get(Dao.class);

```

- 第一条, 这其实是@SetupBy的log, 正是我们之前写的MainSetup类里面调用了`ioc.get`
- 第二条, 这是ioc.get方法在打印方法参数,不要误会那是对应ioc bean的属性. 另外, NutIoc是认名称,不是认类型的,默认就是首字母小写之后的类名(如果没有@IocBean或@InjectName配置的话)
- 第三条, 开始载入配置, 前置log,事情还没发生的
- 第四条, MapLoader(JsonLoader的超类)中找到名为dao的配置
- 第五条, ComboIocLoader提示在JsonLoader中找到名为dao的配置了
- 第六条, 再输出一次修正后的参数, 因为没东西需要修正,所以还是原样输出
- 第七条, 是AOP的拦截点, 因为没有对应的匹配方法,所以不需要对这个类启用AOP,跳过生成子类
- 第八条, 把当前的dao这bean的对象提前放入NutIoc的上下文, 是为了解决构造方法注入的问题
- 第九条到最后, dao这个bean的配置中引用了dataSource, 所以,往下的log都是为了生成dataSource对应的对象

# Dao的日志初探

## 终于讲到Dao的日志了

```
2015-03-30 10:49:49,836 org.nutz.filepool.NutFilePool.<init>(NutFilePool.java:23) INFO  - Init file-pool by: C:\Users\wendal/.nutz/tmp/dao/ [200000]
2015-03-30 10:49:49,837 org.nutz.filepool.NutFilePool.<init>(NutFilePool.java:37) DEBUG - file-pool.home: 'C:\Users\wendal\.nutz\tmp\dao'
2015-03-30 10:49:49,843 org.nutz.filepool.NutFilePool.<init>(NutFilePool.java:66) INFO  - file-pool.cursor: 120
2015-03-30 10:49:49,848 org.nutz.dao.jdbc.Jdbcs.<clinit>(Jdbcs.java:85) DEBUG - Jdbcs init complete
2015-03-30 10:49:49,849 org.nutz.dao.jdbc.Jdbcs.getExpert(Jdbcs.java:98) INFO  - Get Connection from DataSource for JdbcExpert
2015-03-30 10:49:50,062 org.nutz.dao.impl.DaoSupport$1.invoke(DaoSupport.java:165) DEBUG - JDBC Driver --> mysql-connector-java-5.1.34 ( Revision: jess.balint@oracle.com-20141014163213-wqbwpf1ok2kvo1om )
2015-03-30 10:49:50,063 org.nutz.dao.impl.DaoSupport$1.invoke(DaoSupport.java:166) DEBUG - JDBC Name   --> MySQL Connector Java
2015-03-30 10:49:50,063 org.nutz.dao.impl.DaoSupport.setDataSource(DaoSupport.java:174) DEBUG - Database info --> MYSQL:[MySQL - 5.5.5-10.1.2-MariaDB]
2015-03-30 10:49:50,070 org.nutz.resource.Scans.scan(Scans.java:228) DEBUG - Found 4 resource by src( net/wendal/nutzbook/ ) , regex( ^.+[.]class$ )
2015-03-30 10:49:50,120 org.nutz.dao.impl.sql.run.NutDaoExecutor._runSelect(NutDaoExecutor.java:193) DEBUG - SELECT COUNT(*) FROM t_user

```

## 还有什么同义词啊,啊啊啊, 逐行分析Log?

- 第一条, 初始化文件池,这是操作Blob/Clob时用到的东西,在虚拟主机使用nutz的时候有可能报错,这时候可以通过修改 *org/nutz/dao/jdbc/nutz_jdbc_experts.js* 修改里面的路径
- 第二条及第三条,提示路径及当前的偏移量
- 第四条, Jdbcs类加载nutz_jdbc_experts.js完成的提示,这是各种数据库方言的配置文件
- 第五条, 第一次从数据库连接池获取连接的前置log, 如果数据库配置出错,网络不通等等问题, 后面就只有报错的log了
- 第六条, 驱动程序的版本
- 第七条, 数据库版本. 我现在比较习惯MariaDB, 基本用法与mysql无异.
- 第八条, 是资源扫描的log,在扫描那些bean带@Table注解, 触发这句log的代码是 `Daos.createTablesInPackage(dao, "net.wendal.nutzbook", false);`
- 第九条, 是查询User表有多少记录, java语句是 `dao.count(User.class)`

在第一次启动的时候(或之后需要清除数据库的时候), 会有建表,插入之类的语

# 其他小Log

## 经过前面几个小节,还剩下3行没提及的log

```
2015-03-30 10:49:49,667 org.nutz.mvc.impl.NutLoading.createViewMakers(NutLoading.java:344) DEBUG - @Views(DefaultViewMaker)
2015-03-30 10:49:49,675 org.nutz.mvc.impl.NutLoading.createChainMaker(NutLoading.java:245) DEBUG - @ChainBy(org.nutz.mvc.impl.NutActionChainMaker)
2015-03-30 10:49:49,728 org.nutz.mvc.impl.NutLoading.evalLocalization(NutLoading.java:309) DEBUG - @Localization not define

```

## 就三行了,吃了它

- 第一条, 是@Views配置,当前MainModule并没有配置,所以这是默认配置,将来用到自定义View的时候自然会配置上
- 第二条, ChainBy是动作链配置, 是Mvc中的核心配置,也是当前很少人用到的强大配置之一, 下一章就会用到
- 第三条, 是国际化相关, 在稍后的章节中会开始使用.