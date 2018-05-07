### 协作须知

1. checkout 分支
2. pr 分支到masterdoc
3. 具体查看gitflow的协作模式。
4. terminal下：sh ./bin/apidoc.sh 生成apidoc文件

### 分支说明

master：主分支，即d服务器运行的分支。
gyb-boot：微服务分支。
    - gy-nb-service-sys:dubbo-55010
    - gy-nb-service-library:dubbo-55011
    - gy-nb-service-platform:consumer

### 提交说明

> 每一次提交请按照以下方式说明你的具体工作，方便review。

- 功能添加：`add:[what you have done]`
- 功能删除：`del:[what you have deleted]`
- 漏洞修改：`fix:[what you have fixed]`
- 功能优化：`update:[waht you have updated]`

> 标签说明，使用tags标记当前版本

### 缺陷管理

> TAPD和Github通过webhooks进行绑定，通过commit message传递消息

commit message template：100018为bug编号，Marveliu为TAPD昵称。

```
--bug=1000018 --user=Marveliu
add:*
update:*
fix:*
upgrade:*
```

推送到master的分支上记录详细的主版本，例如`v1.2.3`，协作进行协作的时候请标记`v1.2.3.*`，`*`具体由你而定。


