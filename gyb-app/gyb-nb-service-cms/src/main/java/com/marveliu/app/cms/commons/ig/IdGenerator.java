package com.marveliu.app.cms.commons.ig;

import org.nutz.el.opt.RunMethod;

/**
 * Created by wiz on 2018/3/17.
 */
public interface IdGenerator extends RunMethod {
    String next(String tableName, String prefix) throws Exception;
}