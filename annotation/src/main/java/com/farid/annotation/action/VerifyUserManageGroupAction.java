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
public class VerifyUserManageGroupAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_USER_MANAGE_GROUP;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        String groupIdString = (String) argsMap.get(verifyMethodParams.groupIds());
        if (StringUtils.isBlank(groupIdString)) {
            log.warn("valid param is empty groupId={}", groupIdString);
            throw new RuntimeException("PARAM_ILLEGAL");
        }

        List<String> groupIds = Arrays.stream(groupIdString.split(REGEX)).collect(Collectors.toList());
        List<Long> groupIdList = groupIds.stream().map(Long::valueOf).collect(Collectors.toList());
        authBaseService.verifyUserManageGroup(uid, groupIdList);
    }
}