package com.farid.artemis.action;


import com.farid.artemis.annotation.AtopPermissionAuthParams;
import com.farid.artemis.domain.base.apicontext.ApiRequestDO;
import com.farid.artemis.enums.VerifyMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Component
public class VerifyDeviceBelongGatewayAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_DEVICE_BELONG_GATEWAY;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        String gatewayId = getStringValue(argsMap.get(verifyMethodParams.gatewayId()));
        String deviceIds = getStringValue(argsMap.get(verifyMethodParams.deviceIds()));
        if (StringUtils.isBlank(deviceIds) || StringUtils.isBlank(gatewayId)) {
            throw new RuntimeException();
        }

        List<String> deviceIdList = Arrays.stream(deviceIds.split(REGEX)).collect(Collectors.toList());
        authBaseService.verifyDeviceBelongGateway(gatewayId, deviceIdList);
    }
}
