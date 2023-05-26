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
public class EventHubsConsumer4 {



    @PostConstruct
    public void init() {
        startConsumer();
        log.info("EventHubsConsumer4 init success");
    }


    public void startConsumer() {

        // EventProcessorClient eventProcessorClient = new EventProcessorClientBuilder()
        //         .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING, EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
        //         .consumerGroup(EventHubsConstant.EVENT_HUBS_EEC_GROUP_NAME)
        //         .retryOptions(EventHubsUtil.getAmqpRetryOptions())
        //         .processEvent(eventContext -> {
        //             PartitionContext partitionContext = eventContext.getPartitionContext();
        //             EventData eventData = eventContext.getEventData();
        //
        //             log.info("EventHubsConsumer4 startConsumer processEvent from partition {} with sequence number {} with body {}",
        //                     partitionContext.getPartitionId(), eventData.getSequenceNumber(), eventData.getBodyAsString());
        //             // 手动更新检查点
        //             eventContext.updateCheckpointAsync().subscribe();
        //         })
        //         .processError(errorContext -> {
        //             log.info("EventHubsConsumer4 startConsumer processError occurred in partition processor for partition {}, %s.%n",
        //                     errorContext.getPartitionContext().getPartitionId(), errorContext.getThrowable());
        //         })
        //         .checkpointStore(new CheckpointStore() {
        //             @Override
        //             public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
        //                 return null;
        //             }
        //
        //             @Override
        //             public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
        //                 return null;
        //             }
        //
        //             @Override
        //             public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
        //                 return null;
        //             }
        //
        //             @Override
        //             public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
        //                 return null;
        //             }
        //         })
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
            log.info("EventHubsConsumer4 startListening... received event,partionId = {},  msg = {}",partitionContext.getPartitionId(),
                    event.getData().getBodyAsString());
        });
    }
}