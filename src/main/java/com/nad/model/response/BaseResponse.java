package com.nad.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nad.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author duynguyen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {
    public static final BaseResponse SUCCESS;

    static {
        SUCCESS = BaseResponse.builder().status("success").build();
    }

    private String status;
    private Integer code;
    private String message;
    private Object data;

    public static BaseResponse failed(ErrorCode error) {
        return builder()
                .status("failed")
                .code(error.getCode())
                .message(error.getMessage())
                .build();
    }

    public static BaseResponse successData(Object data) {
        return builder()
                .status("success")
                .code(SUCCESS.getCode())
                .data(data)
                .build();
    }

}
