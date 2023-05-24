package com.farid.artemis.controller;

import com.farid.artemis.domain.biz.equipment.EliqEquipmentBO;
import com.farid.artemis.domain.biz.site.EECSiteBO;
import com.farid.artemis.service.IHttpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/http")
public class HttpController {

    @Resource
    private IHttpService httpService;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello(String deviceId, String params1) {
        return ResponseEntity.ok(httpService.sayHello());
    }

    @PostMapping("/createSite")
    public ResponseEntity<Void> createSite() {
        httpService.createSite();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getSite")
    public ResponseEntity<EECSiteBO> getSite(@RequestParam String siteId) {
        EECSiteBO siteBO = httpService.getSite(siteId);
        return ResponseEntity.ok(siteBO);
    }

    @GetMapping("/getEquipments")
    public ResponseEntity<List<EliqEquipmentBO>> getEquipments(@RequestParam String siteId) {
        List<EliqEquipmentBO> equipments = httpService.getEquipments(siteId);
        return ResponseEntity.ok(equipments);
    }

    @PutMapping("/updateSite")
    public ResponseEntity<Void> updateSite() {
        httpService.updateSite();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/migrateData")
    public ResponseEntity<Void> migrateData() {
        httpService.migrateData();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
