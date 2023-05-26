package com.farid.artemis.listener;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.messaging.eventhubs.models.PartitionContext;
import com.farid.artemis.constant.EventHubsConstant;
import com.farid.artemis.proxy.EventHubsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class EventHubsConsumer3 {

    @PostConstruct
    public void init() {
        startConsumer();
        log.info("EventHubsConsumer3 init success");
    }

    public void startConsumer() {
        // BlobContainerAsyncClient blobContainerAsyncClient = new BlobContainerClientBuilder()
        //         .connectionString(EventHubsConstant.EVENT_HUBS_EEC_STORAGE_CONNECTION_STRING)
        //         .containerName(EventHubsConstant.EVENT_HUBS_EEC_STORAGE_CONTAINER_NAME)
        //         .buildAsyncClient();
        //
        // EventProcessorClient eventProcessorClient = new EventProcessorClientBuilder()
        //         .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING, EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
        //         .consumerGroup(EventHubsConstant.EVENT_HUBS_EEC_GROUP_NAME)
        //         .retryOptions(EventHubsUtil.getAmqpRetryOptions())
        //         .processEvent(eventContext -> {
        //             PartitionContext partitionContext = eventContext.getPartitionContext();
        //             EventData eventData = eventContext.getEventData();
        //
        //             log.info("EventHubsConsumer3 startConsumer processEvent from partition {} with sequence number {} with body {}",
        //                     partitionContext.getPartitionId(), eventData.getSequenceNumber(), eventData.getBodyAsString());
        //             // 手动更新检查点
        //             eventContext.updateCheckpointAsync().subscribe();
        //         })
        //         .processError(errorContext -> {
        //             log.info("EventHubsConsumer3 startConsumer processError occurred in partition processor for partition {}, %s.%n",
        //                     errorContext.getPartitionContext().getPartitionId(), errorContext.getThrowable());
        //         })
        //         .checkpointStore(new BlobCheckpointStore(blobContainerAsyncClient))
        //         .loadBalancingStrategy(LoadBalancingStrategy.BALANCED)
        //         .buildEventProcessorClient();
        //
        // eventProcessorClient.start();

        EventHubConsumerAsyncClient consumerClient = new EventHubClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING, EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
                .consumerGroup(EventHubsConstant.EVENT_HUBS_EEC_GROUP_NAME)
                .retryOptions(EventHubsUtil.getAmqpRetryOptions())
                .buildAsyncConsumerClient();

        consumerClient.receive().subscribe(event -> {
            PartitionContext partitionContext = event.getPartitionContext();
            log.info("EventHubsConsumer3 startListening... received event,partionId = {},  msg = {}",partitionContext.getPartitionId(),
                    event.getData().getBodyAsString());
        });
    }
}