package com.service.zerobnb.web.common.image.service;

import com.service.zerobnb.util.aws.AwsS3Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    @MockBean
    private AwsS3Service awsS3Service;

    @Test
    void uploadImage() throws Exception{
        when(awsS3Service.uploadImageFile(any())).thenReturn("success");
        assertNotNull(imageService.uploadImage(new MockMultipartFile("images", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes())));
        verify(awsS3Service,times(1)).uploadImageFile(any());
    }
}