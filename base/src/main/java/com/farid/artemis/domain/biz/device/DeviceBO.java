package com.farid.artemis.domain.biz.device;


import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public class DeviceBO extends BaseBO {

    private String id;
    private String uid;
    private String timeZone;
    private String productId;
    private String uuid;
    private Boolean isExists;
    private Integer accessType;
    private Integer ability;
    private Boolean sub;
    private String timeZoneId;
    private String ownerId;
    private String mac;
    private Long activeTime;

    private String name;
    private String customName;
    private String icon;
    private String lat;
    private String lon;

    private String productVersion;
    private Boolean status;

}
