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

public class XmfdbCommitTMsg extends BaseTMsg implements TMsg {

    private final static String  TEMPLATE_PATH = "xmfdbCommit.html";
    private final static String  SUBJECT = "任务反馈提交";

    private String username;
    private String xminfid;
    private String xminfname;
    private String gyrealname;
    private String gyid;
    private String xmfeedbackcode;
    private String note;



    // 必须要有无参构造
    public XmfdbCommitTMsg() {
    }

    public XmfdbCommitTMsg(String username, String xminfid, String xminfname, String gyrealname, String gyid, String xmfeedbackcode, String note) {
        this.username = username;
        this.xminfid = xminfid;
        this.xminfname = xminfname;
        this.gyrealname = gyrealname;
        this.gyid = gyid;
        this.xmfeedbackcode = xmfeedbackcode;
        this.note = note;
    }

    public String getGyid() {
        return gyid;
    }

    public void setGyid(String gyid) {
        this.gyid = gyid;
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

    public String getGyrealname() {
        return gyrealname;
    }

    public void setGyrealname(String gyrealname) {
        this.gyrealname = gyrealname;
    }

    public String getXmfeedbackcode() {
        return xmfeedbackcode;
    }

    public void setXmfeedbackcode(String xmfeedbackcode) {
        this.xmfeedbackcode = xmfeedbackcode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
