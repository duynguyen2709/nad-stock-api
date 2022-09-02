package com.nad.model.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.nad.model.stock.StockPrice;
import com.nad.service.ExternalStockService;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author duynguyen
 **/
@AllArgsConstructor
public class StockPriceCacheLoader implements CacheLoader<String, StockPrice> {
    private final ExternalStockService externalStockService;

    @Override
    public @Nullable StockPrice load(String key) throws Exception {
        return this.loadAll(Set.of(key)).get(key);
    }

    @Override
    public Map<? extends String, ? extends StockPrice> loadAll(Set<? extends String> keys) throws Exception {
        return externalStockService.getStockPrice(String.join(",", keys))
                .stream()
                .map(StockPrice::new)
                .collect(Collectors.toMap(StockPrice::getSymbol, ele -> ele));
    }
}
