package com.nad.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.nad.config.AppConfig;
import com.nad.model.cache.StockPriceCacheLoader;
import com.nad.model.cache.StockPriceExpiry;
import com.nad.model.stock.StockInfo;
import com.nad.model.stock.StockPrice;
import com.nad.service.CacheService;
import com.nad.service.ExternalStockService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.nad.util.DateTimeUtil.calculateDurationToTime;

/**
 * @author duynguyen
 */
@Service
public class CacheServiceImpl implements CacheService {

    private final Cache<String, StockInfo> stockInfoCache;
    private final LoadingCache<String, StockPrice> stockPriceCache;
    private final ExternalStockService externalStockService;

    public CacheServiceImpl(AppConfig appConfig, ExternalStockService externalStockService) {
        this.externalStockService = externalStockService;
        this.stockInfoCache = Caffeine.newBuilder().build();
        this.stockPriceCache = Caffeine.newBuilder()
                .expireAfter(new StockPriceExpiry(appConfig))
                .build(new StockPriceCacheLoader(externalStockService));

        this.stockInfoCache.putAll(loadStockInfoList());
    }

    @PostConstruct
    void scheduleJob() {
        long initialDelay = calculateDurationToTime(9, 0, 0);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> this.stockInfoCache.putAll(loadStockInfoList()),
                initialDelay,
                TimeUnit.HOURS.toSeconds(12),
                TimeUnit.SECONDS);
    }

    @Override
    public List<StockInfo> getStockList() {
        return new ArrayList<>(stockInfoCache.asMap().values());
    }

    @Override
    public StockPrice getStockPrice(String symbol) {
        return stockPriceCache.get(symbol);
    }

    @Override
    public Map<String, StockPrice> getStockPrice(List<String> symbols) {
        return stockPriceCache.getAll(symbols);
    }

    private Map<String, StockInfo> loadStockInfoList() {
        return externalStockService.fetchStockList()
                .stream()
                .map(StockInfo::new)
                .collect(Collectors.toMap(StockInfo::getSymbol, ele -> ele));
    }
}
