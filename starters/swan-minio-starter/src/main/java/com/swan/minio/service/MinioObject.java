package com.swan.minio.service;

import com.swan.minio.beans.ObjectPutResult;
import com.swan.minio.beans.ObjectState;
import com.swan.minio.service.api.IMinioObject;
import com.swan.minio.util.MinioObjectUtil;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.StatObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @author zongf
 * @since 2021-07-30
 */
public class MinioObject implements IMinioObject {

    @Autowired
    private MinioClient.Builder minIoClientBuilder;

    @Override
    public ObjectPutResult put(String bucketName, String objectName, byte[] bytes, Map<String, String> userData) {
        ObjectWriteResponse response = MinioObjectUtil.putObject(minIoClientBuilder.build(), bucketName, objectName, new ByteArrayInputStream(bytes), userData);
        return new ObjectPutResult(response.etag(), response.versionId());
    }

    @Override
    public ObjectPutResult put(String bucketName, String objectName, InputStream is, Map<String, String> userData) {
        ObjectWriteResponse response = MinioObjectUtil.putObject(minIoClientBuilder.build(), bucketName, objectName, is, userData);
        return new ObjectPutResult(response.etag(), response.versionId());
    }

    @Override
    public ObjectPutResult put(String bucketName, String objectName, byte[] bytes) {
        return this.put(bucketName, objectName, bytes, null);
    }

    @Override
    public ObjectPutResult put(String bucketName, String objectName, InputStream is) {
        return this.put(bucketName, objectName, is, null);
    }

    @Override
    public InputStream get(String bucketName, String objectName) {
        return MinioObjectUtil.getObject(minIoClientBuilder.build(), bucketName, objectName);
    }

    @Override
    public byte[] getBytes(String bucketName, String objectName) {
        return MinioObjectUtil.getObjectBytes(minIoClientBuilder.build(), bucketName, objectName);
    }

    @Override
    public String getString(String bucketName, String objectName) {
        byte[] bytes = getBytes(bucketName, objectName);
        return bytes == null ? null : new String(bytes);
    }

    @Override
    public void remove(String bucketName, String objectName) {
        MinioObjectUtil.remove(minIoClientBuilder.build(), bucketName, objectName);
    }

    @Override
    public boolean exists(String bucketName, String objectName) {
        return MinioObjectUtil.exists(minIoClientBuilder.build(), bucketName, objectName);
    }

    @Override
    public ObjectState stat(String bucketName, String objectName) {

        StatObjectResponse response =  MinioObjectUtil.stat(minIoClientBuilder.build(), bucketName, objectName);

        if(response == null) return null;

        return ObjectState.builder()
                .deleteMarker(response.deleteMarker())
                .etag(response.etag())
                .lastModified(response.lastModified())
                .size(response.size())
                .userMetadata(response.userMetadata())
                .build();
    }

    @Override
    public Map<String, String> getTags(String bucketName, String objectName) {
        return MinioObjectUtil.getTags(minIoClientBuilder.build(), bucketName, objectName);
    }

    @Override
    public void setTags(String bucketName, String objectName, Map<String, String> tags) {
        MinioObjectUtil.setTags(minIoClientBuilder.build(), bucketName, objectName, tags);
    }

    @Override
    public void addTags(String bucketName, String objectName, Map<String, String> addTags) {
        MinioObjectUtil.addTags(minIoClientBuilder.build(), bucketName, objectName, addTags);;
    }
}
