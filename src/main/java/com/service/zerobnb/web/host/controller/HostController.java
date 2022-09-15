package com.service.zerobnb.web.host.controller;

import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.ValidationException;
import com.service.zerobnb.web.host.dto.HostDto;
import com.service.zerobnb.web.host.model.HostInput;
import com.service.zerobnb.web.host.service.HostService;
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

@Tag(name = "호스트", description = "호스트 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/host")
@Slf4j
public class HostController {
    private final HostService hostService;

    @Operation(summary = "호스트 기본 정보", description = "호스트 기본 정보 반환 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "호스트 기본 정보 반환 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 호스트 기본 정보 반환 실패")
    })
    @GetMapping("/{email}")
    public ResponseEntity<HostDto> getHost(@PathVariable String email, Principal principal) {
        // TODO principal 객체를 이용한 guest email 반환 및 이메일 인증에 사용
        return ResponseEntity.status(HttpStatus.OK).body(HostDto.from(hostService.findHostByEmail(email)));
    }

    @Operation(summary = "호스트 정보 등록", description = "호스트 정보 등록 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "호스트 정보 등록 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 호스트 정보 등록 실패")
    })
    @PostMapping("/register")
    public ResponseEntity<Long> registerHost(@RequestBody @Valid HostInput hostInput, BindingResult bindingResult, Principal principal) {
        // TODO principal 객체를 이용한 guest email 반환 및 이메일 인증에 사용
        if (bindingResult.hasErrors()) {
            throw new ValidationException(ExceptionMessage.NOT_VALID_INPUT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(hostService.registerHost(hostInput));
    }

    @Operation(summary = "호스트 정보 수정", description = "호스트 정보 수정 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "호스트 정보 수정 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 호스트 정보 수정 실패")
    })
    @PutMapping("/update/{hostId}")
    public ResponseEntity<Long> updateHost(@RequestBody @Valid HostInput hostInput,
                                           @PathVariable Long hostId,
                                           BindingResult bindingResult, Principal principal) {
        // TODO principal 객체를 이용한 guest email 반환 및 이메일 인증에 사용
        if (bindingResult.hasErrors()) {
            throw new ValidationException(ExceptionMessage.NOT_VALID_INPUT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(hostService.updateHost(hostInput, hostId));
    }

    @Operation(summary = "호스트 비활성화", description = "호스트 비활성화 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "호스트 비활성화 작업 수행 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 호스트 비활성화 작업 수행 실패")
    })
    @PatchMapping("/disable/{hostId}")
    public ResponseEntity<Long> disableHost(@PathVariable Long hostId, Principal principal) {
        // TODO principal 객체를 이용한 guest email 반환 및 이메일 인증에 사용
        return ResponseEntity.status(HttpStatus.OK).body(hostService.disableHost(hostId));
    }

    @Operation(summary = "호스트 활성화", description = "호스트 활성화 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "호스트 활성화 작업 수행 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크, 데이터베이스 저장 실패 등의 이유로 호스트 활성화 작업 수행 실패")
    })
    @PatchMapping("/able/{hostId}")
    public ResponseEntity<Long> ableHost(@PathVariable Long hostId, Principal principal) {
        // TODO principal 객체를 이용한 guest email 반환 및 이메일 인증에 사용
        return ResponseEntity.status(HttpStatus.OK).body(hostService.ableHost(hostId));
    }
}
