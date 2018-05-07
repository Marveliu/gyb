package com.marveliu.app.msg.commons.core;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.marveliu.app.msg.modules.services.impl.RabbitTestService;
import com.marveliu.framework.model.sys.Sys_user;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * @author Marveliu
 * @since 23/04/2018
 **/

@IocBean(create = "init", depose = "depose")
@Modules(packages = "com.marveliu")
public class DubboRpcMsgMainLauncher {

    private static final Log log = Logs.get();


    @Inject("refer:$ioc")
    protected Ioc ioc;

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.getAppContext().setMainPackage("com.marveliu");
        nb.run();
    }

    public void init() throws Exception {
        // 进行队列声明
    }

    public void depose() {

    }
}
