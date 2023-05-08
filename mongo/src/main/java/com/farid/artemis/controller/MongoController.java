package com.farid.artemis.controller;


import com.farid.artemis.service.IMongoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Resource
    private IMongoService mongoService;

    @PostMapping("/insert")
    public void insert() {
        mongoService.insert();
    }
}
