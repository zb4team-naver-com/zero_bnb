package com.service.zerobnb.web.main;

import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.web.accommodation.dto.AccommodationMainDto;
import com.service.zerobnb.web.accommodation.service.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "메인", description = "메인페이지 숙소 정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
@Slf4j
public class MainController {

    private final AccommodationService accommodationService;


    @Operation(summary = "메인 화면 숙소 리스트 반환", description = "현 위치 및 타입 별 주변 숙소 정보 반환 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "현 위치 타입 별 주변 숙소 정보 반환 완료"),
            @ApiResponse(responseCode = "500", description = "현 위치 타입 별 주변 숙소 정보 반환 실패")
    })
    @GetMapping("/")
    public ResponseEntity<List<AccommodationMainDto>> nearDistAccommodation(@RequestParam(required = false) Double lat,
                                                                            @RequestParam(required = false) Double lnt,
                                                                            @RequestParam(required = false) AccommodationType accommodationType) {

        return ResponseEntity.ok(accommodationService.nearDistOrTypeAccommodation(accommodationType, lat, lnt));
    }
}
