package com.marveliu.framework.services.gy;

import com.marveliu.framework.model.gy.gy_auth;
import com.marveliu.framework.services.base.BaseService;

public interface GyAuthSubService extends BaseService<gy_auth> {

    /**
     * 获得雇员身份信息
     * @param gyid
     * @return
     */
    public gy_auth getGyAuthByGyid(String gyid);


    /**
     * 雇员是否认证通过
     * @param gyid
     * @return
     */
    public boolean isAuth(String gyid);

    /**
     * 设置审批状态
     * @param gyid
     * @param flag  true or false
     * @param note  审批留言
     * @return
     */
    public boolean setStatus(String gyid,Boolean flag,String note);
}
