package com.farid.artemis.domain.biz.kv.event;

import com.farid.artemis.domain.biz.kv.SmartBusinessStorageVO;


public class KvStorageChangeEvent extends KvStorageBaseEvent<SmartBusinessStorageVO> {

    public KvStorageChangeEvent(Object source, SmartBusinessStorageVO storageVO) {
        super(source, storageVO);
    }
}
