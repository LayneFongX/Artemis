package com.farid.artemis;

import com.farid.artemis.utils.SchneiderZonedDateTimeUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Banchao
 * @Date 2023/6/30 15:56
 */
public class Junit5 {

    public static void main(String[] args) {
        List<EliqScheduleCostBO> timeRanges = List.of(
                new EliqScheduleCostBO("2023-06-29T22:01:00Z", "2023-07-01T00:00:00Z")
        );

        String timeZoneId = "Europe/Stockholm";
        ZonedDateTime zoneTodayStartTime = LocalDateTime.now().atZone(ZoneId.of(timeZoneId)).toLocalDate().atStartOfDay(ZoneId.of(timeZoneId));
        long zoneTodayStartMilli = zoneTodayStartTime.toInstant().toEpochMilli();
        ZonedDateTime zoneTodayEndTime = zoneTodayStartTime.plusDays(1).minusSeconds(1);
        long zoneTodayEndMilli = zoneTodayEndTime.toInstant().toEpochMilli();

        List<EliqScheduleCostBO> todayScheduleCosts = new ArrayList<>();
        for (EliqScheduleCostBO scheduleCostBO : timeRanges) {
            ZonedDateTime zoneCostScheduleStartTime = SchneiderZonedDateTimeUtils.getTargetZonedDateTime(scheduleCostBO.getFrom(), timeZoneId);
            long zoneCostScheduleStartMilli = zoneCostScheduleStartTime.toInstant().toEpochMilli();

            ZonedDateTime zoneCostScheduleEndTime = SchneiderZonedDateTimeUtils.getTargetZonedDateTime(scheduleCostBO.getTo(), timeZoneId);
            long zoneCostScheduleEndMilli = zoneCostScheduleEndTime.toInstant().toEpochMilli();
            if (zoneCostScheduleEndMilli < zoneTodayStartMilli){
                continue;
            }

            if (zoneCostScheduleStartMilli > zoneTodayEndMilli){
                continue;
            }

            if (zoneCostScheduleStartMilli < zoneTodayStartMilli){
                zoneCostScheduleStartTime = zoneTodayStartTime;
            }

            if (zoneCostScheduleEndMilli > zoneTodayEndMilli){
                zoneCostScheduleEndTime = zoneTodayEndTime;
            }
            scheduleCostBO.setFrom(zoneCostScheduleStartTime.format(SchneiderZonedDateTimeUtils.HHMM));
            scheduleCostBO.setTo(zoneCostScheduleEndTime.format(SchneiderZonedDateTimeUtils.HHMM));
            todayScheduleCosts.add(scheduleCostBO);
        }
        System.out.println(todayScheduleCosts);
    }

    @Data
    static class EliqScheduleCostBO {
        String from;
        String to;

        EliqScheduleCostBO(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }

}
