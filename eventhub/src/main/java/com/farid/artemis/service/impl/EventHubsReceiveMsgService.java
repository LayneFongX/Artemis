package com.farid.artemis.service.impl;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.farid.artemis.service.IEventHubsReceiveMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author Banchao
 * @Date 2023/5/9 10:59
 */

@Slf4j
@Service
public class EventHubsReceiveMsgService implements IEventHubsReceiveMsgService {

    private final static String CONNECTION = "Endpoint=sb://farid-event-hubs.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=E37JZ7wBv59yfx1aoKAl5mwUVHEOFmM5y+ASbPpxCgU=;EntityPath=faridqueue";

    private final static String QUEUE_NAME = "faridqueue";

    @Override
    public void receiveMessages() throws InterruptedException {
        CountDownLatch countdownLatch = new CountDownLatch(1);

        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(CONNECTION)
                .processor()
                .queueName(QUEUE_NAME)
                .processMessage(QueueProcessMessage::processMessage)
                .processError(context -> QueueProcessMessage.processError(context, countdownLatch))
                .buildProcessorClient();

        System.out.println("Starting the processor");
        processorClient.start();

        TimeUnit.SECONDS.sleep(10);
        System.out.println("Stopping and closing the processor");
        processorClient.close();
    }
}
