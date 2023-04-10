package com.farid.artemis.action;


import com.farid.artemis.annotation.AtopPermissionAuthParams;
import com.farid.artemis.enums.VerifyMethodEnum;
import com.farid.artemis.domain.apicontext.ApiRequestDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author banchao.feng@tuya.com
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
        uid = (String) argsMap.get(verifyMethodParams.userId());
        String homeId = (String) argsMap.get(verifyMethodParams.homeId());
        if (StringUtils.isBlank(homeId)) {
            log.warn("valid param is empty homeId={}", homeId);
            throw new RuntimeException("PARAM_ILLEGAL");
        }

        authBaseService.verifyHomeOwner(uid, Long.valueOf(homeId));
    }
}
