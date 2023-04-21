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
public class VerifyUserQueryGroupAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_USER_QUERY_GROUP;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        String groupIdString = getStringValue(argsMap.get(verifyMethodParams.groupIds()));
        if (StringUtils.isBlank(groupIdString)) {
            throw new RuntimeException();
        }

        List<String> groupIds = Arrays.stream(groupIdString.split(REGEX)).collect(Collectors.toList());
        List<Long> groupIdList = groupIds.stream().map(Long::valueOf).collect(Collectors.toList());
        authBaseService.verifyUserQueryGroup(uid, groupIdList);
    }
}
