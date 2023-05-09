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

    @Resource
    private IEventHubsReceiveMsgService eventHubsReceiveMsgService;

    @PostMapping("/sendReceiveEventHubsMsg")
    public void sendReceiveEventHubsMsg() throws InterruptedException {
        eventHubsSendMsgService.sendEventHubsMsg();
        eventHubsSendMsgService.batchSendEventHubsMsg();
        eventHubsReceiveMsgService.receiveMessages();
    }
}
