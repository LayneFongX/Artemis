package com.farid.artemis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.domain.base.apicontext.ApiRequestDO;
import com.farid.artemis.domain.biz.kv.SmartBusinessStorageVO;
import com.farid.artemis.domain.biz.kv.event.KvStorageChangeEvent;
import com.farid.artemis.enums.kv.BusinessStorageType;
import com.farid.artemis.proxy.CacheProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/storage")
public class KvStorageController {

    @Resource
    private CacheProxy cacheProxy;

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/save")
    public ResponseEntity<SmartBusinessStorageVO> addOrUpdateValueBaseAppV2(@RequestParam String key, @RequestParam String value,
                                                                            @RequestParam String remark,
                                                                            @RequestParam("apiRequestDo") String apiRequestDoJson) {
        ApiRequestDO apiRequestDo = JSONObject.parseObject(apiRequestDoJson, ApiRequestDO.class);
        Integer appId = apiRequestDo.getAppInfoDo().getAppId();
        SmartBusinessStorageVO storageVO = addOrUpdateValue("", BusinessStorageType.APP,
                String.valueOf(appId), key, value, remark, apiRequestDo);
        eventPublisher.publishEvent(new KvStorageChangeEvent(this, storageVO));
        return ResponseEntity.ok(storageVO);
    }

    private SmartBusinessStorageVO addOrUpdateValue(String uid, BusinessStorageType resType, String resId,
                                                    String key, String value, String remark, ApiRequestDO apiRequestDo) {
        Integer bizType = apiRequestDo.getAppInfoDo().getBizType();

        SmartBusinessStorageVO storageVO = SmartBusinessStorageVO.builder()
                .bizType(bizType).uid(uid)
                .resType(resType).resId(resId)
                .key(key).value(value).remark(remark)
                .build();

        if (BusinessStorageType.APP == resType) {
            storageVO.setAppId(apiRequestDo.getAppInfoDo().getAppId());
        }
        log.info("KvStorageController addOrUpdateValue addOrUpdateKvStorageParam = {}", JSON.toJSON(storageVO));
        cacheProxy.setValue(key, JSON.toJSONString(storageVO));
        return storageVO;
    }
}
