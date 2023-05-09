package com.farid.artemis.controller;

import com.farid.artemis.service.IEventHubsReceiveMsgService;
import com.farid.artemis.service.IEventHubsSendMsgService;
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
    public void sendEventHubsMsg(){
        eventHubsSendMsgService.sendEventHubsMsg();
    }
}
