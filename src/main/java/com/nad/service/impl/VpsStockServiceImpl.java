package com.nad.service.impl;

import com.nad.annotation.TryCatchWrap;
import com.nad.model.response.external.ExternalStockInfo;
import com.nad.model.response.external.ExternalStockPrice;
import com.nad.service.ExternalStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author duynguyen
 */
@Service
@AllArgsConstructor
public class VpsStockServiceImpl implements ExternalStockService {

    private static final String BASE_URL = "https://bgapidatafeed.vps.com.vn";
    private static final String FETCH_STOCK_LIST_PATH = "getlistallstock";
    private static final String GET_STOCK_PRICE_PATH = "getliststockdata";
    private static final String SLASH = "/";

    private final RestTemplate restTemplate;

    @Override
    @TryCatchWrap
    public List<ExternalStockInfo> fetchStockList() {
        ResponseEntity<ExternalStockInfo[]> response = restTemplate.getForEntity(
                String.join(SLASH, BASE_URL, FETCH_STOCK_LIST_PATH),
                ExternalStockInfo[].class
        );
        ExternalStockInfo[] stockList = response.getBody();
        return Arrays.asList(stockList);
    }

    @Override
    @TryCatchWrap
    public List<ExternalStockPrice> getStockPrice(String symbols) {
        ResponseEntity<ExternalStockPrice[]> response = restTemplate.getForEntity(
                String.join(SLASH, BASE_URL, GET_STOCK_PRICE_PATH, symbols),
                ExternalStockPrice[].class
        );
        ExternalStockPrice[] priceList = response.getBody();
        return Arrays.asList(priceList);
    }
}
