package com.farid.artemis.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

@Slf4j
@Profile("!manual")
@Configuration
public class EventProducerConfiguration {

    private int i = 0;
    private int j = 0;

    @Bean
    public Supplier<Message<String>> supply1() {
        return () -> {
            log.info("Sending message1, sequence1 " + i);
            return MessageBuilder.withPayload("Hello world1, " + i++).build();
        };
    }

    @Bean
    public Supplier<Message<String>> supply2() {
        return () -> {
            log.info("Sending message2, sequence2 " + j);
            return MessageBuilder.withPayload("Hello world2, " + j++).build();
        };
    }
}

