package com.farid.artemis.producer.impl;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.farid.artemis.constant.EventHubsConstant;
import com.farid.artemis.producer.IEventHubsSendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class EventHubsSendMsgService implements IEventHubsSendMsgService {

    @Override
    public void sendEventHubsMsg(String message) {
        EventHubProducerClient producer = new EventHubClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING,
                        EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME).buildProducerClient();

        List<EventData> allEvents = Collections.singletonList(new EventData(message));
        EventDataBatch eventDataBatch = producer.createBatch();
        for (EventData eventData : allEvents) {
            if (!eventDataBatch.tryAdd(eventData)) {
                producer.send(eventDataBatch);
                eventDataBatch = producer.createBatch();

                if (!eventDataBatch.tryAdd(eventData)) {
                    throw new IllegalArgumentException("Event is too large for an empty batch. Max size: "
                            + eventDataBatch.getMaxSizeInBytes());
                }
            }
        }
        if (eventDataBatch.getCount() > 0) {
            producer.send(eventDataBatch);
            log.info("EventHubsSendMsgService sendEventHubsMsg producer send msg = {}", message);
        }
        producer.close();
    }
}
