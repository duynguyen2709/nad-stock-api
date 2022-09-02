package com.nad.service;

import com.nad.model.response.external.ExternalStockInfo;
import com.nad.model.response.external.ExternalStockPrice;

import java.util.List;

/**
 * @author duynguyen
 */
public interface ExternalStockService {

    List<ExternalStockInfo> fetchStockList();

    List<ExternalStockPrice> getStockPrice(String symbols);
}
