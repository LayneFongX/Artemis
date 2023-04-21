package com.farid.artemis.controller;

import com.farid.artemis.service.IHttpService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createSite")
    public void createSite() {
        httpService.createSite();
    }

    @PutMapping("/updateSite")
    public void updateSite() {
        httpService.updateSite();
    }

    @PostMapping("/migrateData")
    public void migrateData() {
        httpService.migrateData();
    }
}
