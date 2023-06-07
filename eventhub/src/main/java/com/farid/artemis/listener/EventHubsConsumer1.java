package com.farid.artemis.listener;

import com.alibaba.fastjson.JSON;
import com.azure.messaging.eventhubs.CheckpointStore;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventProcessorClient;
import com.azure.messaging.eventhubs.EventProcessorClientBuilder;
import com.azure.messaging.eventhubs.models.Checkpoint;
import com.azure.messaging.eventhubs.models.PartitionContext;
import com.azure.messaging.eventhubs.models.PartitionOwnership;
import com.farid.artemis.constant.EventHubsConstant;
import com.farid.artemis.proxy.CacheProxy;
import com.farid.artemis.utils.EventHubsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component
public class EventHubsConsumer1 {

    @Resource
    private CacheProxy cacheProxy;

    private EventProcessorClient eventProcessorClient;

    @PostConstruct
    public void init() {
        clearOwnershipCache();
        startConsumer();
        log.info("EventHubsConsumer1 init success");
    }

    @PreDestroy
    public void destroy() {
        log.info("EventHubsConsumer1 destroy success");
        eventProcessorClient.stop();
        log.info("EventHubsConsumer1 destroy success2");
    }


    public void startConsumer() {
        eventProcessorClient = new EventProcessorClientBuilder()
                .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING, EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
                .consumerGroup(EventHubsConstant.EVENT_HUBS_EEC_GROUP_NAME)
                .processEvent(eventContext -> {
                    PartitionContext partitionContext = eventContext.getPartitionContext();
                    EventData eventData = eventContext.getEventData();
                    log.info("EventHubsConsumer1 startConsumer processEvent from partition = {} with sequence number = {} " +
                                    "with body = {}", partitionContext.getPartitionId(), eventData.getSequenceNumber(),
                            eventData.getBodyAsString());
                    eventContext.updateCheckpoint();
                })
                .processError(errorContext -> {
                    log.info("EventHubsConsumer1 startConsumer processError occurred in partition processor for partition {}",
                            errorContext.getPartitionContext().getPartitionId(), errorContext.getThrowable());
                })
                .checkpointStore(getCheckpointStore())
                .buildEventProcessorClient();
        eventProcessorClient.start();
    }

    private CheckpointStore getCheckpointStore() {
        return new CheckpointStore() {
            @Override
            public Flux<PartitionOwnership> claimOwnership(List<PartitionOwnership> requestedPartitionOwnerships) {
                List<PartitionOwnership> claimedOwnerships = new ArrayList<>();
                for (PartitionOwnership ownership : requestedPartitionOwnerships) {
                    String ownershipKey = EventHubsUtil.buildOwnershipKey(ownership.getFullyQualifiedNamespace(),
                            ownership.getEventHubName(), ownership.getConsumerGroup());
                    String ownershipPartitionKey = EventHubsUtil.buildOwnershipPartitionKey(ownership.getFullyQualifiedNamespace(),
                            ownership.getEventHubName(), ownership.getConsumerGroup(), ownership.getPartitionId());

                    ownership.setLastModifiedTime(System.currentTimeMillis());
                    ownership.setETag(UUID.randomUUID().toString());

                    String ownershipPartitionValue = cacheProxy.getValue(ownershipPartitionKey);
                    if (StringUtils.isBlank(ownershipPartitionValue)) {
                        cacheProxy.setValue(ownershipPartitionKey, JSON.toJSONString(ownership));
                        cacheProxy.sadd(ownershipKey, ownershipPartitionKey);
                        claimedOwnerships.add(ownership);
                        continue;
                    }

                    String requestOwnerId = ownership.getOwnerId();
                    String cacheOwnerId = JSON.parseObject(ownershipPartitionValue, PartitionOwnership.class).getOwnerId();
                    if (!Objects.equals(requestOwnerId, cacheOwnerId)) {
                        continue;
                    }

                    claimedOwnerships.add(ownership);
                }
                return Flux.fromIterable(claimedOwnerships);
            }

            @Override
            public Flux<PartitionOwnership> listOwnership(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
                String ownershipKey = EventHubsUtil.buildOwnershipKey(fullyQualifiedNamespace, eventHubName, consumerGroup);
                Set<String> ownershipPartitionKeySet = cacheProxy.smget(ownershipKey);
                List<PartitionOwnership> partitionOwnerships = new ArrayList<>();

                for (String ownershipPartitionKey : ownershipPartitionKeySet) {
                    String ownershipJson = cacheProxy.getValue(ownershipPartitionKey);
                    if (StringUtils.isBlank(ownershipJson)) {
                        continue;
                    }
                    partitionOwnerships.add(EventHubsUtil.deserializeOwnership(ownershipJson));
                }
                return Flux.fromIterable(partitionOwnerships);
            }

            @Override
            public Mono<Void> updateCheckpoint(Checkpoint checkpoint) {
                String checkpointKey = EventHubsUtil.buildCheckpointKey(checkpoint.getFullyQualifiedNamespace(),
                        checkpoint.getEventHubName(), checkpoint.getConsumerGroup());
                String checkpointPartitionKey = EventHubsUtil.buildCheckpointPartitionKey(checkpoint.getFullyQualifiedNamespace(),
                        checkpoint.getEventHubName(), checkpoint.getConsumerGroup(), checkpoint.getPartitionId());

                String ownershipPartitionKey = EventHubsUtil.buildOwnershipPartitionKey(checkpoint.getFullyQualifiedNamespace(),
                        checkpoint.getEventHubName(), checkpoint.getConsumerGroup(), checkpoint.getPartitionId());

                return Mono.fromRunnable(() -> {
                    String ownershipPartitionValue = cacheProxy.getValue(ownershipPartitionKey);
                    PartitionOwnership ownership = JSON.parseObject(ownershipPartitionValue, PartitionOwnership.class);
                    ownership.setLastModifiedTime(System.currentTimeMillis());
                    cacheProxy.setValue(ownershipPartitionKey, JSON.toJSONString(ownership));

                    cacheProxy.sadd(checkpointKey, checkpointPartitionKey);
                    cacheProxy.setValue(checkpointPartitionKey, EventHubsUtil.serializeCheckpoint(checkpoint));
                });
            }

            @Override
            public Flux<Checkpoint> listCheckpoints(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
                String checkpointKey = EventHubsUtil.buildCheckpointKey(fullyQualifiedNamespace, eventHubName, consumerGroup);
                Set<String> checkpointPartitionKeySet = cacheProxy.smget(checkpointKey);

                List<Checkpoint> checkpoints = new ArrayList<>();
                for (String checkpointPartitionKey : checkpointPartitionKeySet) {
                    String checkpointJson = cacheProxy.getValue(checkpointPartitionKey);
                    if (StringUtils.isBlank(checkpointJson)) {
                        continue;
                    }
                    Checkpoint checkpoint = EventHubsUtil.deserializeCheckpoint(checkpointJson);
                    checkpoints.add(checkpoint);
                }
                return Flux.fromIterable(checkpoints);
            }
        };
    }

    private void clearOwnershipCache() {
        String ownershipKey = EventHubsUtil.buildOwnershipKey(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE,
                EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME, EventHubsConstant.EVENT_HUBS_EEC_GROUP_NAME);
        Set<String> ownershipPartitionKeySet = cacheProxy.smget(ownershipKey);
        for (String ownershipPartitionKey : ownershipPartitionKeySet) {
            cacheProxy.deleteKey(ownershipPartitionKey);
        }
        cacheProxy.deleteKey(ownershipKey);
    }
}