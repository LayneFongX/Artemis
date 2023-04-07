package com.farid.annotation.interceptor;

import org.springframework.aop.Advisor;
import org.springframework.context.annotation.Bean;

/**
 * @author banchao.feng@tuya.com
 * @since 2021/10/21
 */

//@Configuration
public class AopConfiguration {

    @Bean
    public Advisor advisor() {
        MethodPointcutAdvisor methodPointcutAdvisor = new MethodPointcutAdvisor();
        methodPointcutAdvisor.setAdvice(new AopMethodInterceptor());
        return methodPointcutAdvisor;
    }
}
