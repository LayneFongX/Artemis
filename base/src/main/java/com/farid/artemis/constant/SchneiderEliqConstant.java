package com.farid.artemis.constant;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Banchao
 * @Date 2022/5/27 14:20
 */
public class SchneiderEliqConstant {

    public static final DateTime LAST_WEEK_SATURDAY_DATETIME = new DateTime(2022, 1, 1, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime SUNDAY_DATETIME = new DateTime(2022, 1, 2, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime MONDAY_DATETIME = new DateTime(2022, 1, 3, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime TUESDAY_DATETIME = new DateTime(2022, 1, 4, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime WEDNESDAY_DATETIME = new DateTime(2022, 1, 5, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime THURSDAY_DATETIME = new DateTime(2022, 1, 6, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime FRIDAY_DATETIME = new DateTime(2022, 1, 7, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime SATURDAY_DATETIME = new DateTime(2022, 1, 8, 0, 0, 0, DateTimeZone.UTC);

    public static final DateTime NEXT_WEEK_SUNDAY_DATETIME = new DateTime(2022, 1, 9, 0, 0, 0, DateTimeZone.UTC);

    public static final List<DateTime> WEEK_DATETIME_LIST =
            Arrays.asList(LAST_WEEK_SATURDAY_DATETIME, SUNDAY_DATETIME, MONDAY_DATETIME, TUESDAY_DATETIME,
                    WEDNESDAY_DATETIME, THURSDAY_DATETIME, FRIDAY_DATETIME, SATURDAY_DATETIME, NEXT_WEEK_SUNDAY_DATETIME);


}
