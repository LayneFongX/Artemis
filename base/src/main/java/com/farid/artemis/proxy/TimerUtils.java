package com.farid.artemis.proxy;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Legal[guo.li@tuya.com]
 * @date 2021/2/23
 */
@Slf4j
public class TimerUtils {


    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DATE_FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static final Integer MINUTE_SECONDS = 60;
    private static final Integer HOUR_MINUTE = 60;
    private static final String PRE_HOT_TASK = "preHotTask";
    private static final String ZONED_TIME_STR = "%s 00:00:00";
    private static final String FORMATTER1 = "yyyyMMdd HH:mm:ss";
    public static final String FORMATTER2 = "yyyyMMdd HH:mm";
    public static final String FORMATTER3 = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";


    /**
     * 计算从午夜0:00开始的分钟数
     *
     * @param time 时间，例如 05:20
     * @return 从午夜0:00开始的分钟数
     */
    public static long getCurrentMinutes(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * HOUR_MINUTE + Integer.parseInt(times[1]);
    }

    /**
     * 计算从午夜0:00开始的秒数
     *
     * @param time 时间，例如 05:20
     * @return 从午夜0:00开始的秒数
     */
    public static long getCurrentSeconds(String time) {
        return MINUTE_SECONDS * getCurrentMinutes(time);
    }


    /**
     * 功能描述：返回秒
     *
     * @param date 日期
     * @return 返回秒
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    public static long getMinutesByDate(Date date) {
        int hour = getHour(date);
        int minutes = getMinute(date);
        return hour * HOUR_MINUTE + minutes;
    }

    public static long getMinutesByDateTimeZone(Date date, String timeZoneId) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        int minutes = calendar.get(Calendar.MINUTE);
        return hour * HOUR_MINUTE + minutes;
    }

    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前时间,格式为 HH:mm
     *
     * @return 例如 05:30
     */
    public static String getCurrentHHmm(String timeZoneId) {
        ZonedDateTime dataTime = ZonedDateTime.now(ZoneId.of(timeZoneId));
        return TIME_FORMATTER.format(dataTime);
    }

    /**
     * 获取当前的日期,格式为 yyyyMMdd
     *
     * @param timeZoneId 时区
     * @return currentDate
     */
    public static String getCurrentDate(String timeZoneId) {
        ZonedDateTime dataTime = ZonedDateTime.now(ZoneId.of(timeZoneId));
        return DATE_FORMATTER.format(dataTime);
    }

    public static String getDateBefore2Days(String timeZoneId) {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(timeZoneId));
        String formatDt = dateTime.plusDays(-2).format(DATE_FORMATTER);
        return formatDt;
    }

    public static String getSystemCurrentDate(String timezoneId) {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(timezoneId));
        String formatDt = dateTime.format(DATE_FORMATTER);
        return formatDt;
    }

    /**
     * 转换格式 如 "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getCurrentTimeFormat(String deviceTimeZoneId) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(deviceTimeZoneId));
        String result = now.format(DATE_TIME_FORMATTER);
        return result;
    }


    public static void main(String[] args) {
        String dateBefore2Days = getCurrentTimeFormat("Europe/Madrid");
        System.out.println(dateBefore2Days);
    }


    /**
     * 根据date和timeZoneId,构建 zonedDateTime
     *
     * @param date       例如:20210308
     * @param timeZoneId 时区
     * @return {@link ZonedDateTime}
     */
    public static ZonedDateTime buildZonedDateTime(String date, String timeZoneId) {
        // 转为当地时区时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER1).withZone(ZoneId.of(timeZoneId));
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(String.format(ZONED_TIME_STR, date), formatter);
        return zonedDateTime;
    }


    /**
     * 根据date,time,timeZoneId 获取时间戳
     *
     * @param date       yyyyMMdd 例如:20210328
     * @param time       HH:mm 例如 10:30
     * @param timeZoneId 时区
     * @return timestamp(秒 ）
     */
    public static long getTimerStamp(String date, String time, String timeZoneId) {
        StringBuilder sb = new StringBuilder();
        sb.append(date).append(" ").append(time);
        // 转为当地时区时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER2).withZone(ZoneId.of(timeZoneId));
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(sb.toString(), formatter);
        return zonedDateTime.toInstant().getEpochSecond();
    }

    /**
     * 根据date,time,timeZoneId 获取时间戳
     *
     * @param date       yyyy-mm-dd 例如:2021-03-28
     * @param time       HH:mm 例如 10:30
     * @param timeZoneId 时区
     * @return timestamp(秒 ）
     */
    public static long getTimerStampByDash(String date, Long time, String timeZoneId) {
        StringBuilder sb = new StringBuilder();
        sb.append(date).append(" 00:00");
        // 转为当地时区时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER3).withZone(ZoneId.of(timeZoneId));
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(sb.toString(), formatter);
        return zonedDateTime.toInstant().getEpochSecond() + time;
    }


    /**
     * 根据时区获取当前的ts
     *
     * @param zoneId 时区
     * @return 当前的ts
     */
    public static long getCurTsByZoneId(String zoneId) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneId));
        return now.toInstant().getEpochSecond();
    }


    /**
     * 是否在改时间范围内
     *
     * @param startTimeSeconds 单位秒
     * @param endTimeSeconds   单位秒
     * @param timeZoneId
     * @return
     */
    public static boolean betweenPeriod(long startTimeSeconds, long endTimeSeconds, String timeZoneId, String loops) {


        //是否是当天
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        //获取loops
        String weekDay = loops.substring(i - 1, i);
        if (Integer.parseInt(weekDay) == 0) {
            return false;
        }


        String currentHHmm = getCurrentHHmm(timeZoneId);
        long currentSeconds = getCurrentSeconds(currentHHmm);
        log.info("betweenPeriod timeZoneId:{},startTime:{},endTime:{},currentSeconds:{}", timeZoneId, startTimeSeconds, endTimeSeconds, currentSeconds);
        if (startTimeSeconds <= currentSeconds && endTimeSeconds > currentSeconds) {
            return true;
        }
        return false;
    }

    /**
     * yyyyMMdd -> YY-MM-DD
     *
     * @param date
     * @return
     */
    public static String getDashDateString(String date) {
        Date parse = null;
        String dateString = "";
        try {
            parse = new SimpleDateFormat("yyyyMMdd").parse(date);
            dateString = new SimpleDateFormat("yyyy-MM-dd").format(parse);
        } catch (ParseException e) {
            log.warn("日期转换失败，dateTime=" + date, e);
            dateString = null;
        }
        return dateString;
    }

    /**
     * YY-MM-DD -> yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String getDateString(String date) {
        Date parse = null;
        String dateString = "";
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            dateString = new SimpleDateFormat("yyyyMMdd").format(parse);
        } catch (ParseException e) {
            log.warn("日期转换失败，dateTime=" + date, e);
            dateString = null;
        }
        return dateString;
    }

    /**
     * 转化当前的日期为yyyy-MM-dd
     *
     * @return yyyy-MM-dd
     */
    public static String formatCurrentDate2Hyphen(String timeZoneId) {
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of(timeZoneId));
        return formatDate2Hyphen(date);
    }

    /**
     * 将ZonedDateTime转成yyyy-MM-dd
     *
     * @param date {@link ZonedDateTime}
     * @return yyyy-MM-dd
     */
    public static String formatDate2Hyphen(ZonedDateTime date) {
        return DATE_FORMATTER_2.format(date);
    }

    /**
     * 根据date,time,timeZoneId 获取时间戳
     *
     * @param date       yyyyMMdd 例如:20210328
     * @param time       秒数 例如 58619
     * @param timeZoneId 时区
     * @return timestamp(秒 ）
     */
    public static long getTimestampByDate(String date, Long time, String timeZoneId) {
        // 转为当地时区时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER1).withZone(ZoneId.of(timeZoneId));
        // 转为当地时区时间
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + " " + getHHmmssBySeconds(time), formatter);
        return zonedDateTime.toInstant().getEpochSecond();
    }

    /**
     * 根据time获取HH:mm:ss
     *
     * @param time 秒数 例如 7199
     * @return HH:mm:ss
     */
    public static String getHHmmssBySeconds(long time) {
        long ss = time % 60;
        String ssStr = ss < 10 ? "0" + ss : String.valueOf(ss);
        return getHHmmBySeconds(time) + ":" + ssStr;
    }

    /**
     * 根据time获取HH:mm
     *
     * @param time 秒数 例如 7199
     * @return HH:mm
     */
    public static String getHHmmBySeconds(long time) {
        long hh = time / 3600;
        String hhStr = hh < 10 ? "0" + hh : String.valueOf(hh);
        long mm = time % 3600 / 60;
        String mmStr = mm < 10 ? "0" + mm : String.valueOf(mm);
        return hhStr + ":" + mmStr;
    }
}
