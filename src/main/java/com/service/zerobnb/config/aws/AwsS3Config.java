package com.service.zerobnb.config.aws;

import com.service.zerobnb.config.yaml.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:application-aws.yml", factory = YamlPropertySourceFactory.class)
@Data
public class AwsS3Config {
    @Value("${aws-s3-bucket.access_key_id}")
    private String accessKeyId;

    @Value("${aws-s3-bucket.secret_access_key_id}")
    private String secretAccessKeyId;

    @Value("${aws-s3-bucket.bucket_name}")
    private String bucketName;
}
