package com.farid.artemis.service.impl;


import com.farid.artemis.service.IEventHubsReceiveMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author Banchao
 * @Date 2023/5/9 10:59
 */

@Slf4j
@Service
public class EventHubsReceiveMsgService implements IEventHubsReceiveMsgService {

    // private final static String EVENT_HUB_CONNECTION = "Endpoint=sb://farid-event-hubs.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=moHMVM6lLekAbdi9y/TJLIX/yvKxQrynx+AEhDFqCwQ=";
    //
    // private final static String EVENT_HUB_NAME = "myeventhub";
    //
    // private static final String STORAGE_CONNECTION = "DefaultEndpointsProtocol=https;AccountName=fdstorageaccount;AccountKey=VFgne15epNUcs0ruPqbiMSNakmJgNS3BsEUYq1ZWYeAHUsBzlA9WCmlxwGUpPF1uxXJwCpnqR30B+AStxqt1Eg==;EndpointSuffix=core.windows.net";
    //
    // private static final String STORAGE_CONTAINER_NAME = "mycontainer";

    @Override
    public void receiveMessages() throws IOException {
        // // Create a blob container client that you use later to build an event processor client to receive and process events
        // BlobContainerAsyncClient blobContainerAsyncClient = new BlobContainerClientBuilder()
        //         .connectionString(STORAGE_CONNECTION)
        //         .containerName(STORAGE_CONTAINER_NAME)
        //         .buildAsyncClient();
        //
        // // Create a builder object that you will use later to build an event processor client to receive and process events and errors.
        // EventProcessorClientBuilder eventProcessorClientBuilder = new EventProcessorClientBuilder()
        //         .connectionString(EVENT_HUB_CONNECTION, EVENT_HUB_NAME)
        //         .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
        //         .processEvent(PARTITION_PROCESSOR)
        //         .processError(ERROR_HANDLER)
        //         .checkpointStore(new BlobCheckpointStore(blobContainerAsyncClient));
        //
        // // Use the builder object to create an event processor client
        // EventProcessorClient eventProcessorClient = eventProcessorClientBuilder.buildEventProcessorClient();
        //
        // System.out.println("Starting event processor");
        // eventProcessorClient.start();
        //
        // System.out.println("Press enter to stop.");
        // System.in.read();
        //
        // System.out.println("Stopping event processor");
        // eventProcessorClient.stop();
        // System.out.println("Event processor stopped.");
        //
        // System.out.println("Exiting process");
    }

    // public static final Consumer<EventContext> PARTITION_PROCESSOR = eventContext -> {
    //     PartitionContext partitionContext = eventContext.getPartitionContext();
    //     EventData eventData = eventContext.getEventData();
    //
    //     System.out.printf("Processing event from partition %s with sequence number %d with body: %s%n",
    //             partitionContext.getPartitionId(), eventData.getSequenceNumber(), eventData.getBodyAsString());
    //
    //     if (eventData.getSequenceNumber() % 10 == 0) {
    //         eventContext.updateCheckpoint();
    //     }
    // };
    //
    // public static final Consumer<ErrorContext> ERROR_HANDLER = errorContext -> {
    //     System.out.printf("Error occurred in partition processor for partition %s, %s.%n",
    //             errorContext.getPartitionContext().getPartitionId(),
    //             errorContext.getThrowable());
    // };
}
