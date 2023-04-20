package com.farid.artemis.domain.biz.site;

import lombok.Data;

import java.io.Serializable;

@Data
public class EliqSiteGeoLocationBO implements Serializable {

    private Double latitude;

    private Double longitude;

}
