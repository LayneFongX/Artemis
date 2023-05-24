package com.farid.artemis.domain.biz.site;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class EECSiteBO implements Serializable {

    private static final long serialVersionUID = -3407764897248294957L;

    private String id;

    private String name;

    private String country;

    private String zipCode;

    private String timeZone;

    private Geolocation geolocation;

    private String application;

    private Boolean pvPanelsInstalled;

    private Boolean essInstalled;

    private String creationDate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geolocation {

        private Double latitude;

        private Double longitude;

    }
}
