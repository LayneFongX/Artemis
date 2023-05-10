package com.farid.artemis.processor;

import com.azure.messaging.eventhubs.implementation.PartitionProcessor;
import com.azure.messaging.eventhubs.models.ErrorContext;
import com.azure.messaging.eventhubs.models.EventContext;
import org.springframework.stereotype.Component;

@Component
public class EventHubsPartitionProcessor extends PartitionProcessor {

    @Override
    public void processEvent(EventContext eventContext) {
        System.out.println("Received event: " + eventContext.getEventData().getBodyAsString());
    }

    @Override
    public void processError(ErrorContext errorContext) {
        System.out.println("Error occurred in partition: " + errorContext.getThrowable().getMessage());
    }
}