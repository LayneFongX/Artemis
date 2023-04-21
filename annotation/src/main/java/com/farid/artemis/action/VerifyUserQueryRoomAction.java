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

/**
 * @author falcon
 * @since 2021/10/29
 */
@Slf4j
@Component
public class VerifyUserQueryRoomAction extends BaseAction {

    @Override
    public VerifyMethodEnum getAction() {
        return VerifyMethodEnum.VERIFY_USER_QUERY_ROOM;
    }

    @Override
    public void validate(AtopPermissionAuthParams verifyMethodParams, Map<String, Object> argsMap,
                         String uid, ApiRequestDO apiRequestDO) {
        String roomIdString = getStringValue(argsMap.get(verifyMethodParams.roomIds()));
        if (StringUtils.isBlank(roomIdString)) {
            throw new RuntimeException();
        }

        List<String> roomIds = Arrays.stream(roomIdString.split(REGEX)).collect(Collectors.toList());
        List<Long> roomIdList = roomIds.stream().map(Long::valueOf).collect(Collectors.toList());
        authBaseService.verifyUserQueryRoom(uid, roomIdList);
    }
}
