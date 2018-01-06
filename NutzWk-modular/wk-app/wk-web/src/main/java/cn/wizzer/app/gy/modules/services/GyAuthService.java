package cn.wizzer.app.gy.modules.services;

import cn.wizzer.app.gy.modules.models.gy_auth;
import cn.wizzer.framework.base.service.BaseService;

public interface GyAuthService extends BaseService<gy_auth>{

    // 通过雇员id获得雇员验证信息
    public gy_auth getGyAuthByGyid(String gyid);

    // 通过雇员id检查是否验证
    public boolean checkGyAuth(String gyid);

    // 通过雇员id检查是否验证成功
    public boolean ifAuth(String gyid);
}
