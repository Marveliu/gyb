package com.marveliu.framework.services.sys;


import com.marveliu.framework.model.sys.Sys_unit;
import com.marveliu.framework.services.base.BaseService;

/**
 * Created by wizzer on 2016/12/22.
 */
public interface SysUnitService extends BaseService<Sys_unit> {
    void save(Sys_unit unit, String pid);

    void deleteAndChild(Sys_unit unit);
}
