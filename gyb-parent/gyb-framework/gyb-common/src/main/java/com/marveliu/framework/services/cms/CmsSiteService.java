package com.marveliu.framework.services.cms;

import com.marveliu.framework.services.base.BaseService;
import com.marveliu.framework.model.cms.Cms_site;

public interface CmsSiteService extends BaseService<Cms_site> {
    /**
     * 通过编码获取站点信息
     *
     * @param code
     * @return
     */
    Cms_site getSite(String code);

    /**
     * 清空缓存
     */
    void clearCache();
}
