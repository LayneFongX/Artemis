package com.farid.artemis.action;


import com.farid.artemis.annotation.AtopPermissionAuthParams;
import com.farid.artemis.domain.base.apicontext.ApiRequestDO;
import com.farid.artemis.service.IAtopPermissionAuthBaseService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;


public abstract class BaseAction implements AuthAction {

    public static final String REGEX = ",";

    @Resource
    public IAtopPermissionAuthBaseService authBaseService;

    @Override
    public void validAction(AtopPermissionAuthParams verifyMethodParams,
                            Map<String, Object> argsMap) {
        ApiRequestDO apiRequestDO = getRequestDO(argsMap);
        if (Objects.isNull(apiRequestDO)) {
            throw new RuntimeException();
        }

        String uid = apiRequestDO.getApiContextDo().getUid();
        validate(verifyMethodParams, argsMap, uid, apiRequestDO);
    }

    public abstract void validate(AtopPermissionAuthParams verifyMethodParams,
                                  Map<String, Object> argsMap, String uid, ApiRequestDO apiRequestDO);

    public ApiRequestDO getRequestDO(Map<String, Object> argsMap) {
        for (Map.Entry<String, Object> entry : argsMap.entrySet()) {
            Object arg = entry.getValue();
            if (arg instanceof ApiRequestDO) {
                return (ApiRequestDO) arg;
            }
        }
        return null;
    }

    public String getStringValue(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        return String.valueOf(obj);
    }
}
