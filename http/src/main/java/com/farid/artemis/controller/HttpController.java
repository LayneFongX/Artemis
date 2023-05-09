package com.farid.artemis.controller;

import com.farid.artemis.service.IHttpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/http")
public class HttpController {

    @Resource
    private IHttpService httpService;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello(String deviceId, String params1) {
        return ResponseEntity.ok(httpService.sayHello());
    }

    @PostMapping("/createSite")
    public ResponseEntity<Void> createSite() {
        httpService.createSite();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateSite")
    public ResponseEntity<Void> updateSite() {
        httpService.updateSite();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/migrateData")
    public ResponseEntity<Void> migrateData() {
        httpService.migrateData();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
