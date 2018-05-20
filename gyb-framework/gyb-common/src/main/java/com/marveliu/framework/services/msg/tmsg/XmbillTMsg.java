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
 * 项目申请邮件
 * @author Marveliu
 * @since 17/05/2018
 **/

public class ApplyTMsg extends BaseTMsg implements TMsg {

    private final static String  TEMPLATE_PATH = "apply.html";
    private final static String  SUBJECT = "任务书申请";

    private String username;
    private String xmtaskname;
    private String xmtaskid;
    private String authorname;
    private String firstcommitat;

    // 必须要有无参构造
    public ApplyTMsg() {
    }

    public ApplyTMsg(String username, String xmtaskname, String xmtaskid, String authorname, String firstcommitat) {
        this.username = username;
        this.xmtaskname = xmtaskname;
        this.xmtaskid = xmtaskid;
        this.authorname = authorname;
        this.firstcommitat = firstcommitat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getXmtaskname() {
        return xmtaskname;
    }

    public void setXmtaskname(String xmtaskname) {
        this.xmtaskname = xmtaskname;
    }

    public String getXmtaskid() {
        return xmtaskid;
    }

    public void setXmtaskid(String xmtaskid) {
        this.xmtaskid = xmtaskid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getFirstcommitat() {
        return firstcommitat;
    }

    public void setFirstcommitat(String firstcommitat) {
        this.firstcommitat = firstcommitat;
    }

    @Override
    public String getTemplatePath() {
        return TEMPLATE_PATH;
    }

    @Override
    public String getSubject(){
        return SUBJECT;
    }


    @Override
    public String getTMsgClass(){
        return this.getClass().getName();
    }
}
