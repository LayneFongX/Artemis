package com.farid.artemis.token.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.domain.biz.token.TokenRequest;
import com.farid.artemis.token.IRemoteTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;

@Slf4j
@Component
public class RemoteTokenService implements IRemoteTokenService {

    public static final String ACCESS_TOKEN = "access_token";

    @Resource
    private RestTemplate restTemplate;


    @Override
    public String getToken(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setGrant_type("client_credentials");
        tokenRequest.setClient_id("65c6a7c7-c1e5-406e-96c9-3a29489f5a1f");
        tokenRequest.setClient_secret("eV88Q~pWceZzEquLNhN2KdDghvvl_wW-_r6qsbz-");
        tokenRequest.setScope("api://a2d1dc76-da62-4dcd-bb2c-3e6c98233d87/.default");
        MultiValueMap<String, String> paramMap = object2Map(tokenRequest);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(paramMap, headers);
        String result =
                restTemplate.postForObject("https://login.microsoftonline.com/db8e2ba9-95c1-4fbb-b558-6bf8bb1d2981/oauth2/v2.0/token",
                        entity, String.class);
        return parseJSONResult(result);
    }

    /**
     * 解析请求返回值
     */
    private String parseJSONResult(String result) {
        JSONObject res = JSON.parseObject(result);
        return res.getString(ACCESS_TOKEN);
    }

    /**
     * 对象转Map
     *
     * @param object 要转换的对象
     * @return 转换后的Map
     */
    private MultiValueMap<String, String> object2Map(Object object) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Entry<String, Object>> entrySet = jsonObject.entrySet();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Entry<String, Object> entry : entrySet) {
            map.put(entry.getKey(), Collections.singletonList(String.valueOf(entry.getValue())));
        }
        return map;
    }
}
