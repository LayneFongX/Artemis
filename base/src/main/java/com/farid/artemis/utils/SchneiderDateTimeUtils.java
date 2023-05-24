package com.farid.artemis.utils;


import com.farid.artemis.domain.biz.timer.TimeZoneIdDifferTimeVO;
import com.farid.artemis.enums.TimeTypeEnum;
import com.farid.artemis.enums.eliq.EliqDateTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @Author Banchao
 * @Date 2022/5/30 14:45
 */
@Slf4j
public class SchneiderDateTimeUtils {
    public static final DateTimeFormatter YYYY_FORMATTER = DateTimeFormat.forPattern("yyyy");

    public static final DateTimeFormatter YYYY_MM_FORMATTER = DateTimeFormat.forPattern("yyyyMM");

    public static final DateTimeFormatter YYYY_MM_DD_FORMATTER = DateTimeFormat.forPattern("yyyyMMdd");

    public static final DateTimeFormatter YYYY_MM_DD_HH_FORMATTER = DateTimeFormat.forPattern("yyyyMMddHH");

    public final static DateTimeFormatter YYYY_MM_DD_HH_MM_SS_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static final String YYYY_PATTERN = "yyyy";

    public static final String YYYYMM_PATTERN = "yyyyMM";

    public static final String YYYY_MM_PATTERN = "yyyy-MM";

    public static final String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";

    public static final String YYYYMMDD_PATTERN = "yyyyMMdd";

    public final static String YYYYMMDDHH_PATTERN = "yyyyMMddHH";

    public static final String YYYY_MM_DD_HH_MM_SS_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYY_MM_DD_T_HH_MM_SS_Z_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String START_HOUR = "00";

    public static final String END_HOUR = "23";

    public final static String HOUR_MINUTES_00_00 = "00:00";

    public final static String HOUR_MINUTES_24_00 = "24:00";

    /**
     * 获取格式化后的日期
     *
     * @param timeZoneId
     * @param pattern
     * @return
     */
    public static String getFormatDt(String timeZoneId, String pattern) {
        DateTime now = DateTime.now(DateTimeZone.forID(timeZoneId));
        return now.toString(pattern);
    }

    /**
     * 判断是否是最近三天
     *
     * @param queryTime
     * @param timeZoneId
     * @return
     */
    public static boolean isInLastThreeDays(String queryTime, String timeZoneId) {
        return isToday(queryTime, timeZoneId) || isYesterday(queryTime, timeZoneId) || isTheDayBeforeYesterday(queryTime, timeZoneId);
    }

    /**
     * 判断是否是今天
     *
     * @param queryTime
     * @param timeZoneId
     * @return
     */
    public static boolean isToday(String queryTime, String timeZoneId) {
        return StringUtils.equals(queryTime, getTodayDateTime(timeZoneId).toString(YYYY_MM_DD_FORMATTER));
    }

    /**
     * 判断是否是昨天
     *
     * @param queryTime
     * @param timeZoneId
     * @return
     */
    public static boolean isYesterday(String queryTime, String timeZoneId) {
        return StringUtils.equals(queryTime, getTodayDateTime(timeZoneId).plusDays(-1).toString(YYYY_MM_DD_FORMATTER));
    }

    /**
     * 判断是否是前天
     *
     * @param queryTime
     * @param timeZoneId
     * @return
     */
    public static boolean isTheDayBeforeYesterday(String queryTime, String timeZoneId) {
        return StringUtils.equals(queryTime, getTodayDateTime(timeZoneId).plusDays(-2).toString(YYYY_MM_DD_FORMATTER));
    }

    /**
     * 获取今天日期
     *
     * @param timeZoneId
     * @return
     */
    public static DateTime getTodayDateTime(String timeZoneId) {
        return DateTime.now(DateTimeZone.forID(timeZoneId));
    }

    /**
     * 获取今天日期
     *
     * @param timeZoneId
     * @return
     */
    public static String getTodayYYYYMMDDHH(String timeZoneId) {
        return getTodayDateTime(timeZoneId).toString(YYYY_MM_DD_HH_FORMATTER);
    }

    /**
     * 获取今天日期
     *
     * @param timeZoneId
     * @return
     */
    public static String getTodayYYYYMMDD(String timeZoneId) {
        return getTodayDateTime(timeZoneId).toString(YYYY_MM_DD_FORMATTER);
    }

    /**
     * 获取昨天日期
     *
     * @param timeZoneId
     * @return
     */
    public static DateTime getYesterdayDateTime(String timeZoneId) {
        return getTodayDateTime(timeZoneId).plusDays(-1);
    }

    /**
     * 获取昨天日期
     *
     * @param timeZoneId
     * @return
     */
    public static String getYesterdayYYYYMMDD(String timeZoneId) {
        return getTodayDateTime(timeZoneId).plusDays(-1).toString(YYYY_MM_DD_FORMATTER);
    }

    /**
     * 获取前天日期
     *
     * @param timeZoneId
     * @return
     */
    public static DateTime getTheDayBeforeYesterdayDateTime(String timeZoneId) {
        return getTodayDateTime(timeZoneId).plusDays(-2);
    }

    /**
     * 获取前天日期
     *
     * @param timeZoneId
     * @return
     */
    public static String getTheDayBeforeYesterdayYYYYMMDD(String timeZoneId) {
        return getTodayDateTime(timeZoneId).plusDays(-2).toString(YYYY_MM_DD_FORMATTER);
    }

    /**
     * 获取当前月
     *
     * @param timeZoneId
     * @return
     */
    public static String getThisMonthYYYYMM(String timeZoneId) {
        return getTodayDateTime(timeZoneId).toString(YYYY_MM_FORMATTER);
    }

    /**
     * 判断是否包含最近三天
     *
     * @param timeType   查询的时间范围类型
     * @param queryTime  查询的时间点
     * @param timeZoneId 当前设备时区ID
     * @return boolean
     */
    public static boolean isContainsLastThreeDays(TimeTypeEnum timeType, String queryTime, String timeZoneId) {
        return isContainToday(timeType, queryTime, timeZoneId) || isContainYesterday(timeType, queryTime, timeZoneId) ||
                isContainTheDayBeforeYesterday(timeType, queryTime, timeZoneId);
    }

    /**
     * 查询的时间是否包含今天
     *
     * @param timeType   查询的时间范围类型
     * @param queryTime  查询的时间点
     * @param timeZoneId 当前设备时区ID
     * @return boolean
     */
    public static boolean isContainToday(TimeTypeEnum timeType, String queryTime, String timeZoneId) {
        return isContainDay(timeType, queryTime, getTodayDateTime(timeZoneId));
    }

    /**
     * 查询的时间是否包含昨天
     *
     * @param timeType   查询的时间范围类型
     * @param queryTime  查询的时间点
     * @param timeZoneId 当前设备时区ID
     * @return boolean
     */
    public static boolean isContainYesterday(TimeTypeEnum timeType, String queryTime, String timeZoneId) {
        return isContainDay(timeType, queryTime, getYesterdayDateTime(timeZoneId));
    }

    /**
     * 查询的时间是否包含前天
     *
     * @param timeType   查询的时间范围类型
     * @param queryTime  查询的时间点
     * @param timeZoneId 当前设备时区ID
     * @return boolean
     */
    public static boolean isContainTheDayBeforeYesterday(TimeTypeEnum timeType, String queryTime, String timeZoneId) {
        return isContainDay(timeType, queryTime, getTheDayBeforeYesterdayDateTime(timeZoneId));
    }

    /**
     * 查询时间是否包含当前时间
     *
     * @param timeType  查询的时间范围类型
     * @param queryTime 查询的时间点
     * @param dateTime  当前时间
     * @return boolean
     */
    public static boolean isContainDay(TimeTypeEnum timeType, String queryTime, DateTime dateTime) {
        if (Objects.equals(timeType.getCode(), TimeTypeEnum.DAY.getCode())) {
            return StringUtils.equals(queryTime, dateTime.toString(YYYY_MM_DD_FORMATTER));
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.WEEK.getCode())) {
            String[] beginEndDay = getWeekDate(queryTime);
            return NumberUtils.toLong(beginEndDay[0]) <= NumberUtils.toLong(dateTime.toString(YYYY_MM_DD_FORMATTER)) &&
                    NumberUtils.toLong(beginEndDay[1]) >= NumberUtils.toLong(dateTime.toString(YYYY_MM_DD_FORMATTER));
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.MONTH.getCode())) {
            return StringUtils.equals(queryTime, dateTime.toString(YYYY_MM_FORMATTER));
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.YEAR.getCode())) {
            return StringUtils.equals(queryTime, dateTime.toString(YYYY_FORMATTER));
        }

        return Boolean.FALSE;
    }

    /**
     * 判断是否属于当前年
     *
     * @param yearTime
     * @param timeZoneId
     * @return
     */
    public static boolean isInThisYear(String yearTime, String timeZoneId) {
        return StringUtils.equals(yearTime, getTodayDateTime(timeZoneId).toString(YYYY_FORMATTER));
    }

    /**
     * 根据查询时间类型,获取要查询的时间列表
     *
     * @param timeType   查询维度
     * @param queryTime  查询时间
     * @param timeZoneId 时区ID
     * @return List<String>
     */
    public static List<String> getTimeTypeDtList(TimeTypeEnum timeType, String queryTime, String timeZoneId) {
        if (Objects.equals(timeType.getCode(), TimeTypeEnum.DAY.getCode())) {
            return getHoursOfDay(queryTime, timeZoneId);
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.WEEK.getCode())) {
            return getDaysOfWeek(queryTime, timeZoneId);
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.MONTH.getCode())) {
            return getDaysOfMonth(queryTime, timeZoneId);
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.YEAR.getCode())) {
            return getDaysOfYear(queryTime, timeZoneId);
        }
        return Collections.emptyList();
    }

    /**
     * 获取最近三天Dt
     *
     * @param timeZoneId
     * @return
     */
    public static List<String> getLastThreeDays(String timeZoneId) {
        String theDayBeforeYesterday = SchneiderDateTimeUtils.getTheDayBeforeYesterdayYYYYMMDD(timeZoneId);
        String yesterday = SchneiderDateTimeUtils.getYesterdayYYYYMMDD(timeZoneId);
        String today = SchneiderDateTimeUtils.getTodayYYYYMMDD(timeZoneId);
        return Arrays.asList(today, yesterday, theDayBeforeYesterday);
    }

    /**
     * 获取小时列表
     *
     * @param dayTime
     * @param timeZoneId
     * @return
     */
    public static List<String> getHoursOfDay(String dayTime, String timeZoneId) {
        DateTime day = DateTime.parse(dayTime, YYYY_MM_DD_FORMATTER).hourOfDay().withMinimumValue();
        DateTime endDay = DateTime.parse(dayTime, YYYY_MM_DD_FORMATTER).plusHours(24);
        DateTime nowDay = DateTime.parse(getTodayDateTime(timeZoneId).toString(YYYY_MM_DD_HH_FORMATTER), YYYY_MM_DD_HH_FORMATTER);
        List<String> hours = new LinkedList<>();
        while (nowDay.isAfter(day) && endDay.isAfter(day)) {
            hours.add(day.toString(YYYY_MM_DD_HH_FORMATTER));
            day = day.plusHours(1);
        }
        return hours;
    }

    /**
     * 获取周中的天列表
     *
     * @param weekTime
     * @param timeZoneId
     * @return
     */
    public static List<String> getDaysOfWeek(String weekTime, String timeZoneId) {
        DateTime weekTimeDt = DateTime.parse(weekTime, YYYY_MM_DD_FORMATTER).withZone(DateTimeZone.forID(timeZoneId));
        DateTime weekDayDt = weekTimeDt.plusDays(-(weekTimeDt.getDayOfWeek() - 1));
        List<String> weekDayDts = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDayDts.add(weekDayDt.toString(YYYYMMDD_PATTERN));
            weekDayDt = weekDayDt.plusDays(1);
        }
        return weekDayDts;
    }


    /**
     * 获取月中的天列表
     *
     * @param monthTime
     * @param timeZoneId
     * @return
     */
    public static List<String> getDaysOfMonth(String monthTime, String timeZoneId) {
        DateTime nowDay = getTodayDateTime(timeZoneId);
        List<String> days = new LinkedList<>();
        DateTime day = DateTime.parse(monthTime, YYYY_MM_FORMATTER).dayOfMonth().withMinimumValue();
        DateTime endDay = DateTime.parse(monthTime, YYYY_MM_FORMATTER).plusMonths(1).dayOfMonth().withMinimumValue();
        while ((nowDay.isAfter(day) && day.isBefore(endDay)) || day.equals(nowDay)) {
            days.add(day.toString(YYYY_MM_DD_FORMATTER));
            day = day.plusDays(1);
        }
        return days;
    }

    /**
     * 获取指定年的每一天
     *
     * @param time
     * @return
     */
    public static List<String> getDaysOfYear(String time, String timeZoneId) {
        DateTime nowDay = getTodayDateTime(timeZoneId);
        List<String> days = new LinkedList<>();
        DateTime day = DateTime.parse(time, YYYY_FORMATTER).dayOfYear().withMinimumValue();
        DateTime endDay = DateTime.parse(time, YYYY_FORMATTER).plusYears(1).dayOfYear().withMinimumValue();
        while ((nowDay.isAfter(day) && day.isBefore(endDay)) || day.equals(nowDay)) {
            days.add(day.toString(YYYY_MM_DD_FORMATTER));
            day = day.plusDays(1);
        }
        return days;
    }


    /**
     * 获取查询时间列表(不包含最近三天)
     *
     * @param timeType   查询维度
     * @param queryTime  查询时间
     * @param timeZoneId 时区ID
     * @return List<String>
     */
    public static List<String> getSearchDtList(TimeTypeEnum timeType, String queryTime, String timeZoneId) {
        // 获取查询时间列表
        List<String> dtList = getTimeTypeDtList(timeType, queryTime, timeZoneId);
        if (Objects.equals(timeType.getCode(), TimeTypeEnum.DAY.getCode()) && isContainsLastThreeDays(timeType, queryTime, timeZoneId)) {
            return Collections.emptyList();
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.WEEK.getCode()) && isContainsLastThreeDays(timeType, queryTime, timeZoneId)) {
            removeLastThreeDayFromDtList(timeZoneId, dtList);
        }

        if (Objects.equals(timeType.getCode(), TimeTypeEnum.MONTH.getCode()) && isContainsLastThreeDays(timeType, queryTime, timeZoneId)) {
            removeLastThreeDayFromDtList(timeZoneId, dtList);
        }
        return dtList;
    }

    /**
     * 从Dt中移除最近三天数据
     *
     * @param timeZoneId
     * @param dtList
     */
    public static void removeLastThreeDayFromDtList(String timeZoneId, List<String> dtList) {
        // 拿到昨天和今天的日期
        String today = getTodayYYYYMMDD(timeZoneId);
        String yesterday = getYesterdayYYYYMMDD(timeZoneId);
        String theDayBeforeYesterday = getTheDayBeforeYesterdayYYYYMMDD(timeZoneId);

        // 时间列表有昨天或者今天则remove
        dtList.removeIf(dt -> Objects.equals(dt, theDayBeforeYesterday) || Objects.equals(dt, yesterday) || Objects.equals(dt, today));
    }


    public static String[] getWeekDate(String dt) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD_PATTERN);

        GregorianCalendar calendar = GregorianCalendar.from(ZonedDateTime.of(Integer.parseInt(dt.substring(0, 4))
                , Integer.parseInt(dt.substring(4, 6)), Integer.parseInt(dt.substring(6, 8)), 0, 0, 0, 0, ZoneId.systemDefault()));

        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        // 获得当前日期是一个星期的第几天
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == Calendar.SUNDAY) {
            // 原本的周日=1，现在=7
            calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - 8);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        } else {
            calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        }

        String weekBegin = format.format(calendar.getTime());
        calendar.add(Calendar.DATE, 4 + calendar.getFirstDayOfWeek());

        String weekEnd = format.format(calendar.getTime());
        return new String[] {weekBegin, weekEnd};
    }

    /**
     * 获取当天的小时列表
     *
     * @param dateTime
     * @return
     */
    public static List<String> getHoursByDayTime(DateTime dateTime) {
        String yyyyMMDD = dateTime.toString(YYYYMMDD_PATTERN);

        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hours.add(yyyyMMDD + (i < 10 ? "0" + i : i));
        }
        return hours;
    }

    /**
     * 获取当月的天列表
     *
     * @param dateTime
     * @return
     */
    public static List<String> getDaysByDayTime(DateTime dateTime) {
        String yyyyMM = dateTime.toString(YYYYMM_PATTERN);
        int maxDay = dateTime.dayOfMonth().withMaximumValue().getDayOfMonth();
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= maxDay; i++) {
            days.add(yyyyMM + (i < 10 ? "0" + i : i));
        }
        return days;
    }

    public static String getHourMinutes(DateTime dateTime) {
        return String.format("%02d", dateTime.getHourOfDay()) + ":" + String.format("%02d", dateTime.getMinuteOfHour());
    }

    public static TimeZoneIdDifferTimeVO getDifferWithTimeZoneId(String timeZoneId1, String timeZoneId2) {
        long currentTimeMillis = System.currentTimeMillis();
        DateTime timeZone1Dt =
                DateTime.parse(new DateTime(currentTimeMillis, DateTimeZone.forID(timeZoneId1)).toString(YYYY_MM_DD_HH_MM_SS_PATTERN),
                        YYYY_MM_DD_HH_MM_SS_FORMATTER);
        DateTime timeZone2Dt =
                DateTime.parse(new DateTime(currentTimeMillis, DateTimeZone.forID(timeZoneId2)).toString(YYYY_MM_DD_HH_MM_SS_PATTERN),
                        YYYY_MM_DD_HH_MM_SS_FORMATTER);

        long timeZone1TimeMillis = timeZone1Dt.getMillis();
        long timeZone2TimeMillis = timeZone2Dt.getMillis();


        long differTimeSeconds = (timeZone1TimeMillis - timeZone2TimeMillis) / 1000;
        return TimeZoneIdDifferTimeVO.builder().differHour((int) (differTimeSeconds / 3600))
                .differMinute((int) (differTimeSeconds % 3600) / 60).build();
    }

    /**
     * 获取指定时区对应UTC时区的月开始时间
     *
     * @param siteTimeZoneId
     * @return
     */
    public static DateTime getMonthBeginDt(String siteTimeZoneId) {
        // 获取家庭时区对应的开始时间
        DateTime now = DateTime.now(DateTimeZone.forID(siteTimeZoneId));
        DateTime fromDt = now.withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        // 获取家庭时区时间对应的UTC时间
        TimeZoneIdDifferTimeVO differTimeVO = getDifferWithTimeZoneId(siteTimeZoneId, "UTC");
        int differHour = differTimeVO.getDifferHour();
        int differMinute = differTimeVO.getDifferMinute();
        fromDt = fromDt.plusHours(-differHour).plusMinutes(-differMinute);
        return fromDt;
    }

    public static DateTime getYearBeginDt(String siteTimeZoneId) {
        // 获取家庭时区对应的开始时间
        DateTime now = DateTime.now(DateTimeZone.forID(siteTimeZoneId));
        DateTime fromDt =
                now.withMonthOfYear(1).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        // 获取家庭时区时间对应的UTC时间
        TimeZoneIdDifferTimeVO differTimeVO = getDifferWithTimeZoneId(siteTimeZoneId, "UTC");
        int differHour = differTimeVO.getDifferHour();
        int differMinute = differTimeVO.getDifferMinute();
        fromDt = fromDt.plusHours(-differHour).plusMinutes(-differMinute);
        return fromDt;
    }

    /**
     * 生成首次重试时间
     *
     * @param siteTimeZoneId
     * @return
     */
    public static DateTime getEndTime(String siteTimeZoneId) {
        DateTime currentDt = new DateTime(System.currentTimeMillis(), DateTimeZone.forID(siteTimeZoneId));
        return new DateTime(currentDt.getYear(), currentDt.getMonthOfYear(), currentDt.getDayOfMonth(), 23, 0, 0);
    }

    /**
     * 获取指定时区对应UTC时区的月开始时间
     *
     * @param siteTimeZoneId
     * @return
     */
    public static Map<String, String> getMonthBeginEndDtMap(String siteTimeZoneId) {
        Map<String, String> monthBeignEndDtMap = new HashMap<>();

        DateTime now = DateTime.now(DateTimeZone.forID(siteTimeZoneId));
        monthBeignEndDtMap.put("date", String.valueOf(now.getMonthOfYear()));

        String periodLabel = now.toString(YYYY_MM_PATTERN);
        monthBeignEndDtMap.put("periodLabel", periodLabel);

        DateTime startDateTime = now.plusMillis(-now.getMillisOfDay()).plusDays(-now.getDayOfMonth() + 1).withZone(DateTimeZone.UTC);
        String from = startDateTime.toString(YYYY_MM_DD_T_HH_MM_SS_Z_PATTERN);
        monthBeignEndDtMap.put("from", from);

        DateTime endDateTime = now.dayOfMonth().withMaximumValue()
                .withHourOfDay(startDateTime.getHourOfDay())
                .withMinuteOfHour(startDateTime.getMinuteOfHour())
                .withSecondOfMinute(startDateTime.getSecondOfMinute())
                .withMillisOfSecond(startDateTime.getMillisOfSecond());
        String to = endDateTime.toString(YYYY_MM_DD_T_HH_MM_SS_Z_PATTERN);
        monthBeignEndDtMap.put("to", to);

        monthBeignEndDtMap.put("queryType", EliqDateTypeEnum.MONTH.getGroupBy());

        return monthBeignEndDtMap;
    }

    /**
     * 获取指定时区对应UTC时区的年开始时间
     *
     * @param siteTimeZoneId
     * @return
     */
    public static Map<String, String> getYearBeginEndDtMap(String siteTimeZoneId) {
        Map<String, String> yearBeignEndDtMap = new HashMap<>();

        DateTime now = DateTime.now(DateTimeZone.forID(siteTimeZoneId));
        yearBeignEndDtMap.put("date", String.valueOf(now.getYear()));

        String periodLabel = now.toString(YYYY_PATTERN);
        yearBeignEndDtMap.put("periodLabel", periodLabel);

        DateTime startDateTime = now.plusMillis(-now.getMillisOfDay()).plusDays(-now.getDayOfMonth() + 1)
                .plusMonths(-now.getMonthOfYear() + 1).withZone(DateTimeZone.UTC);
        String from = startDateTime.toString(YYYY_MM_DD_T_HH_MM_SS_Z_PATTERN);
        yearBeignEndDtMap.put("from", from);

        DateTime endDateTime = startDateTime.plusYears(1);
        String to = endDateTime.toString(YYYY_MM_DD_T_HH_MM_SS_Z_PATTERN);
        yearBeignEndDtMap.put("to", to);

        yearBeignEndDtMap.put("queryType", EliqDateTypeEnum.YEAR.getGroupBy());
        return yearBeignEndDtMap;
    }

}
