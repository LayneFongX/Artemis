package com.farid.annotation.action;

import com.farid.annotation.annotation.AtopPermissionAuthParams;
import com.farid.annotation.enums.VerifyMethodEnum;
import com.farid.base.domain.ApiRequestDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author banchao.feng@tuya.com
 * @since 2021/10/29
 */
@Slf4j
@Component
public class VerifyDeviceBelongHomeAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_DEVICE_BELONG_HOME;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        String homeId = (String) argsMap.get(verifyMethodParams.homeId());
        String deviceIds = (String) argsMap.get(verifyMethodParams.deviceIds());
        if (StringUtils.isBlank(deviceIds) || StringUtils.isBlank(homeId)) {
            log.warn("valid param is empty deviceId={} homeId={}", deviceIds, homeId);
            throw new RuntimeException("PARAM_ILLEGAL");
        }

        List<String> deviceIdList = Arrays.stream(deviceIds.split(REGEX)).collect(Collectors.toList());
        authBaseService.verifyDeviceBelongHome(homeId, deviceIdList);
    }
}
