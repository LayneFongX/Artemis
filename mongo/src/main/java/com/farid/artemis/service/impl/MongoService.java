package com.farid.artemis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.service.IMongoService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Banchao
 * @Date 2023/5/5 18:05
 */

@Service
public class MongoService implements IMongoService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void insert() {
        JSONObject insertJson = new JSONObject();
        insertJson.put("color", "red");
        insertJson.put("number", 1);
        insertJson.put("author", "Banchao");

        mongoTemplate.insert(insertJson, "test");
    }
}
