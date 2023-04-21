package com.farid.artemis.controller;

import com.farid.artemis.service.IHttpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/http")
public class HttpController {

    @Resource
    private IHttpService httpService;

    @GetMapping("/sayHello")
    public String sayHello(String deviceId, String params1) {
        return httpService.sayHello();
    }

    @GetMapping("/createSite")
    public void createSite() {
        httpService.createSite();
    }

    @GetMapping("/updateSite")
    public void updateSite() {
        httpService.updateSite();
    }
}
