package com.service.zerobnb.web.reservation.controller;

import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.reservation.dto.ReservationDto;
import com.service.zerobnb.web.reservation.model.ReservationForm;
import com.service.zerobnb.web.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.service.zerobnb.web.error.message.ExceptionMessage.NOT_LOGIN_STATUS;

@Tag(name = "예약", description = "예약 관련 API")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "게스트가 원하는 숙소 예약", description = "예약 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약 성공"),
            @ApiResponse(responseCode = "500", description = "예약 실패")
    })
    @PostMapping("/reservation")
    public ResponseEntity<Void> reservation(@RequestBody ReservationForm reservationForm, Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//        String email = principal.getName();
        reservationService.reservation("fgfg@gmail.com", reservationForm);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게스트가 예약한 숙소 정보(게스트)", description = "예약한 숙소 정보 반환 메서드(게스트)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약한 숙소 정보 반환 성공"),
            @ApiResponse(responseCode = "500", description = "예약한 숙소 정보 반환 실패")
    })
    @GetMapping("/reservation/info")
    public ResponseEntity<List<ReservationDto>> myReservation(Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//        String email = principal.getName();
        return ResponseEntity.ok(reservationService.myReservation("fdsfs@daum.net"));
    }

    @Operation(summary = "게스트가 예약한 숙소 취소", description = "예약한 숙소 취소 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약한 숙소 취소 성공"),
            @ApiResponse(responseCode = "500", description = "예약한 숙소 취소 실패")
    })
    @DeleteMapping("/reservation")
    public ResponseEntity<Void> cancelReservation(Principal principal, @RequestParam Long id) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//
//        String email = principal.getName();
        reservationService.cancelReservation("fgfg@gmail.com", id);
        return ResponseEntity.ok().build();
    }
}