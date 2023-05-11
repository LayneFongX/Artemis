package com.farid.artemis.controller;

import com.farid.artemis.producer.IEventHubsSendMsgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eventhubs")
public class EventHubsProducerController {

    @Resource
    private IEventHubsSendMsgService eventHubsSendMsgService;

    @PostMapping("/sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestParam String message, @RequestParam String partitionId) {
        eventHubsSendMsgService.sendEventHubsMsg(message, partitionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
