package com.nad.service;

import com.nad.model.stock.StockInfo;
import com.nad.model.stock.StockPrice;

import java.util.List;
import java.util.Map;

/**
 * @author duynguyen
 */
public interface CacheService {

    List<StockInfo> getStockList();

    StockPrice getStockPrice(String symbol);

    Map<String, StockPrice> getStockPrice(List<String> symbols);
}
