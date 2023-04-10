package com.farid.artemis.controller;


import com.farid.artemis.annotation.AtopPermissionAuth;
import com.farid.artemis.annotation.AtopPermissionAuthParams;
import com.farid.artemis.enums.VerifyMethodEnum;

public interface IAnnotationController {

    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_USER_MANAGE_ROOM, methodParams = @AtopPermissionAuthParams(roomIds = "param1"))
    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_DEVICE_BELONG_HOME, methodParams = @AtopPermissionAuthParams(deviceIds = "deviceId"))
    String sayHello(String deviceId, String param1);

}
