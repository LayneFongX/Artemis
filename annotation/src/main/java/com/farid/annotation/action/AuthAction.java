package com.farid.annotation.action;


import com.farid.annotation.annotation.AtopPermissionAuthParams;
import com.farid.annotation.enums.VerifyMethodEnum;

import java.util.Map;

/**
 * @author banchao.feng@tuya.com
 * @since 2021/10/28
 */
public interface AuthAction {

    /**
     * 获取检验类型
     */
    VerifyMethodEnum getAction();

    /**
     * 检验权限类型
     */
    void validAction(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap);
}
