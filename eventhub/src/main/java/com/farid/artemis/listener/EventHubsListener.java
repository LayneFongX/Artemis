package com.farid.artemis.listener;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.farid.artemis.constant.EventHubsConstant;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EventHubsListener {

    @PostConstruct
    public void init() {
        startListening();
    }

    public void startListening() {
        // 创建 Event Hubs 客户端
        EventHubClientBuilder clientBuilder = new EventHubClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING,
                        EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
                .consumerGroup(EventHubsConstant.EVENT_HUBS_EEC_CONSUMER_GROUP);

        // 启动监听器
        EventHubConsumerAsyncClient consumerClient = clientBuilder.buildAsyncConsumerClient();
        consumerClient.receive(true) // 持续监听消息
                .subscribe(event -> {
                    System.out.println("Received event: " + event.getData().getBodyAsString());
                });
    }
}