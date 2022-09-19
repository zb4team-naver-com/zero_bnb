package com.service.zerobnb.web.guest.controller;

import com.service.zerobnb.web.guest.dto.GuestDto;
import com.service.zerobnb.web.guest.dto.ResponseTokenDto;
import com.service.zerobnb.web.guest.model.Auth;
import com.service.zerobnb.web.guest.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "로그인", description = "로그인 관련 API")
public class LoginController {


    private final GuestService guestService;

    @Operation(summary = "로그인을 요청하는 api 입니다.", description = "guest 의 이메일과 비밀번호가 필요합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공 시 JWT 로 만들어진 Access token 과 Refresh token 이 발급됩니다."),
        @ApiResponse(responseCode = "505", description = "로그인에 실패했을 때의 응답 코드")
    })
    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDto> login(@RequestBody Auth.LogIn request) {

        ResponseTokenDto responseToken = this.guestService.logIn(request);

        return ResponseEntity.ok(responseToken);
    }

    @Operation(summary = "로그아웃을 요청하는 api 입니다.", description = "로그아웃 요청 시 Refresh token 은 삭제됩니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그아웃에 성공하면 메인 페이지로 이동합니다."),
        @ApiResponse(responseCode = "404", description = "로그아웃에 실패했을 때의 응답 코드")
    })
    @GetMapping("/logout")
    public ResponseEntity<GuestDto> logout() {

        GuestDto guestDto = this.guestService.logOut();

        return ResponseEntity.ok(guestDto);
    }


}