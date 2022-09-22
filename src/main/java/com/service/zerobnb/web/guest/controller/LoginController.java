package com.service.zerobnb.web.guest.controller;

import com.service.zerobnb.web.guest.dto.ResponseTokenDto;
import com.service.zerobnb.web.guest.model.Auth;
import com.service.zerobnb.web.guest.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "로그인", description = "로그인 관련 API")
public class LoginController {


    private final AuthService guestService;

    @Operation(summary = "로그인을 요청하는 api 입니다.", description = "guest 의 이메일과 비밀번호가 필요합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공 시 JWT 로 만들어진 Access token 과 Refresh token 이 발급됩니다.")
    })
    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDto> login(@Valid @RequestBody Auth.LogIn request) {

        ResponseTokenDto responseToken = this.guestService.logIn(request);

        return ResponseEntity.ok(responseToken);
    }

    @Operation(summary = "로그아웃을 요청하는 api 입니다.", description = "로그아웃 요청 시 Refresh token 은 삭제됩니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그아웃에 성공하면 메인 페이지로 이동합니다.")
    })
    @PostMapping("/logout")
    public ResponseEntity<String> postLogout(@RequestBody Auth.LogOut request) {

        String response = this.guestService.logOut(request);

        return ResponseEntity.ok(response);
    }
}