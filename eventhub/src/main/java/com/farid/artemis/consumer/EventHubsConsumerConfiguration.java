package com.farid.artemis.consumer;

import com.azure.spring.messaging.checkpoint.Checkpointer;
import com.azure.spring.messaging.eventhubs.support.EventHubsHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

import static com.azure.spring.messaging.AzureHeaders.CHECKPOINTER;

@Slf4j
@Configuration
public class EventHubsConsumerConfiguration {

    @Bean
    public Consumer<Message<String>> consume1() {
        return message -> {
            Checkpointer checkpointer = (Checkpointer) message.getHeaders().get(CHECKPOINTER);
            log.info("New message1 received: '{}'", message);
            checkpointer.success()
                    .doOnSuccess(success -> log.info("Message1 '{}' successfully checkpointed", message))
                    .doOnError(error -> log.error("Exception found", error))
                    .block();
        };
    }

    @Bean
    public Consumer<Message<String>> consume2() {
        return message -> {
            Checkpointer checkpointer = (Checkpointer) message.getHeaders().get(CHECKPOINTER);
            log.info("New message2 received: '{}'", message);
            checkpointer.success()
                    .doOnSuccess(success -> log.info("Message2 '{}' successfully checkpointed", message))
                    .doOnError(error -> log.error("Exception found", error))
                    .block();
        };
    }
}
