package com.farid.artemis.controller;


import com.farid.artemis.service.I18nMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/i18n")
public class I18nController {

    @Resource
    private I18nMessageService messageService;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }


    @GetMapping("/getI18nValue")
    public ResponseEntity<String> getI18nValue(@RequestParam("key") String key) {
        return ResponseEntity.ok(messageService.getMessage(key));
    }

    @GetMapping("/getI18nSay")
    public ResponseEntity<String> getI18nSay(@RequestParam("key1") String key1, @RequestParam("key2") String key2) {
        String[] i18nKeys = {key2};
        return ResponseEntity.ok(messageService.getMessage(key1, i18nKeys));
    }
}
