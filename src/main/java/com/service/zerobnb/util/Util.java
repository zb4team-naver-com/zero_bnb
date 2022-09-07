package com.service.zerobnb.util;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

public class Util {
    public static String getImageFileUUID(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uuid = LocalDateTime.now().toString() + UUID.nameUUIDFromBytes(fileName.getBytes(StandardCharsets.UTF_8)) + "." + extension;
        return uuid;
    }

    public static ObjectMetadata initObjectMetaData(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        return objectMetadata;
    }
}
