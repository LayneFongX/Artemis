package com.farid.artemis.invoke;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.token.IRemoteTokenService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class HttpInvokerProxy {

    public static final String BEARER = "Bearer ";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private IRemoteTokenService remoteToken;


    /**
     * 带header和body的传参
     *
     * @param url
     * @return
     * @author nanyan.wu@tuya.com
     * @date
     */
    public <T> String syncPostHeaderBody(String url, Map<String, T> map, HttpHeaders headers) {
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(type);
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(map), headers);
        return restTemplate.postForObject(url, entity, String.class);
    }

    /**
     * 带header/body/token的传参
     *
     * @param url
     * @return
     * @date
     */
    public <T> String syncPostHeaderBodyV2(String url, String token, Map<String, T> map, HttpHeaders headers) {
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(type);
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + token);
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(map), headers);
        return restTemplate.postForObject(url, entity, String.class);
    }

    /**
     * 带请求头的get请求
     *
     * @param url
     * @return
     */
    public String getForObjecty(String url, HttpHeaders headers) {
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class).getBody();
    }

    public ResponseEntity getForObject(String url, HttpHeaders headers) {
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    public String patchForObject(String url, JSONObject bodyJson, HttpHeaders headers) {
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(bodyJson, headers);
        return restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, String.class).getBody();
    }

    public ResponseEntity patchForObjectResponseEntity(String url, JSONArray jsonArray, HttpHeaders header) {
        // 加上oauth2.0token认证
        header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        header.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));

        // 构建body
        HttpEntity<JSONArray> requestEntity = new HttpEntity<>(jsonArray, header);
        return restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, JSON.class);
    }

    /**
     * 带请求头和body的put请求
     */
    public String putForObjecty(String url, String body, HttpHeaders headers) {
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class).getBody();
    }

    public String deleteHeaderBody(String url, String body, HttpHeaders headers) {
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class).getBody();
    }

    public ResponseEntity deleteHeaderBody(String url, HttpHeaders headers) {
        headers.add(HttpHeaders.AUTHORIZATION, BEARER + remoteToken.getToken(url));
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE));
        HttpEntity<String> requestEntity = new HttpEntity<>(Strings.EMPTY, headers);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
    }
}
