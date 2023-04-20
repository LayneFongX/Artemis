package com.farid.artemis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.adapt.ISchneiderCloudSiteAdapterService;
import com.farid.artemis.service.IHttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Banchao
 * @Date 2023/4/20 16:49
 */

@Slf4j
@Service
public class HttpService implements IHttpService {

    @Resource
    private ISchneiderCloudSiteAdapterService cloudSiteAdapterService;

    @Override
    public String sayHello() {
        return "Hello,this is httpService.";
    }

    @Override
    public void createSite() {
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("id", "Test_update_location_id");
        bodyJson.put("application", "WiserBySE");
        bodyJson.put("name", "Test_update_location_name");
        bodyJson.put("country", "SE");
        bodyJson.put("timeZone", "Europe/Stockholm");

        String domain = "https://fdcs-qa.wiser.schneider-electric.com/eec";
        cloudSiteAdapterService.createCloudSite(domain, bodyJson);
    }

    @Override
    public void updateSite() {
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("id", "Test_update_location_id");
        bodyJson.put("application", "WiserBySE");
        bodyJson.put("name", "Test_update_location_name");
        bodyJson.put("country", "SE");
        bodyJson.put("timeZone", "Europe/Stockholm");

        JSONObject geoJson = new JSONObject();
        geoJson.put("latitude", 60.92270095982282);
        geoJson.put("longitude", 8.21315735578537);

        bodyJson.put("geolocation", geoJson);

        String domain = "https://fdcs-qa.wiser.schneider-electric.com/eec";
        cloudSiteAdapterService.updateCloudSite(domain, "Test_update_location_id", bodyJson);
    }
}
