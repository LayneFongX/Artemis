package com.farid.artemis.adapt.impl;

import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.adapt.ISchneiderCloudMigrateAdapterService;
import com.farid.artemis.invoke.HttpInvokerProxy;
import com.farid.artemis.token.IRemoteTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author banchao.feng@tuya.com
 * @description: 三方云同步数据操作
 * @date 2023/3/14
 */
@Slf4j
@Service
public class SchneiderCloudMigrateAdapterService implements ISchneiderCloudMigrateAdapterService {

    @Resource
    private HttpInvokerProxy httpInvokerProxy;

    @Resource
    private IRemoteTokenService remoteToken;

    private final static String ERROR_CODE = "errorCode";

    private final static String ERROR_TEXT = "errorText";

    private final static String ERROR_TEXT_MSG = "already migrated";

    @Override
    public Boolean syncEquipmentEnergyHoursHistory(String domain, String siteId, String equipmentId, String startDate, String endDate) {
        String token = remoteToken.getToken(domain);

        String url = domain.replace("/eec", "/migration") + "/v1/sites/" + siteId + "/equipment/" + equipmentId + "/datamigration";

        HttpHeaders header = new HttpHeaders();
        JSONObject requestBody = new JSONObject();
        requestBody.put("startDate", startDate);
        requestBody.put("endDate", endDate);

        try {
            httpInvokerProxy.syncPostHeaderBodyV2(url, token, requestBody, header);
            return Boolean.TRUE;
        } catch (HttpClientErrorException e) {
            return getErrorResult(e);
        } catch (Exception e2) {
            return Boolean.FALSE;
        }
    }

    private Boolean getErrorResult(HttpClientErrorException e) {
        String errorResponseBody = e.getResponseBodyAsString();
        JSONObject errorResponseJson = JSONObject.parseObject(errorResponseBody);

        if (Objects.equals(errorResponseJson.getInteger(ERROR_CODE), HttpStatus.BAD_REQUEST.value()) &&
                errorResponseJson.getString(ERROR_TEXT).contains(ERROR_TEXT_MSG)) {
            log.info("SchneiderCloudMigrateAdapterService getErrorResult data for this site and equipment already migrated");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
