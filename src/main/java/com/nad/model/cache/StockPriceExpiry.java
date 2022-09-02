package com.nad.model.cache;

import com.github.benmanes.caffeine.cache.Expiry;
import com.nad.config.AppConfig;
import com.nad.model.stock.StockPrice;
import com.nad.util.DateTimeUtil;
import com.nad.util.StockUtil;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.index.qual.NonNegative;

import java.util.concurrent.TimeUnit;


/**
 * @author duynguyen
 **/
@AllArgsConstructor
public class StockPriceExpiry implements Expiry<String, StockPrice> {
    private final AppConfig appConfig;

    @Override
    public long expireAfterCreate(String s, StockPrice stockPrice, long currentTime) {
        long ttl = StockUtil.isInTradingTime() ?
                appConfig.getStock().getPriceCacheTtl() :
                DateTimeUtil.calculateDurationToTime(9, 0, 0);
        return TimeUnit.SECONDS.toNanos(ttl);
    }
    @Override
    public long expireAfterUpdate(String s, StockPrice stockPrice, long currentTime, @NonNegative long currentDuration) {
        return currentDuration;
    }
    @Override
    public long expireAfterRead(String s, StockPrice stockPrice, long currentTime, @NonNegative long currentDuration) {
        return currentDuration;
    }
}
