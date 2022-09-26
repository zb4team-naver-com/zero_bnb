package com.service.zerobnb.web.guest.controller;

import com.service.zerobnb.web.guest.dto.GuestDto;
import com.service.zerobnb.web.guest.model.GuestInput;
import com.service.zerobnb.web.guest.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저에 관련된 API 입니다.")
public class GuestController {

    private final GuestService guestService;

    @Operation(summary = "유저의 모든 정보를 가져옵니다.", description = "")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유저 정보 데이터(JSON)")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getInfo(@PathVariable Long id) {

        GuestDto guestDto = guestService.getGuestInfo(id);

        return ResponseEntity.ok(guestDto);
    }

    @Operation(summary = "유저의 정보를 수정합니다.", description = "")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유저의 수정된 정보 데이터(JSON)")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<GuestDto> updateInfo(@RequestBody @Valid GuestInput request,
                                                @PathVariable Long id) {

        GuestDto guestDto = this.guestService.update(id, request);

        return ResponseEntity.ok(guestDto);
    }

    @Operation(summary = "유저 탈퇴", description = "탈퇴 시 해당 유저의 서비스 이용이 제한됩니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGuest(@PathVariable Long id) {

        this.guestService.withdraw(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
