package com.marveliu.app.sys.commons.core;

import com.marveliu.app.sys.commons.base.Globals;
import com.marveliu.framework.model.sys.*;
import org.nutz.boot.NbApp;
import org.nutz.dao.Chain;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Mirror;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


@IocBean(create = "init", depose = "depose")
@Modules(packages = "com.marveliu")
public class DubboRpcSysMainLauncher {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    @Inject
    private Globals globals;//注入一下为了初始化

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.getAppContext().setMainPackage("com.marveliu");
        nb.run();
    }

    public void init() {
        //通过POJO类创建表结构
        try {
            Daos.createTablesInPackage(dao, "com.marveliu.framework.model.sys", false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            if (log.isDebugEnabled()) {
                //通过POJO类修改表结构
                Daos.migration(dao, "com.marveliu.framework.model.sys", true, false);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        // 若必要的数据表不存在，则初始化数据库
        if (0 == dao.count(Sys_user.class)) {
        }
    }

    public void depose() {
        // 非mysql数据库,或多webapp共享mysql驱动的话,以下语句删掉
        try {
            Mirror.me(Class.forName("com.mysql.jdbc.AbandonedConnectionCleanupThread")).invoke(null, "shutdown");
        } catch (Throwable e) {
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
    }


}
