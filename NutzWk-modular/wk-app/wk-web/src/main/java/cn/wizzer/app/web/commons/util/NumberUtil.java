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
    public String GyIdGeneraotr(int num,String schoolcode,String stulevel,String sex){
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
     * 员工id
     * @param num
     * @return
     */
    public String GybIdGeneraotr(int num){
        StringBuilder str = new StringBuilder();
        str.append("gyb");
        str.append(DateUtil.format(new Date(),"yyyyMMdd"));
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






}
