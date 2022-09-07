package com.service.zerobnb.web.error.handler;

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
     * 예외별로 어떻게 분류해서 에러 응답값을 반환할지 토의 & 결정
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandler() {
        // TODO
        String message = "에러가 발생하였습니다.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
