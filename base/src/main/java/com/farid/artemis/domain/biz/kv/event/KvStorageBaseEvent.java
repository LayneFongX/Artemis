package com.farid.artemis.domain.biz.kv.event;

import org.springframework.context.ApplicationEvent;


public abstract class KvStorageBaseEvent<T> extends ApplicationEvent {

    private final T eventData;

    public KvStorageBaseEvent(Object source, T eventData) {
        super(source);
        this.eventData = eventData;
    }

    public T getEventData() {
        return eventData;
    }
}
