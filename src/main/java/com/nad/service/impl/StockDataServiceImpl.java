package com.nad.service.impl;

import com.nad.constant.ErrorCode;
import com.nad.model.request.GetStockPriceRequest;
import com.nad.model.response.BaseResponse;
import com.nad.model.stock.StockInfo;
import com.nad.model.stock.StockPrice;
import com.nad.service.CacheService;
import com.nad.service.StockDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author duynguyen
 */
@Service
@AllArgsConstructor
public class StockDataServiceImpl implements StockDataService {

    private final CacheService cacheService;

    @Override
    public List<StockInfo> getStockList() {
        return cacheService.getStockList();
    }

    @Override
    public Map<String, Object> getStockPrice(GetStockPriceRequest request) {
        Map<String, Object> resultMap = getStockPriceAvailableInCache(request.getSymbols());
        Map<String, Object> invalidSymbols = getInvalidSymbols(request.getSymbols(), resultMap);
        resultMap.putAll(invalidSymbols);
        return resultMap;
    }

    private Map<String, Object> getStockPriceAvailableInCache(List<String> symbols) {
        Map<String, StockPrice> cacheMap = cacheService.getStockPrice(symbols);
        return symbols.stream()
                .map(cacheMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(StockPrice::getSymbol, ele -> ele));
    }

    private Map<String, Object> getInvalidSymbols(List<String> symbols, Map<String, Object> resultMap) {
        return symbols.stream()
                .filter(symbol -> !resultMap.containsKey(symbol))
                .collect(Collectors.toMap(symbol -> symbol, s -> BaseResponse.failed(ErrorCode.SYMBOL_NOT_FOUND)));
    }
}
