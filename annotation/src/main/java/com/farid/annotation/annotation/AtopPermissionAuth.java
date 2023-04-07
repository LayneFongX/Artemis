package com.farid.annotation.annotation;

import com.farid.annotation.enums.VerifyBusinessEnum;
import com.farid.annotation.enums.VerifyMethodEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Repeatable(AtopPermissionAuthContainer.class)
public @interface AtopPermissionAuth {

    /**
     * 要调用的校验方法
     *
     * @return
     */
    VerifyMethodEnum method();

    /**
     * 需要校验的参数信息
     *
     * @return 返回授权参数列表
     */
    AtopPermissionAuthParams methodParams();

    /**
     * 要校验的业务
     *
     * @return
     */
    VerifyBusinessEnum business() default VerifyBusinessEnum.NONE;

}