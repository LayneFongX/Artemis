package com.farid.artemis.service;

import java.io.IOException;

/**
 * @Author Banchao
 * @Date 2023/5/9 10:58
 */
public interface IEventHubsReceiveMsgService {

    void receiveMessages() throws IOException;
}
