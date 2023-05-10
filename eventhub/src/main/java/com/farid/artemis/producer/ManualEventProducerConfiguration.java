package com.farid.artemis.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Slf4j
@Profile("manual")
@Configuration
public class ManualEventProducerConfiguration {

    @Bean
    public Sinks.Many<Message<String>> many1() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Sinks.Many<Message<String>> many2() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<String>>> supply1(Sinks.Many<Message<String>> many1) {
        return () -> many1.asFlux()
                          .doOnNext(m -> log.info("Manually sending message1 {}", m))
                          .doOnError(t -> log.error("Error encountered", t));
    }

    @Bean
    public Supplier<Flux<Message<String>>> supply2(Sinks.Many<Message<String>> many2) {
        return () -> many2.asFlux()
                          .doOnNext(m -> log.info("Manually sending message2 {}", m))
                          .doOnError(t -> log.error("Error encountered", t));
    }

}
