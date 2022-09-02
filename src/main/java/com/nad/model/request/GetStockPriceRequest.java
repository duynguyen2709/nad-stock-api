package com.nad.model.request;

import com.nad.constant.ErrorCode;
import com.nad.model.base.RequestEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.nad.constant.Constant.MAX_SYMBOL_PER_PRICE_QUERY;

/**
 * @author duynguyen
 **/
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class GetStockPriceRequest extends RequestEntity implements Serializable {
    private List<String> symbols;

    public static class GetStockPriceRequestBuilder {
        private List<String> symbols;
        public GetStockPriceRequestBuilder symbols(String symbolList) {
            this.symbols = Arrays.stream(symbolList.split(","))
                    .filter(ele -> !ele.isBlank())
                    .collect(Collectors.toList());
            return this;
        }
    }

    @Override
    public ErrorCode validate() {
        if (this.symbols.isEmpty()) {
            return ErrorCode.SYMBOL_LIST_EMPTY;
        }
        if (this.symbols.size() > MAX_SYMBOL_PER_PRICE_QUERY) {
            return ErrorCode.LIMIT_SIZE_PRICE_QUERY;
        }
        return null;
    }
}
