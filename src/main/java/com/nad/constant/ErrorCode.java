package com.nad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duynguyen
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    SUCCESS(1, "Success"),
    EXCEPTION(0, "An error occurred. Please retry later!"),
    LIMIT_SIZE_PRICE_QUERY(-1, String.format("Max number of symbols per price query is %d", Constant.MAX_SYMBOL_PER_PRICE_QUERY)),
    SYMBOL_LIST_EMPTY(-2, "Symbol list can not be empty"),
    SYMBOL_NOT_FOUND(-3, "Symbol not found"),
    PARAMETER_INVALID(-4, "Param '%s' is required");

    private final int code;
    private final String message;
}
