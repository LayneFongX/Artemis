package com.farid.artemis.listener;

import com.alibaba.fastjson.JSON;
import com.farid.artemis.domain.biz.kv.SmartBusinessStorageVO;
import com.farid.artemis.domain.biz.kv.event.KvStorageBaseEvent;
import com.farid.artemis.domain.biz.kv.event.KvStorageChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KvStorageListener {

    @EventListener(value = KvStorageChangeEvent.class)
    public void processHomeServerRecord(KvStorageBaseEvent<SmartBusinessStorageVO> kvStorageBaseEvent) {
        SmartBusinessStorageVO eventData = kvStorageBaseEvent.getEventData();
        log.info("KvStorageListener processHomeServerRecord eventData = {}", JSON.toJSON(eventData));
    }
}