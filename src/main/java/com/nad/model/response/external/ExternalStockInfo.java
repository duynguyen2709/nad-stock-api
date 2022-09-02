package com.nad.model.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author duynguyen
 */
@Getter
@AllArgsConstructor
public class ExternalStockInfo implements Serializable {
    @JsonProperty("stock_code")
    private final String stockCode;
    @JsonProperty("name_vn")
    private final String nameVn;
    @JsonProperty("name_en")
    private final String nameEn;
    @JsonProperty("post_to")
    private final String postTo;
    @JsonProperty("name_short")
    private final String nameShort;
    @JsonProperty("C")
    private final int c;
}
