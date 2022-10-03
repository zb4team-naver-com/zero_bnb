package com.service.zerobnb.web.error.dto;

import javax.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {
    private final String statusCode;
    private final String requestUrl;
    private final String message;

    public static ErrorResponse from(Exception exception, HttpServletRequest httpServletRequest) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .requestUrl(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .build();
    }
}
