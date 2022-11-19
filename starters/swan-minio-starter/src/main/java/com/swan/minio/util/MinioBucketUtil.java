package com.swan.minio.util;

import com.swan.minio.constant.MinioConstant;
import com.swan.minio.exception.SwanMinioException;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/** MinIo bucket 操作工具类
 * @author zongf
 * @since 2021-07-29
 */
@Slf4j
public final class MinioBucketUtil {

    /** 创建 桶, 桶已经存在时, 抛出异常
     * @param client 客户端
     * @param bucketName 桶名称
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static void make(MinioClient client, String bucketName) {
        try {
            MakeBucketArgs bucket = MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build();
            client.makeBucket(bucket);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.BUCKET_ALREADY_OWNED_BY_YOU.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_ALREADY_EXISTS, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 删除 桶，桶必须为空
     * @param client 客户端
     * @param bucketName 桶名称
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static void remove(MinioClient client, String bucketName) {
        try {
            RemoveBucketArgs bucket = RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build();
            client.removeBucket(bucket);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }else if (MinioConstant.BUCKET_NOT_EMPTY.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.BUCKET_NOT_EMPTY, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 判断桶是否存在
     * @param client 客户端
     * @param bucketName 桶名称
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static Boolean exists(MinioClient client, String bucketName) {

        BucketExistsArgs build = BucketExistsArgs.builder()
                .bucket(bucketName)
                .build();
        try {
            return client.bucketExists(build);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 获取桶名称列表
     * @param client 客户端
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static List<String> list(MinioClient client) {
        try {
            List<Bucket> buckets = client.listBuckets();
            return buckets.stream().map(Bucket::name).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 获取桶内的对象列表
     * @param client 客户端
     * @param bucketName 桶名称
     * @param dirName 对象所处目录，如: images/2021/400x600, videos/2021/300x600
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static List<String> listObjects(MinioClient client, String bucketName, String dirName) {

        try {
            ListObjectsArgs bucket = ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .prefix(dirName)
                    .build();
            Iterable<Result<Item>> results = client.listObjects(bucket);

            List<String> objectNameList = new ArrayList<>();
            Iterator<Result<Item>> iterator = results.iterator();
            while (iterator.hasNext()) {
                objectNameList.add(iterator.next().get().objectName());
            }
            return objectNameList;
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 获取桶访问策略
     * @param client 客户端
     * @param bucketName 桶名称
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static String getPolicy(MinioClient client, String bucketName) {

        try {
            GetBucketPolicyArgs bucket = GetBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .build();
            return client.getBucketPolicy(bucket);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 设置桶访问策略
     * @param client 客户端
     * @param bucketName 桶名称
     * @param policyConfig 策略配置
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static void setPolicy(MinioClient client, String bucketName, String policyConfig) {

        try {
            SetBucketPolicyArgs bucket = SetBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .config(policyConfig)
                    .build();
            client.setBucketPolicy(bucket);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 删除桶访问策略
     * @param client 客户端
     * @param bucketName 桶名称
     * @return Boolean
     * @author zongf
     * @since 2021-08-14
     */
    public static void deletePolicy(MinioClient client, String bucketName) {

        try {
            DeleteBucketPolicyArgs bucket = DeleteBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .build();
            client.deleteBucketPolicy(bucket);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

}
