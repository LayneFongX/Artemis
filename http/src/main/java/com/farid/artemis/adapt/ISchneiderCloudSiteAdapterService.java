package com.farid.artemis.adapt;

import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.domain.biz.site.EECSiteBO;

public interface ISchneiderCloudSiteAdapterService {

    /**
     * 获取三方Site信息
     *
     * @param domain
     * @param siteId
     * @return
     */
    EECSiteBO getCloudSite(String domain, String siteId);

    /**
     * 创建三方Site
     *
     * @param domain
     * @param body
     */
    void createCloudSite(String domain, JSONObject body);

    /**
     * 更新三方Site
     *
     * @param domain
     * @param siteId
     * @param body
     * @return
     */
    Boolean updateCloudSite(String domain, String siteId, JSONObject body);

    /**
     * 删除三方Site信息
     *
     * @param domain
     * @param siteId
     * @return
     */
    Boolean deleteCloudSite(String domain, String siteId);
}
