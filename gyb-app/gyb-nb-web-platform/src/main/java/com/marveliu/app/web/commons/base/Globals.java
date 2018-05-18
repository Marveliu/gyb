package com.marveliu.app.web.commons.base;

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.framework.model.sys.Sys_config;
import com.marveliu.framework.model.sys.Sys_dict;
import com.marveliu.framework.model.sys.Sys_route;
import com.marveliu.framework.services.sys.SysConfigService;
import com.marveliu.framework.services.sys.SysDictService;
import com.marveliu.framework.services.sys.SysRouteService;
import com.marveliu.framework.util.ConfigUtil;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.weixin.impl.WxApi2Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wizzer on 2016/12/19.
 */
@IocBean(create = "init")
public class Globals {
    //项目路径
    public static String AppRoot = ConfigUtil.AppRoot;
    //项目目录
    public static String AppBase = ConfigUtil.AppBase;
    //项目名称
    public static String AppName = ConfigUtil.AppName;
    //项目短名称
    public static String AppShrotName = ConfigUtil.AppShrotName;
    //项目域名
    public static String AppDomain = ConfigUtil.AppDomain;
    //项目域名
    public static String AppApiDomain = ConfigUtil.AppApiDomain;
    //文件上传路径
    public static String AppUploadPath = ConfigUtil.AppUploadPath;
    //文件上传路径
    public static String AppUploadBase = ConfigUtil.AppUploadBase;
    // 是否启用了队列
    public static boolean RabbitMQEnabled = false;
    //系统自定义参数
    public static Map<String, String> MyConfig = new HashMap<>();
    //自定义路由
    public static Map<String, Sys_route> RouteMap = new HashMap<>();
    // 数据字典<code name>
    public static Map<String, String> MyDict = new HashMap<>();
    //微信map
    public static Map<String, WxApi2Impl> WxMap = new HashMap<>();

    @Inject
    @Reference
    private SysConfigService sysConfigService;
    @Inject
    @Reference
    private SysRouteService sysRouteService;
    @Inject
    @Reference
    private SysDictService sysDictService;

    public void init() {
        initSysConfig(sysConfigService);
        initSysDict(sysDictService);
        initRoute(sysRouteService);
    }

    public static void initSysConfig(SysConfigService sysConfigService) {
        Globals.MyConfig.clear();
        List<Sys_config> configList = sysConfigService.query();
        for (Sys_config sysConfig : configList) {
            switch (sysConfig.getConfigKey()) {
                case "AppName":
                    Globals.AppName = sysConfig.getConfigValue();
                    break;
                case "AppApiDomain":
                    Globals.AppApiDomain = sysConfig.getConfigValue();
                    break;
                case "AppShrotName":
                    Globals.AppShrotName = sysConfig.getConfigValue();
                    break;
                case "AppDomain":
                    Globals.AppDomain = sysConfig.getConfigValue();
                    break;
                case "AppUploadPath":
                    Globals.AppUploadPath = sysConfig.getConfigValue();
                    break;
                case "AppUploadBase":
                    Globals.AppUploadBase = sysConfig.getConfigValue();
                    break;
                default:
                    Globals.MyConfig.put(sysConfig.getConfigKey(), sysConfig.getConfigValue());
                    break;
            }
        }
    }


    public static void initSysDict(SysDictService sysDictService) {
        Globals.MyDict.clear();
        List<Sys_dict> sysDictList = sysDictService.query();
        for (Sys_dict dict : sysDictList) {
            Globals.MyDict.put(dict.getCode(), dict.getName());
        }
    }

    public static void initRoute(SysRouteService sysRouteService) {
        Globals.RouteMap.clear();
        List<Sys_route> routeList = sysRouteService.query(Cnd.where("disabled", "=", false));
        for (Sys_route route : routeList) {
            Globals.RouteMap.put(route.getUrl(), route);
        }
    }



    public static void initWx() {
        Globals.WxMap.clear();
    }
}
