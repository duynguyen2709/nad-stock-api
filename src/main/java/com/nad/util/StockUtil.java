package com.nad.util;

import java.time.LocalDateTime;

/**
 * @author duynguyen
 */
public class StockUtil {

    public static boolean isInTradingTime() {
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime start = now.withHour(9);
        LocalDateTime end = now.withHour(15);
        return (!now.isBefore(start)) && (!now.isAfter(end));
    }
}
