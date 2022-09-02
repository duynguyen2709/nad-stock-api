package com.nad.util;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author duynguyen
 **/
public class DateTimeUtil {

    public static long calculateDurationToTime(int hour, int minute, int second) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(second).withNano(0);
        if (now.compareTo(nextRun) > 0)
            nextRun = nextRun.plusDays(1);

        Duration duration = Duration.between(now, nextRun);
        return duration.getSeconds();
    }
}
