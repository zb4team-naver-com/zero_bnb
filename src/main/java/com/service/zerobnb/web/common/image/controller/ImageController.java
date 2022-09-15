package com.service.zerobnb.web.common.image.controller;

import com.service.zerobnb.web.common.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "이미지", description = "이미지 관련 API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;

    @Operation(summary = "이미지 업로드", description = "이미지 업로드 수행 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 업로드 완료"),
            @ApiResponse(responseCode = "500", description = "네트워크 불안정, s3 스토리지 연결 불량 등의 이유로 이미지 업로드 실패")
    })
    @PostMapping(path = "/images")
    public ResponseEntity<List<String>> registerImages(@RequestParam("images") List<MultipartFile> multipartFiles) throws Exception {
        List<String> imgSrcList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.getOriginalFilename().isEmpty()) {
                continue;
            }
            String imgSrc = imageService.uploadImage(multipartFile);
            imgSrcList.add(imgSrc);
        }
        return ResponseEntity.status(HttpStatus.OK).body(imgSrcList);
    }
}
