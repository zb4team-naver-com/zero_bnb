package com.service.zerobnb.web.guest.controller;

import com.service.zerobnb.web.guest.dto.GuestDto;
import com.service.zerobnb.web.guest.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "모든 유저에 관련된 API 입니다.")
public class GuestController {

    private final GuestService guestService;

    @Operation(summary = "유저의 모든 정보를 가져옵니다.", description = "")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유저 정보 데이터(JSON)")
    })
    @PostMapping("/info")
    public ResponseEntity<GuestDto> getUserInfo () {

        GuestDto guestDto = GuestDto.builder()
            .build();

        return ResponseEntity.ok(guestDto);
    }
}
