package com.marveliu.framework.services.gy;
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

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.gy.gy_skill;
import com.marveliu.framework.model.library.lib_skill;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.model.sys.Sys_user;
import org.nutz.dao.Cnd;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;

import java.util.List;

/**
 * 外观类
 */
public interface GyFacadeService {

    /**
     * 修改雇员角色
     * @param gyId
     * @param rolecode
     * @return
     */
    public boolean updateGyRole(String gyId, String rolecode);

    /**
     * 给雇员发送消息
     * @param gyid
     * @param msg
     * @return
     */
    public boolean sendMsgByGyid(String gyid,Sys_msg msg);

    /**
     * 修改雇员联系方式
     * @param gyid
     * @param email
     * @return
     */
    public boolean changeEmail(String gyid, String email);


    /************ 支付相关 ************/




    /**
     * 通过支付id获得雇员信息
     * @param payid
     * @return
     */
    public gy_inf getGyByPayid(String payid);

    /**
     * 根据雇员id获取支付信息
     * @param gyid
     * @return
     */
    public List<gy_pay> getPaysByGyid(String gyid);


    /************ 技能相关 ************/





    /************ 用户相关 ************/

    //  获得用户id
    public boolean ifEmailChecked(String gyid);

    public boolean setQq(String gyid , String qq);
}
