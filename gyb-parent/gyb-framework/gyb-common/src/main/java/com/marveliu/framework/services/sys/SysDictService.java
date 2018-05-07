package com.marveliu.framework.services.sys;


import com.marveliu.framework.model.sys.Sys_dict;
import com.marveliu.framework.services.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by 王怀先 on 2017/1/19.
 */
public interface SysDictService extends BaseService<Sys_dict> {
    String getNameByCode(String code);
    String getNameById(String id);
    List<Sys_dict> getSubListByPath(String path);
    List<Sys_dict> getSubListById(String id);
    List<Sys_dict> getSubListByCode(String code);
    Map getSubMapByPath(String path);
    Map getSubMapById(String id);
    Map getSubMapByCode(String code);
    void save(Sys_dict dict, String pid);
    void deleteAndChild(Sys_dict dict);
}
