package com.farid.artemis;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * @Author Banchao
 * @Date 2023/4/25 09:26
 */
public class Junit1 {

    public static void main(String[] args) {

        String siteTimeZoneId = "Europe/Oslo";
        for (int i = 1; i < 13; i++) {
            DateTime now1 = new DateTime(2023, i, 1, 0, 0, 0, DateTimeZone.forID(siteTimeZoneId));
            System.out.println(now1);
           // System.out.println(SchneiderDateTimeUtils.getMonthBeginEndDtMap(now1));
        }
    }

}
