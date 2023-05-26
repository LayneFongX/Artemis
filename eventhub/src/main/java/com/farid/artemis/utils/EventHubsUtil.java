package com.farid.artemis.utils;

import com.azure.core.amqp.AmqpRetryOptions;
import com.azure.messaging.eventhubs.models.Checkpoint;

import java.time.Duration;

/**
 * @Author Banchao
 * @Date 2023/5/11 15:11
 */
public class EventHubsUtil {

    public static AmqpRetryOptions getAmqpRetryOptions(){
        AmqpRetryOptions retryOptions = new AmqpRetryOptions();
        retryOptions.setMaxRetries(3);  // 设置最大重试次数
        retryOptions.setDelay(Duration.ofSeconds(1)) ; // 设置重试延迟时间为 1 秒
        retryOptions.setMaxDelay(Duration.ofSeconds(10));  // 设置最大重试延迟时间为 10 秒
        retryOptions.setTryTimeout(Duration.ofMinutes(5));  // 设置重试操作的超时时间为 5 分钟
        return retryOptions;
    }

    public static String getCheckPointSpaceCacheKey(String fullyQualifiedNamespace, String eventHubName,
                                                    String consumerGroup) {
        return fullyQualifiedNamespace + "/" + eventHubName + "/" + consumerGroup;
    }

    public static String getCheckPointPartitionCacheKey(String fullyQualifiedNamespace, String eventHubName,
                                                      String consumerGroup, String partitionId) {
        return fullyQualifiedNamespace + "/" + eventHubName + "/" + consumerGroup + "/" + partitionId;
    }

    public static Boolean isCheckpointValid(Checkpoint checkpoint) {
        return !(checkpoint == null || (checkpoint.getOffset() == null && checkpoint.getSequenceNumber() == null));
    }

}
