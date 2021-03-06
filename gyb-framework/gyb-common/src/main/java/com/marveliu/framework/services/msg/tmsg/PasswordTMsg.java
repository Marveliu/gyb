package com.marveliu.framework.services.msg.tmsg;
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

import com.marveliu.framework.services.msg.TMsg;


/**
 * @author Marveliu
 * @since 14/05/2018
 **/

public class PasswordTMsg extends BaseTMsg implements TMsg {

    private final static String  TEMPLATE_PATH = "password.html";
    private final static String  SUBJECT = "密码找回";

    private String username;

    private String newpassword;

    // 必须要有无参构造
    public PasswordTMsg() {
    }

    public PasswordTMsg(String username, String newpassword) {
        this.username = username;
        this.newpassword = newpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return newpassword;
    }

    public void setUrl(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getTemplatePath() {
        return TEMPLATE_PATH;
    }

    public String getSubject(){return SUBJECT;}
}
