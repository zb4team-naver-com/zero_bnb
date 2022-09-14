package com.service.zerobnb.web.guest.controller;

import com.service.zerobnb.security.TokenProvider;
import com.service.zerobnb.web.guest.model.Auth;
import com.service.zerobnb.web.guest.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final GuestService guestService;
    private final TokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Auth.LogIn request) {
        // password 검증

        // token 생성 후 반환

        return null;
    }
}
