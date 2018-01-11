package cn.wizzer.app.web.commons.util;

import cn.wizzer.app.library.modules.models.lib_skill;
import cn.wizzer.app.library.modules.services.LibSkillService;
import cn.wizzer.app.sys.modules.services.SysDictService;
import cn.wizzer.app.sys.modules.services.impl.SysDictServiceImpl;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;

/**
 * 自用状态码，封装在数据字典
 *
 * @Author Marveliu
 * @Create 2018/1/6 0006.
 */

@IocBean
public class StatusCodeUtil
{


    @Inject
    private SysDictService sysDictService;

    @Inject
    private LibSkillService libSkillService;

    public static String bind(String code){
        return Mvcs.getIoc().get(SysDictServiceImpl.class).getNameByCode(code);
    }


    public static String test(){
        return  "fuckyou";
    }


    public String GyAuthStatusMean(int status){
        String code = "gyau" + status;
        return  sysDictService.getNameByCode(code);
    }

    public String SexStatusMean(int status){
        String code = "sex" + status;
        return  sysDictService.getNameByCode(code);
    }

    public String StulevelStatusMean(int status){
        String code = "stulevel" + status;
        return  sysDictService.getNameByCode(code);
    }

    public static String CollegeStatusMean(String status){
        String code = status+"";
        return Mvcs.getIoc().get(SysDictServiceImpl.class).getNameByCode(code);
    }

    public String TwoStatusMean(int status){
        String code = "twostatu"+ status;
        return  sysDictService.getNameByCode(code);
    }


    public String getSkillNameById(String id){
        lib_skill skill = libSkillService.fetch(id);
        return skill.getName();
    }


}
