package com.farid.artemis.listener;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.messaging.eventhubs.models.ErrorContext;
import com.azure.messaging.eventhubs.models.EventContext;
import com.azure.messaging.eventhubs.models.PartitionContext;
import com.farid.artemis.constant.EventHubsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@Slf4j
@Component
public class EventHubsListener {

    private static final Consumer<EventContext> PARTITION_PROCESSOR = eventContext -> {
        PartitionContext partitionContext = eventContext.getPartitionContext();
        EventData eventData = eventContext.getEventData();

        log.info("Processing event from partition {} with sequence number {} with body {}",
                partitionContext.getPartitionId(), eventData.getSequenceNumber(), eventData.getBodyAsString());
        eventContext.updateCheckpoint();
    };

    private static final Consumer<ErrorContext> ERROR_HANDLER = errorContext -> {
        log.info("Error occurred in partition processor for partition {}, %s.%n",
                errorContext.getPartitionContext().getPartitionId(), errorContext.getThrowable());
    };

    @PostConstruct
    public void init() {
        startListening();
    }

    public void startListening() {
        // BlobContainerAsyncClient blobContainerAsyncClient = new BlobContainerClientBuilder()
        //         .connectionString(EventHubsConstant.EVENT_HUBS_EEC_STORAGE_CONNECTION_STRING)
        //         .containerName(EventHubsConstant.EVENT_HUBS_EEC_STORAGE_CONTAINER_NAME)
        //         .buildAsyncClient();
        //
        //
        // EventProcessorClientBuilder eventProcessorClientBuilder = new EventProcessorClientBuilder()
        //         .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING, EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
        //         .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
        //         .processEvent(PARTITION_PROCESSOR)
        //         .processError(ERROR_HANDLER)
        //         .checkpointStore(new BlobCheckpointStore(blobContainerAsyncClient));
        //
        // EventProcessorClient eventProcessorClient = eventProcessorClientBuilder.buildEventProcessorClient();
        // eventProcessorClient.start();

        EventHubConsumerAsyncClient consumerClient = new EventHubClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING,EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();

        consumerClient.receive(true) // 持续监听消息
                .subscribe(event -> {
                    log.info("EventHubsListener startListening... received event msg = {}", event.getData().getBodyAsString());
                });
    }
}