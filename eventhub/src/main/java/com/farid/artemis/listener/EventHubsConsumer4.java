package com.farid.artemis.listener;

import com.azure.messaging.eventhubs.*;
import com.azure.messaging.eventhubs.models.Checkpoint;
import com.azure.messaging.eventhubs.models.PartitionContext;
import com.azure.messaging.eventhubs.models.PartitionOwnership;
import com.farid.artemis.constant.EventHubsConstant;
import com.farid.artemis.proxy.CacheProxy;
import com.farid.artemis.utils.EventHubsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component
public class EventHubsConsumer4 {

    @Resource
    private CacheProxy cacheProxy;

    @PostConstruct
    public void init() {
        startConsumer();
        log.info("EventHubsConsumer4 init success");
    }


    public void startConsumer() {
        EventProcessorClient eventProcessorClient = new EventProcessorClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING, EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
                .consumerGroup(EventHubsConstant.EVENT_HUBS_EEC_GROUP_NAME)
                .retryOptions(EventHubsUtil.getAmqpRetryOptions())
                .processEvent(eventContext -> {
                    PartitionContext partitionContext = eventContext.getPartitionContext();
                    EventData eventData = eventContext.getEventData();
                    eventContext.updateCheckpoint();

                    log.info("EventHubsConsumer4 startConsumer processEvent from partition {} with sequence number {} with body {}",
                            partitionContext.getPartitionId(), eventData.getSequenceNumber(), eventData.getBodyAsString());
                })
                .processError(errorContext -> {
                    log.info("EventHubsConsumer4 startConsumer processError occurred in partition processor for partition {}, %s.%n",
                            errorContext.getPartitionContext().getPartitionId(), errorContext.getThrowable());
                })
                .loadBalancingStrategy(LoadBalancingStrategy.BALANCED)
                .checkpointStore(new CheckpointStore() {
                    @Override
                    public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
                        return null;
                    }

                    @Override
                    public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
                        return null;
                    }

                    @Override
                    public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
                        List<Checkpoint> partitionCheckpoints = new ArrayList<>();

                        String checkPointSpaceCacheKey = EventHubsUtil.getCheckPointSpaceCacheKey(
                                fullyQualifiedNamespace, eventHubName, consumerGroup);
                        Set<String> checkPointPartitionCacheKeySet = cacheProxy.get(checkPointSpaceCacheKey);

                        if (CollectionUtils.isEmpty(checkPointPartitionCacheKeySet)){
                            return Flux.fromIterable(partitionCheckpoints);
                        }

                        for (String checkPointPartitionCacheKey : checkPointPartitionCacheKeySet) {
                            Checkpoint checkpoint = cacheProxy.get(checkPointPartitionCacheKey);
                            if (Objects.isNull(checkpoint)){
                                continue;
                            }
                            partitionCheckpoints.add(checkpoint);
                        }
                        return Flux.fromIterable(partitionCheckpoints);
                    }

                    @Override
                    public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
                        if (!EventHubsUtil.isCheckpointValid(checkpoint)) {
                            throw new IllegalStateException(
                                    "Checkpoint is either null, or both the offset and the sequence number are null.");
                        }
                        return Mono.fromRunnable(() -> {
                            String checkPointSpaceCacheKey = EventHubsUtil.getCheckPointSpaceCacheKey(
                                    checkpoint.getFullyQualifiedNamespace(),
                                    checkpoint.getEventHubName(), checkpoint.getConsumerGroup());

                            String checkPointPartitionCacheKey = EventHubsUtil.getCheckPointPartitionCacheKey(
                                    checkpoint.getFullyQualifiedNamespace(),
                                    checkpoint.getEventHubName(), checkpoint.getConsumerGroup(),
                                    checkpoint.getPartitionId());

                            if (!cacheProxy.hasKey(checkPointSpaceCacheKey)) {
                                Set<String> checkPointPartitionCacheKeySet = new HashSet<>();
                                checkPointPartitionCacheKeySet.add(checkPointPartitionCacheKey);
                                cacheProxy.set(checkPointSpaceCacheKey, checkPointPartitionCacheKeySet);
                            }
                            cacheProxy.set(checkPointPartitionCacheKey, checkpoint);
                        });
                    }
                })
                .buildEventProcessorClient();
        eventProcessorClient.start();
    }
}