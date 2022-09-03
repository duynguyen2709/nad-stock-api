package com.nad.handler;

import com.nad.constant.ErrorCode;
import com.nad.model.response.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.nad.constant.ErrorCode.PARAMETER_INVALID;

/**
 * @author duynguyen
 */
@RestControllerAdvice
@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse> handle(Exception ex) {
        log.error("Exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BaseResponse.builder()
                        .status("failed")
                        .code(ErrorCode.EXCEPTION.getCode())
                        .message(ErrorCode.EXCEPTION.getMessage())
                        .build());
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMissingServletRequestParameter(
            @NotNull MissingServletRequestParameterException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BaseResponse.builder()
                        .status("failed")
                        .code(PARAMETER_INVALID.getCode())
                        .message(String.format("Param '%s' is required", ex.getParameterName()))
                        .build());
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BaseResponse.builder()
                        .status("failed")
                        .code(PARAMETER_INVALID.getCode())
                        .message(String.format("Param '%s' is invalid", ex.getParameter().getParameterName()))
                        .build());
    }
}
