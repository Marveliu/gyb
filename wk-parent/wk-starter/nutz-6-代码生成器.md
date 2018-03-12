---
layout: post
title: nutz-6-代码生成器
date: 2017-11-17 09:06:16
tags: [nutz]
categories: java开源框架学习
photos:
---



nutz代码生成器遇到最深坑。



支持两种方式：

1. idea插件：**感觉没用**。
2. 本地安装nutzWk中code-generator的jar包：可行，但是又限制。
   1. 针对与nutzWk项目，会引用到code-generator这个modules。
   2. 生成机制问题。

必须要编译该项目，因此插件要访问生成的classes目录。
读取文件路径：需要扫描当前文件下包的所有目录，貌似还会进行一些判断，如果其他的文件有问题就gg。

例子：原本想在`gy.modules`中生成的，但是貌似会检索到`gy_inf`，可能是我在中间动过`gy_inf,` 导致报错
但是我重新建立一个文件夹在进行生成就好了
![项目目录](http://opm06mqes.bkt.clouddn.com/17-11-17/76384981.jpg)

报错日志如下：

```java
DEBUG] 15:27:53.449cn.wizzer.commons.code.EntityDescLoader.loadTables(EntityDescLoader.java:39) -output dir =D:\Project\gyb\NutzWk-modular\wk-app\wk-web\target\classes\cn\wizzer\app\gy\modules\models

Exception in thread"main" java.lang.NoClassDefFoundError:cn/wizzer/framework/base/service/BaseService

atjava.lang.ClassLoader.defineClass1(Native Method)
atjava.lang.ClassLoader.defineClass(ClassLoader.java:763)
atjava.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
atjava.net.URLClassLoader.defineClass(URLClassLoader.java:467)
atjava.net.URLClassLoader.access$100(URLClassLoader.java:73)
atjava.net.URLClassLoader$1.run(URLClassLoader.java:368)
atjava.net.URLClassLoader$1.run(URLClassLoader.java:362)
atjava.security.AccessController.doPrivileged(Native Method)
atjava.net.URLClassLoader.findClass(URLClassLoader.java:361)
atjava.lang.ClassLoader.loadClass(ClassLoader.java:424)
atsun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
atjava.lang.ClassLoader.loadClass(ClassLoader.java:357)
atjava.lang.Class.getDeclaredFields0(Native Method)
atjava.lang.Class.privateGetDeclaredFields(Class.java:2583)
atjava.lang.Class.getDeclaredFields(Class.java:1916)
atorg.nutz.lang.Mirror._getFields(Mirror.java:557)
atorg.nutz.lang.Mirror.getFields(Mirror.java:535)
atcn.wizzer.commons.code.EntityDescLoader.loadTables(EntityDescLoader.java:72)
atcn.wizzer.commons.code.Generator.main(Generator.java:176)
Caused by:java.lang.ClassNotFoundException: cn.wizzer.framework.base.service.BaseService
atjava.net.URLClassLoader.findClass(URLClassLoader.java:381)
atjava.lang.ClassLoader.loadClass(ClassLoader.java:424)
atsun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
atjava.lang.ClassLoader.loadClass(ClassLoader.java:357)
...19 more
Disconnected fromthe target VM, address: '127.0.0.1:53719', transport: 'socket'
```


简单的debug了一下位置：


```java
publicclassEntityDescLoaderextendsLoader{
privatestaticfinalLoglog=Logs.get();

@Override
publicMap<String,TableDescriptor>loadTables

StringabstractPath=URLDecoder.decode(path,"utf8");
File[]files=Files.lsFile(abstractPath,null);
Map<String,TableDescriptor>tables=newHashMap<String,TableDescriptor>();
```