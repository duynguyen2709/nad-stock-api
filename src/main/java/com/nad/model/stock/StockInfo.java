package com.nad.model.stock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nad.model.response.external.ExternalStockInfo;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author duynguyen
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockInfo implements Serializable {
    @JsonProperty("symbol")
    private final String symbol;
    @JsonProperty("name_vn")
    private final String nameVn;
    @JsonProperty("name_en")
    private final String nameEn;
    @JsonProperty("stock_exchange")
    private final String stockExchange;

    public StockInfo(ExternalStockInfo entity) {
        this.symbol = entity.getStockCode();
        this.nameVn = entity.getNameVn();
        this.nameEn = entity.getNameEn();
        this.stockExchange = entity.getPostTo();
    }
}
