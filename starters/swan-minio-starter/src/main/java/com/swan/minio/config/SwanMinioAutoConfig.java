package com.swan.minio.config;

import com.swan.minio.service.MinioBucket;
import com.swan.minio.service.MinioObject;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zongf
 * @date 2021-07-29
 */
@Configuration
@EnableConfigurationProperties(SwanMinioProperties.class)
public class SwanMinioAutoConfig {

    @Bean
    public MinioClient.Builder minIoClientBuilder(SwanMinioProperties swanMinIoProperties) {
        SwanMinioProperties.Server config = swanMinIoProperties.getServer();
        return MinioClient.builder()
                .credentials(config.getAccessKey(), config.getSecretKey())
                .endpoint(config.getHost(), config.getPort(), config.isHttps())
                .region(config.getRegion());
    }

    @Bean
    public MinioObject minioObject() {
        return new MinioObject();
    }

    @Bean
    @ConditionalOnProperty("sboot.minio.enable-bucket")
    public MinioBucket minioBucket() {
        return new MinioBucket();
    }

}
