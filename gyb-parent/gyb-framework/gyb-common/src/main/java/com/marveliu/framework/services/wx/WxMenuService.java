package com.marveliu.framework.services.wx;


import com.marveliu.framework.model.wx.Wx_menu;
import com.marveliu.framework.services.base.BaseService;

public interface WxMenuService extends BaseService<Wx_menu> {
    void save(Wx_menu menu, String pid);

    void deleteAndChild(Wx_menu menu);
}
