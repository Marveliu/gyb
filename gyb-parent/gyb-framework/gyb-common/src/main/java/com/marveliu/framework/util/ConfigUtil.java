package com.marveliu.framework.util;
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
 * @since 05/05/2018
 **/

public class ConfigUtil {

    public final static int XM_APPLY_INIT = 0;
    public final static int XM_APPLY_PASS = 1;
    public final static int XM_APPLY_FAIL = 2;
    public final static int XM_APPLY_FINAL = 3;


    public static final int XM_BILL_INIT = 0;
    public static final int XM_BILL_CHECKING = 1;
    public static final int XM_BILL_PAYING = 2;
    public static final int XM_BILL_PAYED = 3;
    public static final int XM_BILL_ERROR = 4;

    public static final int XM_TASK_INIT = 0;
    public static final int XM_TASK_PUBLISH = 1;
    public static final int XM_TASK_APPLYING = 2;
    public static final int XM_TASK_DOING = 3;
    public static final int XM_TASK_FINISH = 4;

    public static final int XM_FEEDBACK_INIT = 0;
    public static final int XM_FEEDBACK_COMMIT = 1;
    public static final int XM_FEEDBACK_CHECKING = 2;
    public static final int XM_FEEDBACK_FINISH = 3;
    public static final int XM_FEEDBACK_FINAL = 4;


    public static final int XM_INF_DOING = 0;
    public static final int XM_INF_DONE = 1;
    public static final int XM_INF_CHECKING = 2;
    public static final int XM_INF_PAYING = 3;
    public static final int XM_INF_PAYED = 4;
    public static final int XM_INF_ERROR = 5;


    public static final int SYS_MSG_TYPE_ALL = 0;
    public static final int SYS_MSG_TYPE_EMAIL = 1;


    public static final String SYS_MSG_TAG_SYS = "sys";
    public static final String SYS_MSG_TAG_GY = "gy";
    public static final String SYS_MSG_TAG_XM = "xm";



    public static String AppRoot = "";
    //项目目录
    public static String AppBase = "";
    //项目名称
    public static String AppName = "雇佣帮";
    //项目短名称
    public static String AppShrotName = "GYB";
    //项目域名
    public static String AppDomain = "http://127.0.0.1";
    //项目域名
    public static String AppApiDomain = "http://127.0.0.1";
    //文件上传路径
    public static String AppUploadPath = "upload";
    //文件上传路径
    public static String AppUploadBase = "";





}
