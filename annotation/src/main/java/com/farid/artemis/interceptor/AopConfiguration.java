package com.farid.artemis.interceptor;

import org.springframework.aop.Advisor;
import org.springframework.context.annotation.Bean;


//@Configuration
public class AopConfiguration {

    @Bean
    public Advisor advisor() {
        MethodPointcutAdvisor methodPointcutAdvisor = new MethodPointcutAdvisor();
        methodPointcutAdvisor.setAdvice(new AopMethodInterceptor());
        return methodPointcutAdvisor;
    }
}
