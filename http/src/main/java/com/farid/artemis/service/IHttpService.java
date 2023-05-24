package com.farid.artemis.service;

import com.farid.artemis.domain.biz.equipment.EliqEquipmentBO;
import com.farid.artemis.domain.biz.site.EECSiteBO;

import java.util.List;

/**
 * @Author Banchao
 * @Date 2023/4/20 16:46
 */
public interface IHttpService {

    String sayHello();

    void createSite();

    void updateSite();

    void migrateData();

    EECSiteBO getSite(String siteId);

    List<EliqEquipmentBO> getEquipments(String siteId);
}
