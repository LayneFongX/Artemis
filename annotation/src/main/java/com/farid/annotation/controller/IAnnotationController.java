package com.farid.annotation.controller;


import com.farid.annotation.annotation.AtopPermissionAuth;
import com.farid.annotation.annotation.AtopPermissionAuthParams;
import com.farid.annotation.enums.VerifyMethodEnum;

public interface IAnnotationController {

    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_USER_MANAGE_ROOM, methodParams = @AtopPermissionAuthParams(roomIds = "param1"))
    @AtopPermissionAuth(method = VerifyMethodEnum.VERIFY_DEVICE_BELONG_HOME, methodParams = @AtopPermissionAuthParams(deviceIds = "deviceId"))
    String sayHello(String deviceId, String param1);

}
