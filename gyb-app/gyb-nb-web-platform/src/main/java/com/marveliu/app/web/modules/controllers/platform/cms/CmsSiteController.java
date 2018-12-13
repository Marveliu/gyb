package com.marveliu.app.web.modules.controllers.platform.cms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.app.web.commons.utils.StringUtil;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.cms.Cms_site;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.cms.CmsSiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;

import java.util.List;

/**
 * Created by wiz on 2016/6/28.
 */
@IocBean
@At("/platform/cms/site")
public class CmsSiteController {
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private CmsSiteService cmsSiteService;

    @At("")
    @Ok("beetl:/platform/cms/site/index.html")
    @RequiresPermissions("cms.site.settings")
    public Object index() {
        Cms_site site = cmsSiteService.fetch("site");
        if (site == null) {
            site = new Cms_site();
            site.setId("site");
            site.setSite_name("Demo");
            site.setSite_domain("https://wiz.cn");
            cmsSiteService.insert(site);
        }
        return site;
    }

    @At
    @Ok("json:full")
    @RequiresPermissions("cms.content.article")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        return cmsSiteService.data(length, start, draw, order, columns, cnd, null);
    }

    @At("/add")
    @Ok("beetl:/platform/cms/site/add.html")
    @RequiresPermissions("cms.site.settings")
    public void add() {

    }

    @At("/edit/?")
    @Ok("beetl:/platform/cms/site/edit.html")
    @RequiresPermissions("cms.site.settings")
    public Object edit(String id) {
        return cmsSiteService.fetch(id);
    }

    @At
    @Ok("json")
    @RequiresPermissions("cms.site.settings.save")
    @SLog(tag = "添加站点", msg = "${args[0].site_name}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(@Param("..") Cms_site site) {
        try {
            site.setOpAt(Times.getTS());
            site.setOpBy(StringUtil.getPlatformUid());
            cmsSiteService.insert(site);
            cmsSiteService.clearCache();
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At
    @Ok("json")
    @RequiresPermissions("cms.site.settings.save")
    @SLog(tag = "修改站点", msg = "${args[0].site_name}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(@Param("..") Cms_site site) {
        try {
            site.setOpAt(Times.getTS());
            site.setOpBy(StringUtil.getPlatformUid());
            cmsSiteService.updateIgnoreNull(site);
            cmsSiteService.clearCache();
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }
}
