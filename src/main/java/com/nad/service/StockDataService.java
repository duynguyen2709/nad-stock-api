package com.nad.service;

import com.nad.model.request.GetStockPriceRequest;
import com.nad.model.stock.StockInfo;

import java.util.List;
import java.util.Map;

/**
 * @author duynguyen
 */
public interface StockDataService {

    List<StockInfo> getStockList();

    Map<String, Object> getStockPrice(GetStockPriceRequest request);
}
