package com.farid.artemis.adapt.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.farid.artemis.adapt.ISchneiderCloudEquipmentAdapterService;
import com.farid.artemis.domain.biz.equipment.EliqEquipmentBO;
import com.farid.artemis.invoke.HttpInvokerProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author banchao.feng@tuya.com
 * @description: 三方云设备数据操作
 * @date 2023/3/14
 */
@Slf4j
@Service
public class SchneiderCloudEquipmentAdapterService implements ISchneiderCloudEquipmentAdapterService {

    @Resource
    private HttpInvokerProxy httpInvokerProxy;

    private final static String ERROR_CODE = "errorCode";

    private final static String ERROR_TEXT = "errorText";

    private final static String ERROR_TEXT_MSG = "Equipment not found";

    @Override
    public List<EliqEquipmentBO> getEquipments(String domain, String siteId) {
        String url = domain + "/v1/sites/" + siteId + "/equipments";
        log.info("SchneiderCloudEquipmentAdapterService getEquipments step-01 siteId = {},url = {}", siteId, url);

        HttpHeaders header = new HttpHeaders();

        try {
            String res = httpInvokerProxy.getForObjecty(url, header);
            log.info("SchneiderCloudEquipmentAdapterService getEquipments step-02 success, siteId = {},url = {},res = {}",
                    siteId, url, res);
            return JSON.parseObject(res, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.info("SchneiderCloudEquipmentAdapterService getEquipments step-03 failed, siteId = {},url = {}", siteId, url, e);
        }
        return Collections.emptyList();
    }

    @Override
    public EliqEquipmentBO getEquipmentById(String domain, String siteId, String equipmentId) {
        String url = domain + "/v1/sites/" + siteId + "/equipments/" + equipmentId;
        log.info("SchneiderCloudEquipmentAdapterService getEquipmentById step-01 siteId = {},equipmentId = {},url = {}", siteId,
                equipmentId, url);

        HttpHeaders header = new HttpHeaders();

        try {
            ResponseEntity responseEntity = httpInvokerProxy.getForObject(url, header);
            // 解析结果
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                EliqEquipmentBO eliqEquipmentBO = JSON.parseObject(String.valueOf(responseEntity.getBody()), EliqEquipmentBO.class);
                log.info("SchneiderCloudEquipmentAdapterService getEquipmentById step-02 success, siteId = {},equipmentId = {}," +
                        "eliqEquipmentBO = {}", siteId, equipmentId, JSON.toJSON(eliqEquipmentBO));
                return eliqEquipmentBO;
            }
        } catch (Exception e) {
            log.info("SchneiderCloudEquipmentAdapterService getEquipmentById step-03 failed, siteId = {},equipmentId = {}", siteId,
                    equipmentId, e);
        }
        return null;
    }

    @Override
    public boolean createEquipment(String domain, String siteId, String equipmentId, JSONObject body) {
        String url = domain + "/v1/sites/" + siteId + "/equipments";
        body = getCreateEquipmentBody(equipmentId, body);
        log.info("SchneiderCloudEquipmentAdapterService createEquipment step-01 siteId = {},equipmentId = {},url = {},body = {}", siteId,
                equipmentId, url, body);

        HttpHeaders header = new HttpHeaders();
        try {
            httpInvokerProxy.syncPostHeaderBody(url, body, header);
            log.info("SchneiderCloudEquipmentAdapterService createEquipment step-02 success, siteId = {},equipmentId = {},url = {}," +
                    "body = {}", siteId, equipmentId, url, body);
            return true;
        } catch (Exception e) {
            log.info("SchneiderCloudEquipmentAdapterService createEquipment step-03 failed, siteId = {},equipmentId = {},url = {}, " +
                    "body = {}", siteId, equipmentId, url, body, e);
        }
        return false;
    }

    private JSONObject getCreateEquipmentBody(String equipmentId, JSONObject body) {
        if (Objects.nonNull(body)) {
            return body;
        }

        body = new JSONObject();
        body.put("id", equipmentId);
        body.put("label", "Main incomer");
        body.put("family", "grid");
        return body;
    }

    @Override
    public boolean updateEquipment(String domain, String siteId, String equipmentId, JSONObject body) {
        String url = domain + "/v1/sites/" + siteId + "/equipments/" + equipmentId;
        log.info("SchneiderCloudEquipmentAdapterService updateEquipment step-01 siteId = {},equipmentId = {},url = {},body = {}", siteId,
                equipmentId, url, body);

        HttpHeaders header = new HttpHeaders();

        try {
            httpInvokerProxy.patchForObject(url, body, header);
            log.info("SchneiderCloudEquipmentAdapterService updateEquipment step-02 success, siteId = {},equipmentId = {},url = {}," +
                    "body = {}", siteId, equipmentId, url, body);
            return true;
        } catch (Exception e) {
            log.info("SchneiderCloudEquipmentAdapterService updateEquipment step-03 failed, siteId = {},equipmentId = {},url = {}, " +
                    "body = {}", siteId, equipmentId, url, body, e);
        }
        return false;
    }

    @Override
    public boolean deleteEquipment(String domain, String siteId, String equipmentId) {
        String url = domain + "/v1/sites/" + siteId + "/equipments/" + equipmentId;
        log.info("SchneiderCloudEquipmentAdapterService deleteEquipment step-01 siteId = {},equipmentId = {},url = {}", siteId,
                equipmentId, url);

        HttpHeaders header = new HttpHeaders();

        try {
            httpInvokerProxy.deleteHeaderBody(url, null, header);
            log.info("SchneiderCloudEquipmentAdapterService deleteEquipment step-02 success, siteId = {},equipmentId = {},url = {}", siteId,
                    equipmentId, url);
            return Boolean.TRUE;
        } catch (HttpClientErrorException e) {
            log.info("SchneiderCloudEquipmentAdapterService deleteEquipment step-03 fail, siteId = {}, equipmentId = {}," +
                    "url = {}", siteId, equipmentId, url, e);
            return getDeleteEquipmentErrorResult(e);
        } catch (Exception e2) {
            log.info("SchneiderCloudEquipmentAdapterService deleteEquipment step-04 fail, siteId = {}, equipmentId = {}," +
                    "url = {}", siteId, equipmentId, url, e2);
            return Boolean.FALSE;
        }
    }

    private Boolean getDeleteEquipmentErrorResult(HttpClientErrorException e) {
        String errorResponseBody = e.getResponseBodyAsString();
        JSONObject errorResponseJson = JSONObject.parseObject(errorResponseBody);
        log.info("SchneiderCloudEquipmentAdapterService getDeleteEquipmentErrorResult errorResponseBody = {},errorResponseJson = {}",
                errorResponseBody, errorResponseJson);

        if (Objects.equals(errorResponseJson.getInteger(ERROR_CODE), HttpStatus.NOT_FOUND.value()) &&
                errorResponseJson.getString(ERROR_TEXT).equals(ERROR_TEXT_MSG)) {
            log.info("SchneiderCloudEquipmentAdapterService getDeleteEquipmentErrorResult step-02 equipment deleted success");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
