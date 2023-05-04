package com.farid.artemis;

import com.farid.artemis.utils.SchneiderDateTimeUtils;
import com.farid.artemis.utils.SchneiderZonedDateTimeUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Banchao
 * @Date 2023/4/25 09:26
 */
public class Junit1 {

    public static void main(String[] args) {

        String equipmentTimeZoneId = "Europe/Copenhagen";

        System.out.println(SchneiderDateTimeUtils.getYearBeginEndDtMap(equipmentTimeZoneId));
    }

}
