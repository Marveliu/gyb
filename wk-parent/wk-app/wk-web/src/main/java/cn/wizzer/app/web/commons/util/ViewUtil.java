package cn.wizzer.app.web.commons.util;

import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 *
 * Created by 89792 on 2017/11/23 0023.
 */


@IocBean
public class ViewUtil {

    /**
    * @function: 输出性别select
    * @param:
    * @return:
    * @note:
    <option value="0" <%if(obj.sex=="0" ){%>
    selected="true"
    <%}%>
    >男</option>
    <option value="1" <%if(obj.sex=="1" ){%>
    selected="true"
    <%}%> >女</option>
    */
    public static String bindSexSelect(int sex,int target){
        if(target == sex){
            return "selected=\"true\"";
        }
        return "";
    }

    public static String bindSex(int sex){
        if(sex == 0){
            return "男";
        }else{
            return "女";
        }
    }

    /**
    * @function: 状态部分
    * @param:
    * @return:
    * @note:
    */
    public static String bindStatus(int status){
        if(status == 0){
            return "禁用";
        }else{
            return "启用";
        }
    }

    /**
    * @function: feedback status
    * @param:
    * @return:
    * @note:
    */
    public static String bindFeedbackStatus(int status){
        switch(status){
            case 0: return "雇员编辑中";
            case 1: return "雇员已提交";
            case 2: return "正在审核";
            case 3: return "审核完成";
            case 4: return "最终反馈";
            default: return "error";
        }
    }
}
