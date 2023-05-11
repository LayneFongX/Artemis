package com.farid.artemis.producer.impl;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.farid.artemis.constant.EventHubsConstant;
import com.farid.artemis.producer.IEventHubsSendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventHubsSendMsgService implements IEventHubsSendMsgService {

    @Override
    public void sendEventHubsMsg(String message) {
        EventHubProducerClient producerClient = new EventHubClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING,
                        EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME).buildProducerClient();

        // 创建要发送的消息
        EventDataBatch eventDataBatch = producerClient.createBatch();
        eventDataBatch.tryAdd(new EventData(message));

        // 发送消息
        producerClient.send(eventDataBatch);

        // 关闭发送器
        producerClient.close();
    }
}
