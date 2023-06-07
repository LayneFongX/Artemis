package com.farid.artemis.utils;

import com.alibaba.fastjson.JSON;
import com.azure.core.amqp.AmqpRetryOptions;
import com.azure.messaging.eventhubs.models.Checkpoint;
import com.azure.messaging.eventhubs.models.PartitionOwnership;

import java.time.Duration;

/**
 * @Author Banchao
 * @Date 2023/5/11 15:11
 */
public class EventHubsUtil {

    private static final String CONNECTION_STRING_ENDFIX = ".servicebus.windows.net";

    public static AmqpRetryOptions getAmqpRetryOptions(){
        AmqpRetryOptions retryOptions = new AmqpRetryOptions();
        retryOptions.setMaxRetries(3);  // 设置最大重试次数
        retryOptions.setDelay(Duration.ofSeconds(1)) ; // 设置重试延迟时间为 1 秒
        retryOptions.setMaxDelay(Duration.ofSeconds(10));  // 设置最大重试延迟时间为 10 秒
        retryOptions.setTryTimeout(Duration.ofMinutes(5));  // 设置重试操作的超时时间为 5 分钟
        return retryOptions;
    }

    public static String buildOwnershipKey(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
        return String.format("ownership:%s:%s:%s", fullyQualifiedNamespace, eventHubName, consumerGroup);
    }

    public static String buildOwnershipPartitionKey(String fullyQualifiedNamespace, String eventHubName, String consumerGroup,
                                                    String partitionId) {
        return String.format("ownership:%s:%s:%s:%s", fullyQualifiedNamespace, eventHubName, consumerGroup, partitionId);
    }

    public static String buildCheckpointKey(String fullyQualifiedNamespace, String eventHubName, String consumerGroup) {
        return String.format("checkpoint:%s:%s:%s", fullyQualifiedNamespace, eventHubName, consumerGroup);
    }

    public static String buildCheckpointPartitionKey(String fullyQualifiedNamespace, String eventHubName, String consumerGroup,
                                                    String partitionId) {
        return String.format("checkpoint:%s:%s:%s:%s", fullyQualifiedNamespace, eventHubName, consumerGroup, partitionId);
    }

    public static String serializeOwnership(PartitionOwnership ownership) {
        return JSON.toJSONString(ownership);
    }

    public static PartitionOwnership deserializeOwnership(String ownershipJson) {
        return JSON.parseObject(ownershipJson, PartitionOwnership.class);
    }

    public static String serializeCheckpoint(Checkpoint checkpoint) {
        return JSON.toJSONString(checkpoint);
    }

    public static Checkpoint deserializeCheckpoint(String checkpointJson) {
        return JSON.parseObject(checkpointJson, Checkpoint.class);
    }

}
