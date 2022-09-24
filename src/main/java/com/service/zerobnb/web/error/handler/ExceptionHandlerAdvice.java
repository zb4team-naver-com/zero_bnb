package com.service.zerobnb.web.error.handler;

import com.service.zerobnb.web.error.dto.ErrorResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    /**
     * 아래는 예시 메서드
     * 필요에 따라 예외 별로 분류
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception exception, HttpServletRequest httpServletRequest) {
        log.error("zerobnb[exceptionHandler] error happened", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.from(exception, httpServletRequest));
    }
}
