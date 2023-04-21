package com.farid.artemis.action;


import com.farid.artemis.annotation.AtopPermissionAuthParams;
import com.farid.artemis.domain.base.apicontext.ApiRequestDO;
import com.farid.artemis.enums.VerifyMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author falcon
 * @since 2021/10/28
 */
@Slf4j
@Component
public class VerifyHomeOwnerAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_HOME_OWNER;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        String homeId = getStringValue(argsMap.get(verifyMethodParams.homeId()));
        if (StringUtils.isBlank(homeId)) {
            throw new RuntimeException();
        }

        authBaseService.verifyHomeOwner(uid, Long.valueOf(homeId));
    }
}
