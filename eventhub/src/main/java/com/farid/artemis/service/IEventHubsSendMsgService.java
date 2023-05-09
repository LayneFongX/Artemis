package com.farid.artemis.service;

/**
 * @Author Banchao
 * @Date 2023/5/9 10:58
 */
public interface IEventHubsSendMsgService {

    void sendEventHubsMsg();

    void batchSendEventHubsMsg();
}
