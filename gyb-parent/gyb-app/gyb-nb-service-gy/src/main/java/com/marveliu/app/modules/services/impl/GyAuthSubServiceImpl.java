package com.marveliu.app.modules.services.impl;
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

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.gy.GyAuthSubService;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/
@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GyAuthSubService.class)
public class GyAuthSubServiceImpl extends BaseServiceImpl<gy_auth> implements GyAuthSubService {

    private static final int GY_AUTH_DECLINE = 3;
    private static final int GY_AUTH_PASS = 2;
    private static final int GY_AUTH_CHECKING = 1;
    private static final int GY_AUTH_WAIT = 0;
    private static final String GY_AUTH_DECLINE_NOTE = "很遗憾您的雇员身份未能认证通过！";
    private static final String GY_AUTH_PASS_NOTE = "恭喜您雇员身份认证通过！";

    public GyAuthSubServiceImpl(Dao dao) {
        super(dao);
    }

    /**
     * 获得雇员身份信息
     *
     * @param gyid
     * @return
     */
    @Override
    public gy_auth getGyAuthByGyid(String gyid) {
        return this.fetch(Cnd.where("gyid", "=", gyid));
    }

    /**
     * 雇员是否认证通过
     *
     * @param gyid
     * @return
     */
    @Override
    public boolean isAuth(String gyid) {
        gy_auth auth = this.getGyAuthByGyid(gyid);
        if (Lang.isEmpty(auth)) return false;

        if (auth.getStatus() == GY_AUTH_PASS) {
            return true;
        }

        return false;
    }

    /**
     * 设置审批状态
     *
     * @param gyid
     * @param flag true or false
     * @param note 审批留言
     * @return
     */
    @Override
    public boolean setStatus(String gyid, Boolean flag, String note) {
        Cnd cnd = Cnd.where("gyid", "=", gyid);
        int status = 0;
        if(Lang.isEmpty(note)) note = GY_AUTH_DECLINE_NOTE;

        if (flag) {
            status = GY_AUTH_PASS;
            if(Lang.isEmpty(note)) note = GY_AUTH_PASS_NOTE;
        }
        int count =  this.dao().update(gy_auth.class, Chain.make("status", status).add("note",note),cnd);
        if (count == 1) return true;
        return false;
    }
}