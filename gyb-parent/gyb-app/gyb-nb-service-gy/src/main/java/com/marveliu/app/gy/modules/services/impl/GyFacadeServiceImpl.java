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
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.gy.gy_pay;
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.services.gy.*;
import com.marveliu.framework.services.sys.SysRoleService;
import com.marveliu.framework.services.sys.SysUserService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

@IocBean
@Service(interfaceClass=GyFacadeService.class)
public class GyFacadeServiceImpl implements GyFacadeService {

    private static final Log log = Logs.get();

    @Inject
    private GySkillService gySkillService;
    @Inject
    private GyAuthService gyAuthService;
    @Inject
    private GyInfService gyInfService;
    @Inject
    private GyPayService gyPayService;

    @Inject
    @Reference
    private SysUserService sysUserService;

    @Inject
    @Reference
    private SysRoleService sysRoleService;


    /**
     * 修改雇员角色
     *
     * @param gyid
     * @param rolecode
     * @return
     */
    @Override
    public boolean updateGyRoleByGyid(String gyid, String rolecode) {
        try {
            String roleid =  sysRoleService.getRoleFromCode(rolecode).getId();
            String userid = gyInfService.getUserByGyid(gyid);
            return sysRoleService.setUserRoleByRoleid(userid,roleid);
        }catch (Exception e){
            log.error("修改雇员角色出错:",e);
        }
        return false;
    }

    /**
     * 给雇员发送消息
     *
     * @param gyid
     * @param msg
     * @return
     */
    @Override
    public boolean sendMsgByGyid(String gyid, Sys_msg msg) {
        return false;
    }

    /**
     * 修改雇员联系方式
     *
     * @param gyid
     * @param email
     * @return
     */
    @Override
    public boolean changeEmail(String gyid, String email) {
        return false;
    }


    /************ 支付相关 ************/

     /**
     * 通过支付id获得雇员信息
     * @param payid
     * @return
     */
    @Override
    public gy_inf getGyByPayid(String payid) {
        if(!Lang.isEmpty(payid)) return gyInfService.getGyByUserId(gyPayService.getGyidByPayid(payid));
        return null;
    }


    /**
     * 根据雇员id获取支付信息
     * @param gyid
     * @return
     */
    public List<gy_pay> getPaysByGyid(String gyid){
        gy_inf gy = gyInfService.fetch(gyid);
        gy = gyInfService.fetchLinks(gy,"gy_pays");
        return gy.getGypays();
    }

    /**
     * 检查指定联系方式是否被验证
     *
     * @param gyid
     * @param contactWay
     * @return
     */
    @Override
    public boolean ifEmailChecked(String gyid, String[] contactWay) {
        return false;
    }

    /**
     * 修改联系方式,迁移联系方式
     * 在已认证的情况下，只允许修改联系方式信息，并且全部需要重新验证
     * @param gy_inf 提交的雇员信息
     * @return
     */
    @Override
    public boolean updateGyinf(gy_inf gy_inf) {


        return false;
    }


    /************ 用户相关 ************/
    public boolean ifEmailChecked(String gyid) {
        return false;
    }

    public boolean setQq(String gyid, String qq) {
        return false;
    }
}
