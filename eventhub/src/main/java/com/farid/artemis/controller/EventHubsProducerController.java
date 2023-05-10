package com.farid.artemis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Sinks;

import javax.annotation.Resource;

@Slf4j
@Profile("manual")
@RestController
@RequestMapping("/eventHubs")
public class EventHubsProducerController {

    @Resource(name = "many1")
    private Sinks.Many<Message<String>> many1;

    @Resource(name = "many2")
    private Sinks.Many<Message<String>> many2;

    @PostMapping("/eecQASendMessage")
    public ResponseEntity<String> eecQASendMessage(@RequestParam String message) {
        log.info("Going to add message {} to eecQASendMessage", message);
        many1.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/eecFTSendMessage")
    public ResponseEntity<String> eecFTSendMessage(@RequestParam String message) {
        log.info("Going to add message {} to eecQASendMessage.", message);
        many2.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/eecFTSendMessage")
    public ResponseEntity<String> eecReleaseSendMessage(@RequestParam String message) {
        log.info("Going to add message {} to eecQASendMessage.", message);
        many2.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return ResponseEntity.ok(message);
    }
}
