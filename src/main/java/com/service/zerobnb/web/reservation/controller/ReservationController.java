package com.service.zerobnb.web.reservation.controller;

import com.service.zerobnb.web.error.model.GuestException;
import com.service.zerobnb.web.error.model.ValidationException;
import com.service.zerobnb.web.reservation.dto.ReservationDto;
import com.service.zerobnb.web.reservation.model.ReservationForm;
import com.service.zerobnb.web.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.service.zerobnb.web.error.message.ExceptionMessage.NOT_LOGIN_STATUS;
import static com.service.zerobnb.web.error.message.ExceptionMessage.NOT_VALID_INPUT;

@Tag(name = "예약", description = "예약 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "게스트가 원하는 숙소 예약 및 결제", description = "예약과 결제 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약 성공"),
            @ApiResponse(responseCode = "500", description = "예약 실패")
    })
    @PostMapping("/")
    public ResponseEntity<ReservationDto> reservation(@RequestBody @Valid ReservationForm reservationForm,
                                                      BindingResult bindingResult,
                                                      Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//        String email = principal.getName();
        if (bindingResult.hasErrors()) {
            throw new ValidationException(NOT_VALID_INPUT);
        }

        return ResponseEntity.ok(reservationService.reservation("fgfg@gmail.com", reservationForm));
    }

    @Operation(summary = "게스트가 예약한 숙소 정보(게스트)", description = "예약한 숙소 정보 반환 메서드(게스트)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약한 숙소 정보 반환 성공"),
            @ApiResponse(responseCode = "500", description = "예약한 숙소 정보 반환 실패")
    })
    @GetMapping("/info/{guestId}")
    public ResponseEntity<List<ReservationDto>> myReservation(@PathVariable Long guestId, Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//        String email = principal.getName();
        return ResponseEntity.ok(reservationService.myReservation(guestId, "fgfg@gmail.com"));
    }

    @Operation(summary = "게스트가 예약한 숙소 취소", description = "예약한 숙소 취소 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "예약한 숙소 취소 성공"),
            @ApiResponse(responseCode = "500", description = "예약한 숙소 취소 실패")
    })
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId, Principal principal) {
//        TODO principal 통해 email 가져오기
//        if (principal == null) {
//            throw new GuestException(NOT_LOGIN_STATUS);
//        }
//
//        String email = principal.getName();
        reservationService.cancelReservation("fgfg@gmail.com", reservationId);
        return ResponseEntity.ok().build();
    }
}