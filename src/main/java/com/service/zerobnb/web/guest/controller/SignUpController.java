package com.service.zerobnb.web.guest.controller;

import com.service.zerobnb.web.guest.model.Auth;
import com.service.zerobnb.web.guest.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
@Tag(name = "회원가입", description = "회원가입 관련 API")
public class SignUpController {

    private final AuthService guestService;

    @Operation(summary = "가입을 원하는 회원의 정보를 받습니다.", description = "회원 가입 메서드")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원 가입에 성공했을 때의 응답 코드"),
        @ApiResponse(responseCode = "500", description = "특정 이유로 가입에 실패했을 때의 응답 코드")
    })
    @PostMapping
    public ResponseEntity<?> Signup(@RequestBody Auth.SignUp request) {
        var result = this.guestService.register(request);

        return ResponseEntity.ok(result);
    }


    @Operation(summary = "이메일 인증이 완료된 회원이 받는 결과입니다.", description = "메일 인증 메서드")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "메일 인증에 성공했을 때의 응답 코드"),
        @ApiResponse(responseCode = "404", description = "인증에 실패했을 때의 응답 코드")
    })
    @GetMapping("/email-auth/{id}")
    public ResponseEntity<?> emailAuth(@PathVariable("id") String request) {

        if(!guestService.emailAuth(request)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
