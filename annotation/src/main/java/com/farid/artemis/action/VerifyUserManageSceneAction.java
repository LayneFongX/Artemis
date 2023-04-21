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
public class VerifyUserManageSceneAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_USER_MANAGE_SCENE;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        String sceneIds = getStringValue(argsMap.get(verifyMethodParams.sceneIds()));
        if (StringUtils.isBlank(sceneIds)) {
            throw new RuntimeException();
        }

        List<String> sceneIdList = Arrays.stream(sceneIds.split(REGEX)).collect(Collectors.toList());
        authBaseService.verifyUserManageScene(uid, sceneIdList);
    }
}
