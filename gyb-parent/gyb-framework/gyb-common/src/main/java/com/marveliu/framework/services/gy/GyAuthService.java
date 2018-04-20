package com.marveliu.framework.services.gy;

import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.services.base.BaseService;

public interface GyAuthService extends BaseService<gy_auth> {

    // 通过雇员id获得雇员验证信息
    public gy_auth getGyAuthByGyid(String gyid);

    // 通过雇员id检查是否验证
    public boolean checkGyAuth(String gyid);

    // 通过雇员id检查是否验证成功
    public boolean ifAuth(String gyid);

    public boolean enable(String gyid,String note);

    public boolean disable(String gyid,String note);
}
