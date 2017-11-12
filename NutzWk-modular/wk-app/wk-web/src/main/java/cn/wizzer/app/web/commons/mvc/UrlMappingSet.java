package cn.wizzer.app.web.commons.mvc;

import org.nutz.mvc.impl.ActionInvoker;
import org.nutz.mvc.impl.UrlMappingImpl;

import java.util.Map;

/**
 * Created by 89792 on 2017/11/12 0012.
 * 想写UrlMappingImpl，支持url路径查看
 * 但是urlmapping 是实体类，不是interface
 */
public class UrlMappingSet extends UrlMappingImpl {

   // private  Map<String, ActionInvoker> urlmap;

    public Map<String, ActionInvoker> getUrlmap() {
        return super.map;
    }
}

