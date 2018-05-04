package com.marveliu.app.xm.modules.services.impl;
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
import com.marveliu.framework.model.gy.gy_inf;
import com.marveliu.framework.model.xm.xm_apply;
import com.marveliu.framework.model.xm.xm_task;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.xm.XmApplyService;
import com.marveliu.framework.util.DateUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Marveliu
 * @since 02/05/2018
 **/


@IocBean(args = {"refer:dao"},create = "init", depose = "close")
@Service(interfaceClass = XmApplyService.class)
public class XmApplyServiceImpl extends BaseServiceImpl<xm_apply> implements XmApplyService,Runnable {

    private static final int XM_APPLY_INIT = 0;
    private static final int XM_APPLY_PASS = 1;
    private static final int XM_APPLY_FAIL = 2;


    private final static Log log = Logs.get();

    ExecutorService es;

    LinkedBlockingQueue<xm_apply> queue;

    public XmApplyServiceImpl(Dao dao) {
        super(dao);
    }


    public void init() {
        // 创建阻塞队列
        queue = new LinkedBlockingQueue<xm_apply>();
        // 创建线程池
        int c = Runtime.getRuntime().availableProcessors();
        es = Executors.newFixedThreadPool(c);
        for (int i = 0; i < c; i++) {
            es.submit(this);
        }
    }

    public void close() throws InterruptedException {
        queue = null; // 触发关闭
        if (es != null && !es.isShutdown()) {
            es.shutdown();
            es.awaitTermination(5, TimeUnit.SECONDS);
        }
    }


    @Override
    public void run() {
        while (true) {
            LinkedBlockingQueue<xm_apply> queue = this.queue;
            if (queue == null)
                break;
            try {
                // 从阻塞队列中拿task
                xm_apply xmApply = queue.poll(1, TimeUnit.SECONDS);
                if (xmApply != null) {
                    // 同步到数据库
                    sync(xmApply);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }


    /**
     * 异步处理任务申请
     * @param xmApply 任务对象
     */
    public void async(xm_apply xmApply) {
        LinkedBlockingQueue<xm_apply> queue = this.queue;
        if (queue != null)
            try {
                boolean re = queue.offer(xmApply, 50, TimeUnit.MILLISECONDS);
                if (!re) {
                    log.info(" xmApply queue is full, drop it ...");
                }
            } catch (InterruptedException e) {
            }
    }

    /**
     * 批量同步项目申请
     * @param xmApply
     */
    public void sync(xm_apply xmApply) {
        try {
            this.fastInsert(xmApply);
        } catch (Throwable e) {
            log.info("insert xmApply sync fail", e);
        }
    }


    /**
     * 生成xmAp
     * @param xmtaskid
     * @return
     */
    public String generateId(String xmtaskid){
        StringBuilder str = new StringBuilder("apply_");
        str.append(xmtaskid.split("_")[1]);
        // 使用阻塞队列，失效
        // str.append(this.count(Cnd.where("xmtaskid","=",xmtaskid))+1);
        str.append(DateUtil.getCurrentTime());
        return str.toString();
    }


    /**
     * 队列添加项目申请
     * 大概只有一万左右的并发程度
     *
     * @param xmtaskid
     * @param gyid
     * @return
     */
    @Override
    public Boolean addXmApply(String xmtaskid, String gyid,Boolean async) {
        xm_apply  xmApply = new xm_apply();
        xmApply.setGyid(gyid);
        xmApply.setXmtaskid(xmtaskid);
        xmApply.setId(R.UU32().toLowerCase());
        xmApply.setStatus(XM_APPLY_INIT);

        if(async){
            LinkedBlockingQueue<xm_apply> queue = this.queue;
            // 添加队列
            if (queue != null)
                try {
                    // 失效时间
                    boolean re = queue.offer(xmApply, 50, TimeUnit.MILLISECONDS);
                    if (!re) {
                        System.out.println("xmApply queue is full, drop it ...");
                    }
                    return re;
                } catch (InterruptedException e) {
                }
        }else{
            sync(xmApply);
        }
        return false;
    }



    /**
     * 受理项目申请
     * @param xmapplyid
     * @param flag true 通过 false 不通过
     * @param uid
     * @return
     */
    public Boolean setXmApplyStatus(String xmapplyid, Boolean flag,String uid) {
        Cnd cnd = Cnd.where("id","=",xmapplyid);
        long opAt = (int) (System.currentTimeMillis() / 1000);
        Chain chain = Chain.make("opAt",opAt).add("opBy",uid);
        if(flag){
            chain.add("status",XM_APPLY_PASS);
            // 之前所有的申请拒绝
            this.dao().execute(Sqls.create("update xm_apply set status = @status where id = @xmapplyid")
                    .setParam("status",XM_APPLY_FAIL)
                    .setParam("xmapplyid",xmapplyid)
            );
        }else{
            chain.add("status",XM_APPLY_FAIL);
        }
        if(this.dao().update(gy_inf.class,chain,cnd)!=0)
        {
            return true;
        }
        return false;
    }


    /**
     * 通过申请编号获得任务
     *
     * @param xmapplyid
     * @return
     */
    @Override
    public xm_task getXmTaskByAppyid(String xmapplyid) {
        return this.dao().fetch(xm_task.class,this.fetch(xmapplyid).getXmtaskid());
    }


    /**
     * 获得雇员申请的项目信息
     *
     * @param gyid
     * @return
     */
    @Override
    public List<xm_apply> getXmApplyListByGyid(String gyid) {
        return this.query(Cnd.where("gyid","=",gyid));
    }

    /**
     * 获得雇员申请的项目信息
     *
     * @param xmtaskid
     * @return
     */
    @Override
    public List<xm_apply> getXmApplyListByXmtaskid(String xmtaskid) {
        return this.query(Cnd.where("xmtaskid","=",xmtaskid));
    }
}