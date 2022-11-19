package com.swan.minio.service;

import com.swan.minio.service.api.IMinioBucket;
import com.swan.minio.util.MinioBucketUtil;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** MinIo 桶操作
 * @author zongf
 * @since 2021-07-30
 */
public class MinioBucket implements IMinioBucket {

    @Autowired
    private MinioClient.Builder minIoClientBuilder;

    @Override
    public void make(String bucketName) {
        MinioBucketUtil.make(this.minIoClientBuilder.build(), bucketName);
    }

    @Override
    public Boolean exists(String bucketName) {
        return MinioBucketUtil.exists(this.minIoClientBuilder.build(), bucketName);
    }

    @Override
    public void remove(String bucketName) {
        MinioBucketUtil.remove(this.minIoClientBuilder.build(), bucketName);
    }

    @Override
    public List listBuckets() {
        return MinioBucketUtil.list(this.minIoClientBuilder.build());
    }

    @Override
    public List<String> listObjects(String bucketName, String objectNamePrefix) {
        return MinioBucketUtil.listObjects(this.minIoClientBuilder.build(), bucketName, objectNamePrefix);
    }

    @Override
    public void setPolicy(String bucketName, String policyConfig) {
        MinioBucketUtil.setPolicy(this.minIoClientBuilder.build(), bucketName, policyConfig);
    }

    @Override
    public String getPolicy(String bucketName) {
        return MinioBucketUtil.getPolicy(this.minIoClientBuilder.build(), bucketName);
    }

    @Override
    public void deletePolicy(String bucketName) {
        MinioBucketUtil.deletePolicy(this.minIoClientBuilder.build(), bucketName);
    }
}
