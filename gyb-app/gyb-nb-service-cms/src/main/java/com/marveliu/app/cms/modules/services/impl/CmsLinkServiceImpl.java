package com.marveliu.app.cms.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.cms.Cms_link;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.cms.CmsLinkService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = CmsLinkService.class)
public class CmsLinkServiceImpl extends BaseServiceImpl<Cms_link> implements CmsLinkService {
    public CmsLinkServiceImpl(Dao dao) {
        super(dao);
    }
}
