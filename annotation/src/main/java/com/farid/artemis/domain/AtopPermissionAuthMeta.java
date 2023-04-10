package com.farid.artemis.domain;


import com.farid.artemis.annotation.AtopPermissionAuthParams;
import com.farid.artemis.enums.VerifyBusinessEnum;
import com.farid.artemis.enums.VerifyMethodEnum;
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
