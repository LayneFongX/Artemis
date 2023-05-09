package com.farid.artemis.main;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Banchao
 * @Date 2023/5/9 15:01
 */
public class EventHubSender {

    private final static String EVENT_HUB_CONNECTION = "Endpoint=sb://farid-event-hubs.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=moHMVM6lLekAbdi9y/TJLIX/yvKxQrynx+AEhDFqCwQ=";

    private final static String EVENT_HUB_NAME = "myeventhub";

    public static void main(String[] args) {
        EventHubProducerClient producer = new EventHubClientBuilder().connectionString(EVENT_HUB_CONNECTION, EVENT_HUB_NAME).buildProducerClient();

        List<EventData> allEvents = Arrays.asList(new EventData("Foo"), new EventData("Bar"));

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
        }
        producer.close();
    }

}
