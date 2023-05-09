package com.farid.artemis.service.impl;

import com.farid.artemis.service.IEventHubsSendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author Banchao
 * @Date 2023/5/9 10:59
 */

@Slf4j
@Service
public class EventHubsSendMsgService implements IEventHubsSendMsgService {

    // private final static String EVENT_HUB_CONNECTION = "Endpoint=sb://farid-event-hubs.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=moHMVM6lLekAbdi9y/TJLIX/yvKxQrynx+AEhDFqCwQ=";
    //
    // private final static String EVENT_HUB_NAME = "myeventhub";

    @Override
    public void sendEventHubsMsg() {
        // EventHubProducerClient producer = new EventHubClientBuilder().connectionString(EVENT_HUB_CONNECTION, EVENT_HUB_NAME).buildProducerClient();
        //
        // List<EventData> allEvents = Arrays.asList(new EventData("Foo"), new EventData("Bar"));
        //
        // EventDataBatch eventDataBatch = producer.createBatch();
        // for (EventData eventData : allEvents) {
        //     if (!eventDataBatch.tryAdd(eventData)) {
        //         producer.send(eventDataBatch);
        //         eventDataBatch = producer.createBatch();
        //
        //         if (!eventDataBatch.tryAdd(eventData)) {
        //             throw new IllegalArgumentException("Event is too large for an empty batch. Max size: "
        //                     + eventDataBatch.getMaxSizeInBytes());
        //         }
        //     }
        // }
        // if (eventDataBatch.getCount() > 0) {
        //     producer.send(eventDataBatch);
        // }
        // producer.close();
    }
}
