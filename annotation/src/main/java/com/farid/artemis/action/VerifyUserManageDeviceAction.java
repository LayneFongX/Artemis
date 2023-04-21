package com.farid.artemis.action;

import com.alibaba.fastjson.JSONObject;
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
public class VerifyUserManageDeviceAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_USER_MANAGE_DEVICE;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        log.info("VerifyUserManageDeviceAction validate verifyMethodParams = {},argsMap = {}", JSONObject.toJSONString(verifyMethodParams),
                JSONObject.toJSONString(argsMap));
        String deviceIds = getStringValue(argsMap.get(verifyMethodParams.deviceIds()));
        if (StringUtils.isBlank(deviceIds)) {
            throw new RuntimeException();
        }

        List<String> deviceIdList = Arrays.stream(deviceIds.split(REGEX)).collect(Collectors.toList());
        authBaseService.verifyUserManageDevice(uid, deviceIdList);
    }
}
