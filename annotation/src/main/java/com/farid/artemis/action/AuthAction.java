package com.farid.artemis.action;


import com.farid.artemis.annotation.AtopPermissionAuthParams;
import com.farid.artemis.enums.VerifyMethodEnum;

import java.util.Map;


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
