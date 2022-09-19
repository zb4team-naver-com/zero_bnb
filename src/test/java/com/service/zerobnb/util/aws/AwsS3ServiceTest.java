package com.service.zerobnb.util.aws;

import com.service.zerobnb.web.error.message.ExceptionMessage;
import com.service.zerobnb.web.error.model.FileHandleException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AwsS3ServiceTest {
    @Autowired
    private AwsS3Service awsS3Service;

    @MockBean
    private AwsS3Util awsS3Util;

    @Test
    @Disabled
    void uploadImageFile() throws Exception {
        // 정상 이미지
        when(awsS3Util.storeImageFile(any(), any(), any())).thenReturn("success");
        assertNotNull(awsS3Service.uploadImageFile(new MockMultipartFile("images", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes())));
        verify(awsS3Util, times(1)).storeImageFile(any(), any(), any());

        // origin file name empty
        when(awsS3Util.storeImageFile(any(), any(), any())).thenReturn("success");
        assertEquals(ExceptionMessage.NOT_VALID_FILE_NAME.message(), assertThrows(FileHandleException.class,
                () -> awsS3Service.uploadImageFile(new MockMultipartFile("images", "", "image/jpeg", "<<jpeg data>>".getBytes()))).getMessage());
    }
}