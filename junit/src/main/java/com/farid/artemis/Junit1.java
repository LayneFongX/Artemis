package com.farid.artemis;

import com.farid.artemis.utils.SchneiderZonedDateTimeUtils;

import java.time.ZonedDateTime;

/**
 * @Author Banchao
 * @Date 2023/4/25 09:26
 */
public class Junit1 {

    private final static String BEGIN_STORE_TIME = "2022-09-01T00:00:00Z";


    public static void main(String[] args) {
        String from = "2022-02-01T12:51:15Z";
        String equipmentTimeZoneId = "Europe/Copenhagen";

        ZonedDateTime beginZonedDateTime = SchneiderZonedDateTimeUtils.getTargetZonedDateTime(BEGIN_STORE_TIME, equipmentTimeZoneId);
        ZonedDateTime fromZonedDateTime = SchneiderZonedDateTimeUtils.getTargetZonedDateTime(from, equipmentTimeZoneId);
        if (fromZonedDateTime.isBefore(beginZonedDateTime)){
            fromZonedDateTime = beginZonedDateTime;
        }
        System.out.println(fromZonedDateTime);
    }

}
