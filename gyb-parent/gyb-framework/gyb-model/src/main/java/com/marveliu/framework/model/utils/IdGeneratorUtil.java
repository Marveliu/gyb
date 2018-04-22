package com.marveliu.framework.model.utils;
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

import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Date;

/**
 * @author Marveliu
 * @since 22/04/2018
 **/

@IocBean
public class IdGeneratorUtil {

    // /**
    //  * 根据时间生成唯一雇员编号
    //  *
    //  * @param schoolcode
    //  * @param stulevel
    //  * @param sex
    //  * @return
    //  */
    // public String GyIdGenerator(int count, String schoolcode, int stulevel, String sex) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("gy");
    //     str.append(DateUtil.format(new Date(), "yyyyMMdd").substring(2, 6));
    //     str.append(count + 1);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 雇员验证id
    //  *
    //  * @param gyid
    //  * @return
    //  */
    // public String GyAuthIdGenerator(String gyid) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("Au");
    //     str.append(gyid);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 根据时间生成唯一员工编号
    //  *
    //  * @param num
    //  * @param sex
    //  * @return
    //  */
    // public String GyberIdGenerator(int num, String sex) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("gyb");
    //     str.append(DateUtil.format(new Date(), "yyyy").substring(0, 4));
    //     str.append(sex);
    //     str.append(num);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 根据时间生成唯一员工编号
    //  *
    //  * @param num
    //  * @param sex
    //  * @return
    //  */
    // public String GzIdGenerator(int num, String sex) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("gz");
    //     str.append(DateUtil.format(new Date(), "yyyy").substring(0, 4));
    //     str.append(sex);
    //     str.append(num);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 任务书编号生成
    //  *
    //  * @param num
    //  * @param category
    //  * @return
    //  */
    // public String XmtaskidGenerator(int num, String category) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("rws_");
    //     str.append(category);
    //     str.append(DateUtil.format(new Date(), "yyyyMMdd").substring(0, 8));
    //     str.append(num);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 任务申请单编号生成
    //  *
    //  * @param num
    //  * @param task
    //  * @return
    //  */
    // public String XmapplyidGenerator(int num, String task) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("apply");
    //     String[] arr = task.split("_");
    //     str.append(arr[1]);
    //     str.append(num);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 任务编号生成
    //  *
    //  * @param num
    //  * @param task
    //  * @return
    //  */
    // public String XminfidGenerator(int num, String task) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("rw_");
    //     String[] arr = task.split("_");
    //     str.append(arr[1]);
    //     str.append(num);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 任务反馈编号生成
    //  *
    //  * @param num
    //  * @param xminfid
    //  * @return
    //  */
    // public String XfdIdGenerator(int num, String xminfid) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("fk_");
    //     String[] arr = xminfid.split("_");
    //     str.append(arr[1]);
    //     str.append("_" + num);
    //     return str.toString();
    // }
    //
    // /**
    //  * 账单生成编号
    //  *
    //  * @param infid
    //  * @return
    //  */
    // public String XmbillidGenerator(String infid) {
    //     StringBuilder str = new StringBuilder();
    //     String[] arr = infid.split("_");
    //     str.append("bill_");
    //     str.append(arr[1]);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 任务评价单生成编号
    //  *
    //  * @param infid
    //  * @return
    //  */
    // public String XmEvaidGenerator(String infid) {
    //     StringBuilder str = new StringBuilder();
    //     String[] arr = infid.split("_");
    //     str.append("eva_");
    //     str.append(arr[1]);
    //     return str.toString();
    // }
    //
    //
    // /**
    //  * 雇员支付方式编码
    //  *
    //  * @param gyid
    //  * @param num
    //  * @return
    //  */
    // public String GyPayidGenerator(String gyid, int num) {
    //     StringBuilder str = new StringBuilder();
    //     str.append("pay" + num + "_");
    //     str.append(gyid);
    //     return str.toString();
    // }
}
