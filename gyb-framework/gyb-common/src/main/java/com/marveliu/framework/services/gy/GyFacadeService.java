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


    /************ 雇员信息相关 ************/

    /**
     * 雇员注册
     * @param gyInf
     * @param gyAuth
     * @return
     */
    public boolean regInfo(gy_inf gyInf,gy_auth gyAuth);


    public boolean editInfo(gy_inf gyInf,gy_auth gyAuth);



    /**
     * 检查指定联系方式是否被验证
     * @param gyid
     * @return
     */
    public boolean isContactChecked(String gyid,String[] contactWay);

    /**
     * 修改联系方式,迁移联系方式
     * 在已认证的情况下，只允许修改联系方式信息，并且全部需要重新验证
     * @param gy_inf 提交的雇员信息
     * @return
     */
    public boolean updateGyinf(gy_inf gy_inf);


    /**
     * 修改雇员角色
     * @param gyId
     * @param rolecode
     * @return
     */
    public boolean updateGyRoleByGyid(String gyId, String rolecode);


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

    /**
     * 给雇员发送消息
     * @param gyid
     * @param msg
     * @return
     */
    public boolean sendMsgByGyid(String gyid,Sys_msg msg);
}
