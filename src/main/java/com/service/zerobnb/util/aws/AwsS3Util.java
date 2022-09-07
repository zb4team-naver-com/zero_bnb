package com.service.zerobnb.util.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.service.zerobnb.config.aws.AwsS3Config;
import com.service.zerobnb.util.ConstUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class AwsS3Util {
    private final AwsS3Config awsS3Config;

    public String storeImageFile(String uuid, InputStream inputStream, ObjectMetadata objectMetadata) throws AmazonClientException {
        AmazonS3 amazonS3 = initAwsS3Object();
        amazonS3.putObject(awsS3Config.getBucketName(), String.format("image/%s", uuid), inputStream, objectMetadata);
        return String.format(ConstUtil.AWS_S3_IMAGE_URL, uuid);
    }

    private AmazonS3 initAwsS3Object() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsS3Config.getAccessKeyId(), awsS3Config.getSecretAccessKeyId())))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
