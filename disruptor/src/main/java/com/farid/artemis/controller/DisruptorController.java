package com.farid.artemis.controller;

import com.farid.artemis.service.IDisruptorConsumerMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/disruptor")
public class DisruptorController {

    @Resource
    private IDisruptorConsumerMessageService disruptorConsumerMessageService;

    @GetMapping("/singleConsumeMessage")
    public ResponseEntity<String> singleConsumeMessage(@RequestParam("producerCount") int producerCount, @RequestParam("consumerCount") int consumerCount,
                                                       @RequestParam("msgCount") int msgCount) {
        long startTime = System.currentTimeMillis();
        disruptorConsumerMessageService.singleConsumeMessage(producerCount, consumerCount, msgCount);
        String msg = "当前生产者有" + producerCount + "个，当前消费者有:" + consumerCount + "个,生产并消费了:" + msgCount + "条消息,耗时为:" +
                (System.currentTimeMillis() - startTime) + "ms";
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/multiRepeatConsumeMessage")
    public ResponseEntity<String> multiRepeatConsumeMessage(@RequestParam("producerCount") int producerCount,
                                            @RequestParam("consumerCount") int consumerCount,
                                            @RequestParam("msgCount") int msgCount) {
        long startTime = System.currentTimeMillis();
        disruptorConsumerMessageService.multiRepeatConsumeMessage(producerCount, consumerCount, msgCount);
        String msg = "当前生产者有" + producerCount + "个，当前消费者有:" + consumerCount + "个,生产并消费了:" + msgCount + "条消息,耗时为:" +
                (System.currentTimeMillis() - startTime) + "ms";
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/multiCompeteConsumeMessage")
    public ResponseEntity<String> multiCompeteConsumeMessage(@RequestParam("producerCount") int producerCount,
                                             @RequestParam("consumerCount") int consumerCount,
                                             @RequestParam("msgCount") int msgCount) {
        long startTime = System.currentTimeMillis();
        disruptorConsumerMessageService.multiCompeteConsumeMessage(producerCount, consumerCount, msgCount);
        String msg = "当前生产者有" + producerCount + "个，当前消费者有:" + consumerCount + "个,生产并消费了:" + msgCount + "条消息,耗时为:" +
                (System.currentTimeMillis() - startTime) + "ms";
        return ResponseEntity.ok(msg);
    }
}
