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
 * 任务反馈审批
 * @author Marveliu
 * @since 17/05/2018
 **/

public class XmfdbRelpyTMsg extends BaseTMsg implements TMsg {

    private final static String  TEMPLATE_PATH = "xmfdbReply.html";
    private final static String  SUBJECT = "任务反馈审批";

    private String username;
    private String xminfid;
    private String xminfname;
    private String authername;
    private String xmfeedbackid;
    private String statuname;
    private String nexttime;
    private String reply;



    // 必须要有无参构造
    public XmfdbRelpyTMsg() {
    }

    public XmfdbRelpyTMsg(String username, String xminfid, String xminfname, String authername, String xmfeedbackid, String statuname, String nexttime, String reply) {
        this.username = username;
        this.xminfid = xminfid;
        this.xminfname = xminfname;
        this.authername = authername;
        this.xmfeedbackid = xmfeedbackid;
        this.statuname = statuname;
        this.nexttime = nexttime;
        this.reply = reply;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getXminfid() {
        return xminfid;
    }

    public void setXminfid(String xminfid) {
        this.xminfid = xminfid;
    }

    public String getXminfname() {
        return xminfname;
    }

    public void setXminfname(String xminfname) {
        this.xminfname = xminfname;
    }

    public String getAuthername() {
        return authername;
    }

    public void setAuthername(String authername) {
        this.authername = authername;
    }

    public String getXmfeedbackid() {
        return xmfeedbackid;
    }

    public void setXmfeedbackid(String xmfeedbackid) {
        this.xmfeedbackid = xmfeedbackid;
    }

    public String getStatuname() {
        return statuname;
    }

    public void setStatuname(String statuname) {
        this.statuname = statuname;
    }

    public String getNexttime() {
        return nexttime;
    }

    public void setNexttime(String nexttime) {
        this.nexttime = nexttime;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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
