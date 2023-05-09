package com.farid.artemis.service.impl;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusMessageBatch;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.farid.artemis.service.IEventHubsSendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Banchao
 * @Date 2023/5/9 10:59
 */

@Slf4j
@Service
public class EventHubsSendMsgService implements IEventHubsSendMsgService {

    private final static String CONNECTION = "Endpoint=sb://farid-event-hubs.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=E37JZ7wBv59yfx1aoKAl5mwUVHEOFmM5y+ASbPpxCgU=;EntityPath=faridqueue";

    private final static String QUEUE_NAME = "faridqueue";

    @Override
    public void sendEventHubsMsg() {
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(CONNECTION)
                .sender()
                .queueName(QUEUE_NAME)
                .buildClient();
        senderClient.sendMessage(new ServiceBusMessage("Hello, World!"));
        System.out.println("Sent a single message to the queue: " + QUEUE_NAME);

        senderClient.close();
    }

    @Override
    public void batchSendEventHubsMsg() {
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(CONNECTION)
                .sender()
                .queueName(QUEUE_NAME)
                .buildClient();

        ServiceBusMessageBatch messageBatch = senderClient.createMessageBatch();

        List<ServiceBusMessage> listOfMessages = createMessages();
        for (ServiceBusMessage message : listOfMessages) {
            if (messageBatch.tryAddMessage(message)) {
                continue;
            }

            senderClient.sendMessages(messageBatch);
            System.out.println("Sent a batch of messages to the queue: " + QUEUE_NAME);

            messageBatch = senderClient.createMessageBatch();
            if (!messageBatch.tryAddMessage(message)) {
                System.err.printf("Message is too large for an empty batch. Skipping. Max size: %s.", messageBatch.getMaxSizeInBytes());
            }
        }

        if (messageBatch.getCount() > 0) {
            senderClient.sendMessages(messageBatch);
            System.out.println("Sent a batch of messages to the queue: " + QUEUE_NAME);
        }

        senderClient.close();
    }

    private static List<ServiceBusMessage> createMessages() {
        ServiceBusMessage[] messages = {
                new ServiceBusMessage("First message"),
                new ServiceBusMessage("Second message"),
                new ServiceBusMessage("Third message")
        };
        return Arrays.asList(messages);
    }
}
