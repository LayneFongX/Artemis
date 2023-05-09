package com.farid.artemis.controller;


import com.farid.artemis.service.IMongoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> insert() {
        mongoService.insert();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
