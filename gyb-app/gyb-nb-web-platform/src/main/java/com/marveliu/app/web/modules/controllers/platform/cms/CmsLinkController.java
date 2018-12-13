package com.marveliu.app.web.modules.controllers.platform.cms;


import com.alibaba.dubbo.config.annotation.Reference;
import com.marveliu.app.web.commons.slog.annotation.SLog;
import com.marveliu.framework.model.base.Result;
import com.marveliu.framework.model.cms.Cms_link;
import com.marveliu.framework.model.cms.Cms_link_class;
import com.marveliu.framework.page.datatable.DataTableColumn;
import com.marveliu.framework.page.datatable.DataTableOrder;
import com.marveliu.framework.services.cms.CmsLinkClassService;
import com.marveliu.framework.services.cms.CmsLinkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wiz on 2016/6/28.
 */
@IocBean
@At("/platform/cms/link/link")
public class CmsLinkController {
    private static final Log log = Logs.get();
    @Inject
    @Reference
    private CmsLinkClassService cmsLinkClassService;
    @Inject
    @Reference
    private CmsLinkService cmsLinkService;

    @At({"", "/index/","/index/?"})
    @Ok("beetl:/platform/cms/link/link/index.html")
    @RequiresPermissions("cms.link.link")
    public void index(String classId, HttpServletRequest req) {
        List<Cms_link_class> list = cmsLinkClassService.query(Cnd.NEW());
        if (list.size() > 0 && Strings.isBlank(classId)) {
            classId = list.get(0).getId();
        }
        req.setAttribute("list", list);
        req.setAttribute("classId", Strings.sBlank(classId));
    }

    @At({"/add/","/add/?"})
    @Ok("beetl:/platform/cms/link/link/add.html")
    @RequiresPermissions("cms.link.link")
    public void add(String classId, HttpServletRequest req) {
        req.setAttribute("classId", Strings.sBlank(classId));
        req.setAttribute("list", cmsLinkClassService.query(Cnd.NEW()));
    }

    @At
    @Ok("json")
    @RequiresPermissions("cms.link.link.add")
    @SLog(tag = "添加链接", msg = "分类名称:${args[0].name}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(@Param("..") Cms_link link, HttpServletRequest req) {
        try {
            cmsLinkService.insert(link);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit/?")
    @Ok("beetl:/platform/cms/link/link/edit.html")
    @RequiresPermissions("cms.link.link")
    public Object edit(String id, HttpServletRequest req) {
        Cms_link link = cmsLinkService.fetch(id);
        List<Cms_link_class> list = cmsLinkClassService.query(Cnd.NEW());
        req.setAttribute("list", list);
        req.setAttribute("classId", Strings.sBlank(link.getClassId()));
        return link;
    }

    @At
    @Ok("json")
    @RequiresPermissions("cms.link.link.edit")
    @SLog(tag = "修改链接", msg = "分类名称:${args[0].name}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(@Param("..") Cms_link link, HttpServletRequest req) {
        try {
            cmsLinkService.updateIgnoreNull(link);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At({"/delete/?", "/delete"})
    @Ok("json")
    @RequiresPermissions("cms.link.link.delete")
    @SLog(tag = "删除链接", msg = "ID:${args[2].getAttribute('id')}")
    public Object delete(String oneId, @Param("ids") String[] ids, HttpServletRequest req) {
        try {
            if (ids != null && ids.length > 0) {
                cmsLinkService.delete(ids);
                req.setAttribute("id", StringUtils.toString(ids));
            } else {
                cmsLinkService.delete(oneId);
                req.setAttribute("id", oneId);
            }
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At({"/data/", "/data/?"})
    @Ok("json:full")
    @RequiresPermissions("cms.link.link")
    public Object data(String classId, @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.NEW();
        if (!Strings.isBlank(classId)) {
            cnd.and("classId", "=", classId);
        }
        return cmsLinkService.data(length, start, draw, order, columns, cnd, null);
    }


}
