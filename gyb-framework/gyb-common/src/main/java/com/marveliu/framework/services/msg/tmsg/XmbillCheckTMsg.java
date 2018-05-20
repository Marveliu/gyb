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
 * 任务账单
 * @author Marveliu
 * @since 17/05/2018
 **/

public class XmbillTMsg extends BaseTMsg implements TMsg {

    private final static String  TEMPLATE_PATH = "xmbill.html";
    private final static String  SUBJECT = "任务账单";

    private String username;
    private String billid;
    private String xminfid;
    private String paysum;
    private String payname;
    private String typename;

    // 必须要有无参构造
    public XmbillTMsg() {
    }


    public XmbillTMsg(String username, String billid, String xminfid, String paysum, String payname, String typename) {
        this.username = username;
        this.billid = billid;
        this.xminfid = xminfid;
        this.paysum = paysum;
        this.payname = payname;
        this.typename = typename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public String getXminfid() {
        return xminfid;
    }

    public void setXminfid(String xminfid) {
        this.xminfid = xminfid;
    }

    public String getPaysum() {
        return paysum;
    }

    public void setPaysum(String paysum) {
        this.paysum = paysum;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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
