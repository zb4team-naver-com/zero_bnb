package com.service.zerobnb.web.room.controller;

import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.ValidationException;
import com.service.zerobnb.web.room.dto.RoomDto;
import com.service.zerobnb.web.room.model.RoomInput;
import com.service.zerobnb.web.room.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Tag(name = "방", description = "방 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
@Slf4j
public class RoomController {
    private final RoomService roomService;

    @Operation(summary = "특정 숙소의 숙소 정보 반환", description = "숙소 정보 반환 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "숙소 정보 반환 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 숙소 정보 반환 실패")
    })
    @GetMapping("/search/{accommodationId}")
    public ResponseEntity<List<RoomDto>> findRoom(@PathVariable Long accommodationId, Principal principal) {
        // TODO 추후에 principal 객체를 통한 로그인 여부, hostId <-> parameter hostId 비교 및 인증
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findRoomsByAccommodationId(accommodationId));
    }

    @Operation(summary = "숙소 정보 등록", description = "숙소 정보 등록 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "숙소 정보 등록 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 숙소 정보 등록 실패")
    })
    @PostMapping("/register")
    public ResponseEntity<Long> registerRoom(@RequestBody @Valid RoomInput roomInput, BindingResult bindingResult, Principal principal) {
        // TODO 추후에 principal 객체를 통한 로그인 여부, hostId <-> parameter hostId 비교 및 인증
        if (bindingResult.hasErrors()) {
            throw new ValidationException(ExceptionMessage.NOT_VALID_INPUT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(roomService.registerRoom(roomInput));
    }

    @Operation(summary = "숙소 정보 수정", description = "숙소 정보 수정 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "숙소 정보 수정 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 숙소 정보 수정 실패")
    })
    @PutMapping("/update/{roomId}")
    public ResponseEntity<Long> updateRoom(@RequestBody @Valid RoomInput roomInput, @PathVariable Long roomId
            , BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(ExceptionMessage.NOT_VALID_INPUT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoom(roomInput, roomId));
    }


    @Operation(summary = "방 정보 삭제", description = "방 정보 삭제 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "방 정보 삭제 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 방 정보 삭제 실패")
    })
    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Long> deleteRoom(@PathVariable Long roomId, Principal principal) {
        // TODO principal 객체를 이용한 guest email 반환 및 이메일 인증에 사용
        return ResponseEntity.status(HttpStatus.OK).body(roomService.deleteRoom(roomId));
    }
}
