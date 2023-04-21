package com.farid.artemis.adapt;

public interface ISchneiderCloudMigrateAdapterService {

    /**
     * Equipment数据同步
     *
     * @param domain
     * @param siteId
     * @param equipmentId
     * @param from
     * @param to
     */
    Boolean syncEquipmentEnergyHoursHistory(String domain, String siteId, String equipmentId, String startDate, String endDate);

}
