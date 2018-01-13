package cn.wizzer.app.web.commons.util;

import cn.wizzer.framework.util.DateUtil;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Date;


/**
 * 自用编号生产器
 *
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */

@IocBean
public class NumberUtil {


    /**
     * 根据时间生成唯一雇员编号
     * @param num
     * @param schoolcode
     * @param stulevel
     * @param sex
     * @return
     */
    public String GyIdGeneraotr(int num,String schoolcode,int stulevel,String sex){
        StringBuilder str = new StringBuilder();

        str.append("gy");
        str.append(DateUtil.format(new Date(),"yyyy").substring(0,4));
        str.append(schoolcode);
        str.append(stulevel);
        str.append(sex);
        str.append(num);

        return str.toString();
    }



    /**
     * 雇员验证id
     * @param gyid
     * @return
     */
    public String GyAuthIdGeneraotr(String gyid){
        StringBuilder str = new StringBuilder();
        str.append("Au");
        str.append(gyid);
        return str.toString();
    }


    /**
     * 根据时间生成唯一员工编号
     * @param num
     * @param sex
     * @return
     */
    public String GyberIdGeneraotr(int num,String sex){
        StringBuilder str = new StringBuilder();
        str.append("gyb");
        str.append(DateUtil.format(new Date(),"yyyy").substring(0,4));
        str.append(sex);
        str.append(num);
        return str.toString();
    }


    /**
     * 根据时间生成唯一员工编号
     * @param num
     * @param sex
     * @return
     */
    public String GzIdGeneraotr(int num,String sex){
        StringBuilder str = new StringBuilder();
        str.append("gz");
        str.append(DateUtil.format(new Date(),"yyyy").substring(0,4));
        str.append(sex);
        str.append(num);
        return str.toString();
    }


    /**
     * 任务书编号生成
     * @param num
     * @param category
     * @return
     */
    public String XmtaskidGenerator(int num,String category){
        StringBuilder str = new StringBuilder();
        str.append("xmtask_");
        str.append(category);
        str.append(DateUtil.format(new Date(),"yyyyMMdd").substring(0,8));
        str.append(num);
        return str.toString();
    }


    /**
     * 申请单编号生成
     * @param num
     * @param task
     * @return
     */
    public String XmapplyidGenerator(int num,String task){
        StringBuilder str = new StringBuilder();
        str.append("apply");
        String[] arr = task.split("_");
        str.append(arr[1]);
        str.append(num);
        return str.toString();
    }


    /**
     * 立项编号生成
     * @param num
     * @param task
     * @return
     */
    public String XminfidGenerator(int num,String task){
        StringBuilder str = new StringBuilder();
        str.append("xminf_");
        String[] arr = task.split("_");
        str.append(arr[1]);
        str.append(num);
        return str.toString();
    }


    /**
     * 反馈编号生成
     * @param num
     * @param xminfid
     * @return
     */
    public String XfdIdGenerator(int num,String xminfid){
        StringBuilder str = new StringBuilder();
        str.append("xfd_");
        String[] arr = xminfid.split("_");
        str.append(arr[1]);
        str.append("_"+num);
        return str.toString();
    }

    /**
     * 账单生成编号
     * @param infid
     * @return
     */
    public String XmbillidGenerator(String infid){
        StringBuilder str = new StringBuilder();
        String[] arr = infid.split("_");
        str.append("bill_");
        str.append(arr[1]);
        return str.toString();
    }




}
