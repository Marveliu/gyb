package com.marveliu.framework.services.msg;
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

import com.marveliu.framework.model.sys.Sys_msg;

/**
 * @author Marveliu
 * @since 10/05/2018
 **/

// 消息接口实现
public interface MsgService  {

    // 聚合发送消息
    public boolean send(Sys_msg sysMsg);

}
