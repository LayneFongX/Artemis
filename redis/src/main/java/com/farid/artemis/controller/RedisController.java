package com.farid.artemis.controller;


import com.farid.artemis.service.IRedisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private IRedisService redisService;

    @PostMapping("/insert")
    public ResponseEntity<Void> add(@RequestParam String key, @RequestParam String value) {
        redisService.add(key,value);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
