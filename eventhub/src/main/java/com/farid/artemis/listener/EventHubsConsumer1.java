package com.farid.artemis.listener;

import com.azure.messaging.eventhubs.*;
import com.azure.messaging.eventhubs.checkpointstore.blob.BlobCheckpointStore;
import com.azure.messaging.eventhubs.models.PartitionContext;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.farid.artemis.constant.EventHubsConstant;
import com.farid.artemis.utils.EventHubsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class EventHubsConsumer1 {

    @PostConstruct
    public void init() {
        startConsumer();
        log.info("EventHubsConsumer1 init success");
    }

    public void startConsumer() {
        BlobContainerAsyncClient blobContainerAsyncClient = new BlobContainerClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_STORAGE_CONNECTION_STRING)
                .containerName(EventHubsConstant.EVENT_HUBS_EEC_STORAGE_CONTAINER_NAME)
                .buildAsyncClient();

        EventProcessorClient eventProcessorClient = new EventProcessorClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING, EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .retryOptions(EventHubsUtil.getAmqpRetryOptions())
                .processEvent(eventContext -> {
                    PartitionContext partitionContext = eventContext.getPartitionContext();
                    EventData eventData = eventContext.getEventData();

                    log.info("EventHubsConsumer1 startConsumer processEvent from partition {} with sequence number {} with body {}",
                            partitionContext.getPartitionId(), eventData.getSequenceNumber(), eventData.getBodyAsString());
                    // 手动更新检查点
                    eventContext.updateCheckpointAsync().subscribe();
                })
                .processError(errorContext -> {
                    log.info("EventHubsConsumer1 startConsumer processError occurred in partition processor for partition {}, %s.%n",
                            errorContext.getPartitionContext().getPartitionId(), errorContext.getThrowable());
                })
                .checkpointStore(new BlobCheckpointStore(blobContainerAsyncClient))
                .loadBalancingStrategy(LoadBalancingStrategy.BALANCED)
                .buildEventProcessorClient();

        eventProcessorClient.start();
    }
}