package com.farid.artemis.controller.impl;

import com.farid.artemis.controller.IHttpController;
import com.farid.artemis.service.IHttpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/http")
public class HttpController implements IHttpController {

    @Resource
    private IHttpService httpService;

    @Override
    @GetMapping("/sayHello")
    public String sayHello(String deviceId, String params1) {
        return httpService.sayHello();
    }

    @Override
    @GetMapping("/createSite")
    public void createSite() {
        httpService.createSite();
    }

    @Override
    @GetMapping("/updateSite")
    public void updateSite() {
        httpService.updateSite();
    }
}
