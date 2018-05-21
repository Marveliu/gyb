package com.marveliu.app.msg.modules.services.impl;
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
import com.marveliu.framework.model.sys.Sys_msg;
import com.marveliu.framework.services.msg.MsgService;
import com.marveliu.framework.services.msg.email.EmailService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author Marveliu
 * @since 10/05/2018
 **/

@IocBean
@Service(interfaceClass = MsgService.class)
public class MsgServiceImpl implements MsgService {

    @Inject
    private EmailService emailService;

    @Override
    public boolean send(Sys_msg sysMsg) {
        //todo: 默认发送邮件
        // emailService.sendHtmlTemplateByTemplateName(sysMsg.getRevid(),);
        return false;
    }
}
