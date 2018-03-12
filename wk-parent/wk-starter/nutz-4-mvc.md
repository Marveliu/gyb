---
layout: post
title: nutz-4-mvc
date: 2017-11-12 17:22:57
tags: [MVC,nutz]
categories: java开源框架学习
photos:
---

> MVC这个东西大家都有，关键是要理解，怎么建立的这一个请求，响应，转发的机制。



MVC是将客户端请求的url，转发到对应的controller，一些框架还支持对请求进行预处理，比如封装注入对象的方法等，使得使用的方法更加的简单，至于返回的view，还支持三方的集成等操作。



# url-mapping

非常的重要一点，MVC必须维护一个url-controller映射的关系，实现mvc框架最最基本的功能。

```java
public UrlMapping load(NutConfig config) {
	....
    Ioc ioc = null;
    Stopwatch sw = Stopwatch.begin();
    UrlMapping mapping1;
    try {
        Class e = config.getMainModule();					//后去主函数
        this.createContext(config);							//创建context
        ioc = this.createIoc(config, e);					//创建ioc
        mapping1 = this.evalUrlMapping(config, e, ioc);		//根据上面三个变量信息执行扫描
        this.evalLocalization(config, e);
        this.createSessionProvider(config, e);
        this.evalSetup(config, e);
    } catch (Exception var8) {
        if(log.isErrorEnabled()) {
            log.error("Error happend during start serivce!", var8);
        }
        if(ioc != null) {
            log.error("try to depose ioc");
            try {
                ioc.depose();
            } catch (Throwable var7) {
                log.error("error when depose ioc", var8);
            }
        }
        throw (LoadingException)Lang.wrapThrow(var8, LoadingException.class);
    }
    sw.stop();
    if(log.isInfoEnabled()) {
        log.infof("Nutz.Mvc[%s] is up in %sms", new Object[]{config.getAppName(), Long.valueOf(sw.getDuration())});
    }
    return mapping1;
}
```



# 改写urlmapping

想自己改写urlmapping 方式， 

由于Nutz是零配置的，所以通过URL找到处理类以及跳转的页面，就显得很麻烦，不方便维护。

于是，我在大神兽的指导下，实现如下功能：在项目启动时，将URL路径、类、方法、以及跳转页面写入项目中的一个文件中，方便查看。

```
@UrlMappingBy(value=UrlMappingSet.class)
public class MainModule { 
}
```

在Nutz入口类，加入 @UrlMappingBy。

```java
UrlMappingSet.java 实现在 /WEB-INF/ 目录下生成 paths.txt 文件，记录路径，文件格式如下：
      

/**
 * 类描述： 创建人：Wizzer 联系方式：www.wizzer.cn 创建时间：2013-12-19 下午10:36:17
 * 
 * @version
 */
public class UrlMappingSet extends UrlMappingImpl {				//继承了基本的urlmappingimp
	private static int count = 0;

	public void add(ActionChainMaker maker, ActionInfo ai, NutConfig config) {
		super.add(maker, ai, config);
		printActionMappingThis(ai);

	}

	protected void printActionMappingThis(ActionInfo ai) {
		String[] paths = ai.getPaths();
		StringBuilder sb = new StringBuilder();
		if (null != paths && paths.length > 0) {
			sb.append(paths[0]);
			for (int i = 1; i < paths.length; i++)
				sb.append(",").append(paths[i]);
		} else {
			throw Lang.impossible();
		}
		sb.append("\n");
		// 打印方法名
		Method method = ai.getMethod();
		String str;
		if (null != method)
			str = String.format("%-30s : %-10s", Lang.simpleMetodDesc(method),
					method.getReturnType().getSimpleName());
		else
			throw Lang.impossible();

		sb.append("\t" + ai.getModuleType().getName());
		sb.append("\n\r");
		sb.append("\t" + str);
		sb.append("\n");
		String filepath = Mvcs.getServletContext().getRealPath("/WEB-INF/")
				+ "/paths.txt";
		File f = new File(filepath);
		if (count == 0) {
			Files.write(f, sb.toString());
		} else {

			Files.appendWrite(f, sb.toString());
		}
		count++;
	}
}
```

## 热部署的问题

在系统进行热部署的时候，虽然类似jrebel的插件可以支持函数名，参数的动态更新，但是对于注解，还是不支持的，如果能够在系统运行的时候，进行资源的重载，重新扫包就好了。

我感觉是可以的，留一个金手指，手动调用重载函数？但是感觉很重启系统没差啊。

扫描包的核心方法：

```java
protected UrlMapping evalUrlMapping(NutConfig config, Class<?> mainModule, Ioc ioc) throws Exception {
    UrlMapping mapping = this.createUrlMapping(config);
    if(log.isInfoEnabled()) {
        log.infof("Build URL mapping by %s ...", new Object[]{mapping.getClass().getName()});
    }

    ViewMaker[] makers = this.createViewMakers(mainModule, ioc);
    ActionChainMaker maker = this.createChainMaker(config, mainModule);
    ActionInfo mainInfo = Loadings.createInfo(mainModule);
    Determiner ann = (Determiner)mainModule.getAnnotation(Determiner.class);
    Object determiner = null == ann?new NutEntryDeterminer():(EntryDeterminer)Loadings.evalObj(config, ann.value(), ann.args());
    Set modules = Loadings.scanModules(ioc, mainModule, (EntryDeterminer)determiner);
    if(modules.isEmpty() && log.isWarnEnabled()) {
        log.warn("None module classes found!!!");
    }
    int atMethods = 0;
    if(log.isDebugEnabled()) {
        log.debugf("Use %s as EntryMethodDeterminer", new Object[]{determiner.getClass().getName()});
    }
    Iterator var12 = modules.iterator();
    while(true) {
        ActionInfo moduleInfo;
        do {
            if(!var12.hasNext()) {
                if(atMethods == 0) {
                    if(log.isWarnEnabled()) {
                        log.warn("None @At found in any modules class!!");
                    }
                } else {
                    log.infof("Found %d module methods", new Object[]{Integer.valueOf(atMethods)});
                }

                config.setUrlMapping(mapping);
                config.setActionChainMaker(maker);
                config.setViewMakers(makers);
                return mapping;
            }
            Class module = (Class)var12.next();
            moduleInfo = Loadings.createInfo(module).mergeWith(mainInfo);
            Method[] var15 = module.getMethods();
            int en = var15.length;
            for(int var17 = 0; var17 < en; ++var17) {
                Method method = var15[var17];
                if(((EntryDeterminer)determiner).isEntry(module, method)) {
                    ActionInfo info = Loadings.createInfo(method).mergeWith(moduleInfo);
                    info.setViewMakers(makers);
                    mapping.add(maker, info, config);
                    ++atMethods;
                }
            }
        } while(null == moduleInfo.getPathMap());
        Iterator var20 = moduleInfo.getPathMap().entrySet().iterator();
        while(var20.hasNext()) {
            Entry var21 = (Entry)var20.next();
            config.getAtMap().add((String)var21.getKey(), (String)var21.getValue());
        }
    }
}
```


# 适配器

> 将 HTTP 参数转换成一个函数参数的过程是一个典型适配过程，执行这个过程的对象被称为适配器。



每一个入口函数上，你都可以通过注解 @AdaptBy 来声明如何适配 HTTP 参数。当然，你 没必要在每一个入口函数上都声明，在子模块类上声明，或者在整个应用的主模块上声明均可。

1. 默认情况下：Nutz.Mvc 会采用 **org.nutz.mvc.adaptor.PairAdaptor** ，键值对形式

2. 表单方式 Form Bean：  `Param("..") Pet pet`，但是要注意".." 有特殊含义，表示当前的这个对象，**需要对应整个的 HTTP 参数表**。

3. 前缀表单方式：传递多个参数

   ```java
   @Param("::pet.")Pet pet, @Param("::food.")Foodfood, 
   ```


   ``` html
                   宠物名字<input type="text" name="pet.name"/>
                   宠物岁数<input type="text" name="pet.age"/>
                   宠物性别<input type="text" name="pet.sex"/>
                   食品名字<input type="text" name="food.name"/>
                   食品价格<input type="text" name="food.price"/>
                   食品数量<input type="text" name="food.count"/>
   ```


**注意**：遇到的一个坑，遇到了传递时间，但是无法解析，**本质上类型无法转化**，比如传入了String，但是对应的对象的属性是int，无法转化。 展示的解决方法，`@Param("birthday")`。

## 文件上传

WhaleAdaptor，必须要修改适配器为此类型。

如果在一个表单里混合上两个甚至多个表单项，那么 HTTP 的参数就会有点复杂，虽然这种情况下我更推荐采用 [Json 输入流](http://www.nutzam.com/core/mvc/http_adaptor.html#JSON_输入流_-_JsonAdaptor)，但是并不是所有人都那么喜欢它，对吗？



# View


```html
<input type="text" size="16" readonlyclass="form control" value="${@date.getTime()}" data-parsley-required="true">
```

`Gettime()`，其实是程序员自己编写的`DateUtil`里面的方法，`@Date`是这个对象的实例。如果想增加@？，可以在`GlobalsSettingProcessor`里面:


```java
ac.getRequest().setAttribute("shiro",Mvcs.ctx().getDefaultIoc().get(ShiroUtil.class));
ac.getRequest().setAttribute("date",Mvcs.ctx().getDefaultIoc().get(DateUtil.class));
ac.getRequest().setAttribute("string",Mvcs.ctx().getDefaultIoc().get(StringUtil.class));
```



待解决：

1. context概念理解