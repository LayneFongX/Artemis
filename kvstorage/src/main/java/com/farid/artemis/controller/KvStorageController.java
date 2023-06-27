package com.farid.artemis.controller;

import com.alibaba.fastjson.JSON;
import com.farid.artemis.domain.base.apicontext.ApiRequestDO;
import com.farid.artemis.domain.biz.kv.SmartBusinessStorageVO;
import com.farid.artemis.enums.kv.BusinessStorageType;
import com.farid.artemis.proxy.CacheProxy;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/save")
    public ResponseEntity<Boolean> addOrUpdateValueBaseAppV2(@RequestParam String key, @RequestParam String value,
                                                             @RequestParam String remark,
                                                             @RequestParam ApiRequestDO apiRequestDo) {
        Integer appId = apiRequestDo.getAppInfoDo().getAppId();
        Boolean isSuccess = addOrUpdateValue("", BusinessStorageType.APP, String.valueOf(appId), key, value,
                remark, apiRequestDo);
        return ResponseEntity.ok(isSuccess);
    }

    private Boolean addOrUpdateValue(String uid, BusinessStorageType resType, String resId,
                                     String key, String value, String remark, ApiRequestDO apiRequestDo) {
        Integer bizType = apiRequestDo.getAppInfoDo().getBizType();

        SmartBusinessStorageVO addOrUpdateKvStorageParam = SmartBusinessStorageVO.builder()
                .bizType(bizType).uid(uid)
                .resType(resType).resId(resId)
                .key(key).value(value).remark(remark)
                .build();

        if (BusinessStorageType.APP == resType) {
            // 目前感觉appId只有在app维度需要加上，以后如果所有维度需要再直接挪到builder那里吧
            addOrUpdateKvStorageParam.setAppId(apiRequestDo.getAppInfoDo().getAppId());
        }
        log.info("KvStorageController addOrUpdateValue addOrUpdateKvStorageParam = {}", JSON.toJSON(addOrUpdateKvStorageParam));
        cacheProxy.setValue(key, JSON.toJSONString(addOrUpdateKvStorageParam));
        return true;
    }
}
