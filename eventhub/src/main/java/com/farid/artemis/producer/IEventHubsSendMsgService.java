package com.farid.artemis.producer;

/**
 * @Author Banchao
 * @Date 2023/5/10 17:02
 */
public interface IEventHubsSendMsgService {

    void sendEventHubsMsg(String message,String partitionId);
}
