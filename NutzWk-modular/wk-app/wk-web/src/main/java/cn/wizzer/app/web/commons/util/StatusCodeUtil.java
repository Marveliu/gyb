package cn.wizzer.app.web.commons.util;

/**
 * 自用状态码，封装在数据字典
 *
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */

public class StatusCodeUtil {

    public String GyAuthStatusMean(int status){
        switch(status){
            case 0: return "等待填写认证信息";
            case 1: return "认证审核";
            case 2: return "审核不通过";
            case 3: return "审核通过";
            default: return "状态码错误";
        }
    }


}
