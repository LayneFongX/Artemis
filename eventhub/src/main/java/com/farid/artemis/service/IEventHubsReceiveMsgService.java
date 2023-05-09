package com.farid.artemis.service;

/**
 * @Author Banchao
 * @Date 2023/5/9 10:58
 */
public interface IEventHubsReceiveMsgService {

    void receiveMessages() throws InterruptedException;
}
