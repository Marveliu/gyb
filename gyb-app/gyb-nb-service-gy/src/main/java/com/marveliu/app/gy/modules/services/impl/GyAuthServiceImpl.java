package com.marveliu.app.gy.modules.services.impl;
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

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.gy.GyAuthService;
import com.marveliu.framework.services.msg.TMsg;
import com.marveliu.framework.services.msg.tmsg.GyAuthTMsg;
import com.marveliu.framework.services.sys.SysMsgService;
import com.marveliu.framework.util.ConfigUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/
@IocBean(args = {"refer:dao"})
@Service(interfaceClass = GyAuthService.class)
public class GyAuthServiceImpl extends BaseServiceImpl<gy_auth> implements GyAuthService {

    private final static Log log = Logs.get();

    private static final String GY_AUTH_DECLINE_NOTE = "很遗憾您的雇员身份未能认证通过！";
    private static final String GY_AUTH_PASS_NOTE = "恭喜您雇员身份认证通过！";

    @Inject
    @Reference
    private SysMsgService sysMsgService;

    public GyAuthServiceImpl(Dao dao) {
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
        if (auth.getStatus() == ConfigUtil.GY_AUTH_PASS) {
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
        try {
            Cnd cnd = Cnd.where("gyid", "=", gyid);
            int status = ConfigUtil.GY_AUTH_DECLINE;
            if (flag) {
                status = ConfigUtil.GY_AUTH_PASS;
                Strings.sNull(note, GY_AUTH_PASS_NOTE);
            } else {
                Strings.sNull(note, GY_AUTH_DECLINE_NOTE);
            }
            int count = this.dao().update(gy_auth.class, Chain.make("status", status).add("note", note), cnd);
            if (count == 1) {
                String result = note;
                Lang.runInAnThread(new Runnable() {
                    @Override
                    public void run() {
                        gy_inf gyInf = dao().fetch(gy_inf.class, gyid);
                        TMsg tMsg = new GyAuthTMsg(
                                gyInf.getGyid(),
                                result
                        );
                        Sys_msg sysMsg = new Sys_msg();
                        sysMsg.setRevid(gyInf.getId());
                        sysMsg.setRevaccount(gyInf.getEmail());
                        sysMsg.setMsg(Json.toJson(tMsg));
                        sysMsg.setType(ConfigUtil.SYS_MSG_TYPE_EMAIL);
                        sysMsg.setTag(ConfigUtil.SYS_MSG_TAG_GY);
                        sysMsg.setTmsgclass(tMsg.getTMsgClass());
                        sysMsgService.pushMsg(sysMsg);
                    }
                });
                return true;
            }
        } catch (Exception e) {
            log.error("身份审核失败", e);
        }
        return false;
    }
}