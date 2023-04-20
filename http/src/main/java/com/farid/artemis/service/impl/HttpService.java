package com.farid.artemis.service.impl;

import com.farid.artemis.service.IHttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author Banchao
 * @Date 2023/4/20 16:49
 */

@Slf4j
@Service
public class HttpService implements IHttpService {
    @Override
    public String sayHello() {
        return "Hello,this is httpService.";
    }
}
