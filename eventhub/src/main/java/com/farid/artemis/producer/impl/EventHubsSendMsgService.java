package com.farid.artemis.producer.impl;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.azure.messaging.eventhubs.models.CreateBatchOptions;
import com.farid.artemis.constant.EventHubsConstant;
import com.farid.artemis.producer.IEventHubsSendMsgService;
import com.farid.artemis.utils.EventHubsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventHubsSendMsgService implements IEventHubsSendMsgService {

    private final static EventHubProducerClient PRODUCER_CLIENT = new EventHubClientBuilder()
            .connectionString(EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_CONNECTION_STRING,
                    EventHubsConstant.EVENT_HUBS_EEC_NAME_SPACE_EVENT_HUB_NAME)
            .retryOptions(EventHubsUtil.getAmqpRetryOptions())
            .buildProducerClient();

    @Override
    public void sendEventHubsMsg(String message, String partitionId) {
        EventDataBatch eventDataBatch = StringUtils.isBlank(partitionId) ? PRODUCER_CLIENT.createBatch() :
                PRODUCER_CLIENT.createBatch(new CreateBatchOptions().setPartitionId(partitionId));

        eventDataBatch.tryAdd(new EventData(message));
        // 发送消息
        PRODUCER_CLIENT.send(eventDataBatch);
        log.info("EventHubsSendMsgService sendEventHubsMsgDistributePartition producer send message = {}", message);
    }
}
