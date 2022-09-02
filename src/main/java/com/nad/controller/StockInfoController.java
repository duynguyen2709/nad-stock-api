package com.nad.controller;

import com.nad.model.request.GetStockPriceRequest;
import com.nad.model.response.BaseResponse;
import com.nad.service.StockDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author duynguyen
 */
@RestController
@RequestMapping("/api/v1/stocks")
@AllArgsConstructor
public class StockInfoController {

    private final StockDataService stockListService;

    @GetMapping("")
    public BaseResponse getAll() {
        return BaseResponse.successData(stockListService.getStockList());
    }

    @GetMapping("/price")
    public BaseResponse getStockPrice(@RequestParam(name = "symbols") String symbols) {
        GetStockPriceRequest request = GetStockPriceRequest.builder().symbols(symbols).build();

        return Optional.ofNullable(request.validate())
                .map(BaseResponse::failed)
                .orElseGet(() -> BaseResponse.successData(stockListService.getStockPrice(request)));
    }
}
