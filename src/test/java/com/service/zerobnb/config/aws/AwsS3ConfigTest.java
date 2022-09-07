package com.service.zerobnb.config.aws;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AwsS3ConfigTest {
    @Autowired
    private AwsS3Config awsS3Config;

    @Test
    public void awsS3ConfigTest() {
        //        System.out.println(awsS3Config.getAccessKeyId());
        assertNotNull(awsS3Config.getAccessKeyId());
        //        System.out.println(awsS3Config.getSecretAccessKeyId());
        assertNotNull(awsS3Config.getSecretAccessKeyId());
        //        System.out.println(awsS3Config.getBucketName());
        assertNotNull(awsS3Config.getBucketName());
    }
}
