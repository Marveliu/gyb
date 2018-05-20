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
 * @since 20/05/2018
 **/


public class XmfinalTMsg extends BaseTMsg implements TMsg {

    private final static String  TEMPLATE_PATH = "xmfinal.html";
    private final static String  SUBJECT = "任务结算";

    private String username;
    private String xminfid;
    private String xminfanme;
    private String evagrade;
    private String evanote;
    private String paysum;
    private String paynote;

    public XmfinalTMsg() {
    }

    public XmfinalTMsg(String username, String xminfid, String xminfanme, String evagrade, String evanote, String paysum, String paynote) {
        this.username = username;
        this.xminfid = xminfid;
        this.xminfanme = xminfanme;
        this.evagrade = evagrade;
        this.evanote = evanote;
        this.paysum = paysum;
        this.paynote = paynote;
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

    public String getXminfanme() {
        return xminfanme;
    }

    public void setXminfanme(String xminfanme) {
        this.xminfanme = xminfanme;
    }

    public String getEvagrade() {
        return evagrade;
    }

    public void setEvagrade(String evagrade) {
        this.evagrade = evagrade;
    }

    public String getEvanote() {
        return evanote;
    }

    public void setEvanote(String evanote) {
        this.evanote = evanote;
    }

    public String getPaysum() {
        return paysum;
    }

    public void setPaysum(String paysum) {
        this.paysum = paysum;
    }

    public String getPaynote() {
        return paynote;
    }

    public void setPaynote(String paynote) {
        this.paynote = paynote;
    }



    public String getTemplatePath() {
        return TEMPLATE_PATH;
    }

    public String getSubject(){return SUBJECT;}
}
