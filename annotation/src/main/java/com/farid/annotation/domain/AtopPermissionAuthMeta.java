package com.farid.annotation.domain;


import com.farid.annotation.annotation.AtopPermissionAuthParams;
import com.farid.annotation.enums.VerifyBusinessEnum;
import com.farid.annotation.enums.VerifyMethodEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class AtopPermissionAuthMeta {

    /**
     * 要调用的校验方法信息
     */
    private VerifyMethodEnum verifyMethod;

    /**
     * 授权参数信息
     *
     * @return 返回授权参数列表
     */
    private AtopPermissionAuthParams verifyMethodParams;

    /**
     * 业务信息 分业务校验
     */
    private VerifyBusinessEnum verifyBusinessEnum;
}
