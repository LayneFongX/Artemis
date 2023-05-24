package com.farid.artemis.adapt;

import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.domain.biz.equipment.EliqEquipmentBO;

import java.util.List;

public interface ISchneiderCloudEquipmentAdapterService {

    /**
     * 查询设备列表
     *
     * @param domain
     * @param siteId
     * @return
     */
    List<EliqEquipmentBO> getEquipments(String domain, String siteId);

    /**
     * 查询单设备
     *
     * @param domain
     * @param siteId
     * @param equipmentId
     * @return
     */
    EliqEquipmentBO getEquipmentById(String domain, String siteId, String equipmentId);

    /**
     * 创建设备
     *
     * @param domain
     * @param siteId
     * @param body
     * @return
     */
    boolean createEquipment(String domain, String siteId, String equipmentId, JSONObject body);

    /**
     * 修改设备
     *
     * @param domain
     * @param siteId
     * @param equipmentId
     * @param body
     * @return
     */
    boolean updateEquipment(String domain, String siteId, String equipmentId, JSONObject body);

    /**
     * 删除设备
     *
     * @param domain
     * @param siteId
     * @param equipmentId
     */
    boolean deleteEquipment(String domain, String siteId, String equipmentId);
}
