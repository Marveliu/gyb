package cn.wizzer.app.web.commons.util;

import org.nutz.ioc.loader.annotation.IocBean;

/**
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
}
