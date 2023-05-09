package com.farid.artemis.controller;

import com.farid.artemis.service.IEventHubsSendMsgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eventHubs")
public class EventHubsController {

    @Resource
    private IEventHubsSendMsgService eventHubsSendMsgService;

    @PostMapping("/sendEventHubsMsg")
    public ResponseEntity<Void> sendEventHubsMsg() {
        eventHubsSendMsgService.sendEventHubsMsg();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
