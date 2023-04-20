package com.farid.artemis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Banchao
 * @Date 2023/4/20 17:51
 */
@Configuration
public class RestTemplateConfiguration {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
