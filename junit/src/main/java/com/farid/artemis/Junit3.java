package com.farid.artemis;

import com.farid.artemis.constant.SchneiderEliqConstant;
import com.farid.artemis.domain.biz.timer.SolutionTimerBizVO;
import com.farid.artemis.domain.biz.timer.TimeZoneIdDifferTimeVO;
import com.farid.artemis.proxy.SchneiderDateTimeUtils;
import com.farid.artemis.proxy.TimerUtils;
import org.joda.time.DateTime;

import java.util.Objects;

/**
 * @Author Banchao
 * @Date 2023/5/23 18:00
 */
public class Junit3 {

    private final static String SPLIT_SYSBOL_1 = ":";

    private final static char CHAR_0 = '0';

    public static void main(String[] args) {
        // String loopTimerBizVOJson = "{\"ext\":\"{\\\"actions\\\":{\\\"101\\\":\\\"ON\\\"},\\\"enableNotify\\\":true}\",\"note\":\"Fri\",\"gmtModified\":1685072974674,\"bizType\":303478,\"isLocalTimer\":false,\"timerStatus\":2,\"resType\":1,\"timeZoneId\":\"Asia/Shanghai\",\"loops\":\"0000010\",\"resId\":\"vdevo168498543838310\",\"localTime\":42600,\"uid\":\"ay1666340943028jaU54\",\"bizId\":\"oyEFmm0vWe7MSEMqLiLAy30ci08hhe\",\"localDate\":\"\",\"category\":\"schneider_pte_14A_loops_timer\"}";
        // LoopTimerVO loopTimerVO = JSON.parseObject(loopTimerBizVOJson,LoopTimerVO.class);
        // SolutionTimerBizVO solutionTimerBizVO = OrikaUtil.convert(loopTimerVO, SolutionTimerBizVO.class);
        // covertTimerLoopsByTimeZoneId(solutionTimerBizVO, timeZoneId);
    }

    private static void covertTimerLoopsByTimeZoneId(SolutionTimerBizVO solutionTimerBizVO, String timeZoneId) {
        String timeZoneIdLoops = "0000000";

        String timerTimeZoneId = solutionTimerBizVO.getTimeZoneId();
        String timerLoops = solutionTimerBizVO.getLoops();
        String startTime = TimerUtils.getHHmmBySeconds(solutionTimerBizVO.getLocalTime());

        TimeZoneIdDifferTimeVO differVO = SchneiderDateTimeUtils.getDifferWithTimeZoneId(timeZoneId, timerTimeZoneId);
        int plusHour = differVO.getDifferHour();
        int plusMinute = differVO.getDifferMinute();

        String[] startTimeInfo = startTime.split(SPLIT_SYSBOL_1);
        int startHour = Integer.parseInt(startTimeInfo[0]);
        int startMinute = Integer.parseInt(startTimeInfo[1]);

        char[] weekDaysCharArray = timerLoops.toCharArray();
        char[] weekDaysCharArrayWithTimeZoneId = timeZoneIdLoops.toCharArray();

        for (int i = 0; i < weekDaysCharArray.length; i++) {
            char weekChar = weekDaysCharArray[i];
            if (Objects.equals(weekChar, CHAR_0)) {
                continue;
            }
            DateTime weekDayTime = SchneiderEliqConstant.WEEK_DATETIME_LIST.get(i + 1);
            DateTime weekDayStartDateTime = weekDayTime.plusHours(startHour + plusHour).plusMinutes(startMinute + plusMinute);

            upgradeLoops(weekDayStartDateTime, weekDaysCharArrayWithTimeZoneId);
            solutionTimerBizVO.setStartTime(SchneiderDateTimeUtils.getHourMinutes(weekDayStartDateTime));
            solutionTimerBizVO.setLocalTime(TimerUtils.getCurrentSeconds(solutionTimerBizVO.getStartTime()));
        }
        solutionTimerBizVO.setLoops(String.valueOf(weekDaysCharArrayWithTimeZoneId));
        solutionTimerBizVO.setTimeZoneId(timeZoneId);

    }

    private static void upgradeLoops(DateTime dateTime, char[] weekDaysCharArray) {
        int timeZoneIdDay = dateTime.getDayOfMonth();
        if (Objects.equals(timeZoneIdDay, SchneiderEliqConstant.NEXT_WEEK_SUNDAY_DATETIME.getDayOfMonth())) {
            weekDaysCharArray[0] = 49;
            return;
        }

        if (Objects.equals(timeZoneIdDay, SchneiderEliqConstant.LAST_WEEK_SATURDAY_DATETIME.getDayOfMonth())) {
            weekDaysCharArray[6] = 49;
            return;
        }

        for (int i = 0; i < SchneiderEliqConstant.WEEK_DATETIME_LIST.size(); i++) {
            int weekDay = SchneiderEliqConstant.WEEK_DATETIME_LIST.get(i).getDayOfMonth();
            if (!Objects.equals(timeZoneIdDay, weekDay)) {
                continue;
            }
            weekDaysCharArray[i - 1] = 49;
        }
    }

}
