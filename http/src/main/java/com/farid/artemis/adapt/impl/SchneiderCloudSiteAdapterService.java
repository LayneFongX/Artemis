package com.farid.artemis.adapt.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.adapt.ISchneiderCloudSiteAdapterService;
import com.farid.artemis.domain.biz.site.EECSiteBO;
import com.farid.artemis.invoke.HttpInvokerProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SchneiderCloudSiteAdapterService implements ISchneiderCloudSiteAdapterService {

    @Resource
    private HttpInvokerProxy httpInvokerProxy;

    @Override
    public EECSiteBO getCloudSite(String domain, String siteId) {
        String url = domain + "/v1/sites/" + siteId;
        HttpHeaders header = new HttpHeaders();
        ResponseEntity responseEntity = httpInvokerProxy.getForObject(url, header);
        return JSON.parseObject(String.valueOf(responseEntity.getBody()), EECSiteBO.class);
    }

    @Override
    public void createCloudSite(String domain, JSONObject body) {
        String url = domain + "/v1/sites";
        HttpHeaders header = new HttpHeaders();
        httpInvokerProxy.syncPostHeaderBody(url, body, header);
    }

    @Override
    public Boolean updateCloudSite(String domain, String siteId, JSONObject body) {
        String url = domain + "/v1/sites/" + siteId;
        HttpHeaders header = new HttpHeaders();
        httpInvokerProxy.putForObjecty(url, JSON.toJSONString(body), header);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteCloudSite(String domain, String siteId) {
        String url = domain + "/v1/sites/" + siteId;
        HttpHeaders header = new HttpHeaders();
        httpInvokerProxy.deleteHeaderBody(url, header);
        log.info("SchneiderCloudSiteAdapterService deleteCloudSite step-02 success, siteId = {},url = {}", siteId, url);
        return Boolean.TRUE;
    }
}
