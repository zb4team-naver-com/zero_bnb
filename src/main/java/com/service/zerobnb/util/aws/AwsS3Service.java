package com.service.zerobnb.util.aws;

import com.service.zerobnb.util.Util;
import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.FileHandleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    private final AwsS3Util awsS3Util;

    public String uploadImageFile(MultipartFile multipartFile) throws Exception {
        if (multipartFile.getOriginalFilename().isEmpty()) {
            throw new FileHandleException(ExceptionMessage.NOT_VALID_FILE_NAME);
        }

        String imgSrc = awsS3Util.storeImageFile(Util.getImageFileUUID(multipartFile), multipartFile.getInputStream(), Util.initObjectMetaData(multipartFile));
        return imgSrc;
    }
}
