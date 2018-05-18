package com.marveliu.app.cms.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.cms.Cms_link_class;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.cms.CmsLinkClassService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=CmsLinkClassService.class)
public class CmsLinkClassServiceImpl extends BaseServiceImpl<Cms_link_class> implements CmsLinkClassService {
    public CmsLinkClassServiceImpl(Dao dao) {
        super(dao);
    }
}
