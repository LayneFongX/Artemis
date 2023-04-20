package com.farid.artemis.domain.biz.site;

import lombok.Data;

import java.io.Serializable;

@Data
public class EliqSiteBO implements Serializable {

    private static final long serialVersionUID = -3407764897248294957L;

    private String id;

    private String name;

    private String country;

    private String zipCode;

    private String timeZone;

    private EliqSiteGeoLocationBO geolocation;

    private String application;

    private String connectionStatus;

    private String creationDate;

}
