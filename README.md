
# 使用说明

项目依赖需要，请务必开启以下三个服务
1. redis-server
2. zKserver
3. rabbitMq

启动：`sh ./bin/start.sh start system`
停止：`sh ./bin/start.sh stop system`

如果出现`java.lang.SecurityException: JCE cannot authenticate the provider BC`

1. 编辑文件 ${JAVA_HOME}/jre/lib/security/java.security,在9下面添加 security.provider.10=org.bouncycastle.jce.provider.BouncyCastleProvider
2. 拷贝 bin/bcprov-jdk16-140.jar 到 ${JAVA_HOME}/jre/lib/ext 目录下



# 缺陷管理

TAPD和Github通过webhooks进行绑定，通过commit message传递消息,模板如下（100018为bug编号，Marveliu为TAPD昵称）:

```
--bug=1000018 --user=Marveliu
add:*
update:*
fix:*
upgrade:*
```


