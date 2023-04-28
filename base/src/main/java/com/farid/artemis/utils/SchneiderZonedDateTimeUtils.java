package com.farid.artemis.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @Author Banchao
 * @Date 2022/5/30 14:45
 */
@Slf4j
public class SchneiderZonedDateTimeUtils {

    public final static DateTimeFormatter YYYYMMDDHH = DateTimeFormatter.ofPattern("yyyyMMddHH");

    public final static DateTimeFormatter YYYY_MM_DD_HH = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");

    public final static DateTimeFormatter YYYY_MM_DD_T_HH_MM_SS_Z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /**
     * UTC时间转换指定时区时间
     *
     * @param sourceUTCDateTime
     * @param targerTimeZoneId
     * @return
     */
    public static ZonedDateTime getTargetZonedDateTime(String sourceUTCDateTime, String targerTimeZoneId) {
        LocalDateTime UTCLocalDateTime = LocalDateTime.parse(sourceUTCDateTime, DateTimeFormatter.ISO_DATE_TIME);
        return ZonedDateTime.of(UTCLocalDateTime, ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of(targerTimeZoneId));
    }

    public static String getUTCDateTime(Long seconds) {
        Instant instant = Instant.ofEpochSecond(seconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return YYYY_MM_DD_T_HH_MM_SS_Z.format(localDateTime);
    }

}
