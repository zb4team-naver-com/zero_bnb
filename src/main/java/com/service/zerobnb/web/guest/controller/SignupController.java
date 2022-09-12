package com.service.zerobnb.web.guest.controller;

import com.service.zerobnb.web.guest.model.Auth;
import com.service.zerobnb.web.guest.service.GuestService;
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
public class SignupController {

    private final GuestService guestService;

    @GetMapping
    public String getSignupPage() {

        return "미인증 회원 홈페이지";
    }

    @PostMapping
    public ResponseEntity<?> postSignup(@RequestBody Auth.Signup request) {
        var result = this.guestService.register(request);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/email-auth/{id}")
    public String emailAuth(@PathVariable("id") String request) {

        if(!guestService.emailAuth(request)) {
            return "인증 실패";
        }

        return "인증 회원 홈페이지";
    }
}
