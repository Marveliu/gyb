package com.marveliu.app.cms.modules.services.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.marveliu.framework.model.cms.Cms_article;
import com.marveliu.framework.page.Pagination;
import com.marveliu.framework.services.base.BaseServiceImpl;
import com.marveliu.framework.services.cms.CmsArticleService;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.plugins.wkcache.annotation.CacheDefaults;
import org.nutz.plugins.wkcache.annotation.CacheRemoveAll;
import org.nutz.plugins.wkcache.annotation.CacheResult;


@IocBean(args = {"refer:dao"})
@Service(interfaceClass = CmsArticleService.class)
@CacheDefaults(cacheName = "cms_article")
public class CmsArticleServiceImpl extends BaseServiceImpl<Cms_article> implements CmsArticleService {
    public CmsArticleServiceImpl(Dao dao) {
        super(dao);
    }

    @CacheResult
    @Override
    public Pagination getListPage(int pageNumber, int pageSize, Condition cnd) {
        return this.listPage(pageNumber, pageSize, cnd);
    }

    @CacheResult
    @Override
    public Cms_article getArticle(Condition cnd) {
        return this.fetch(cnd);
    }

    @CacheRemoveAll
    @Override
    public void clearCache() {

    }
}
