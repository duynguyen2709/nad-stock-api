package com.nad.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nad.model.response.external.ExternalStockPrice;
import com.nad.util.NumberUtil;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author duynguyen
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockPrice implements Serializable {
    @JsonIgnore
    private final String symbol;
    @JsonProperty("ceil_price")
    private float ceilPrice;
    @JsonProperty("floor_price")
    private float floorPrice;
    @JsonProperty("reference_price")
    private float referencePrice;
    @JsonProperty("last_price")
    private float lastPrice;
    @JsonProperty("last_volume")
    private int lastVolume;
    @JsonProperty("changed_price")
    private float changedPrice;
    @JsonProperty("changed_percentage")
    private float changedPercentage;
    @JsonProperty("average_price")
    private float avgPrice;
    @JsonProperty("low_price")
    private float lowPrice;
    @JsonProperty("high_price")
    private float highPrice;

    public StockPrice(ExternalStockPrice entity) {
        this.symbol = entity.getSym();
        this.ceilPrice = entity.getC();
        this.floorPrice = entity.getF();
        this.referencePrice = entity.getR();
        this.lastPrice = entity.getLastPrice();
        this.lastVolume = entity.getLastVolume();
        this.changedPrice = NumberUtil.parseFloat(entity.getOt());
        this.changedPercentage = NumberUtil.parseFloat(entity.getChangePc());
        if (this.lastPrice < this.referencePrice) {
            this.changedPrice *= -1;
            this.changedPercentage *= -1;
        }
        this.avgPrice = NumberUtil.parseFloat(entity.getAvePrice());
        this.lowPrice = NumberUtil.parseFloat(entity.getLowPrice());
        this.highPrice = NumberUtil.parseFloat(entity.getHighPrice());
    }
}
