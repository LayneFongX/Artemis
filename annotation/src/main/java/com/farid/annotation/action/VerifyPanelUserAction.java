package com.farid.annotation.action;


import com.farid.annotation.annotation.AtopPermissionAuthParams;
import com.farid.annotation.enums.VerifyMethodEnum;
import com.farid.base.domain.ApiRequestDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author banchao.feng@tuya.com
 * @since 2021/10/29
 */
@Slf4j
@Component
public class VerifyPanelUserAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_PANEL_USER;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap, String uid, ApiRequestDO apiRequestDO) {
        String userId = (String) argsMap.get(verifyMethodParams.userId());
        String deviceId = (String) argsMap.get(verifyMethodParams.deviceIds());
        if (StringUtils.isBlank(userId)) {
            log.warn("valid param is empty userId={} deviceId={} uid={}", userId, deviceId, uid);
            throw new RuntimeException("PARAM_ILLEGAL");
        }

        authBaseService.verifyPanelUser(userId, deviceId, uid);
    }
}
