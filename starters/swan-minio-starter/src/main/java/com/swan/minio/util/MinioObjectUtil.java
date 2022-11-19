package com.swan.minio.util;

import com.swan.minio.constant.MinioConstant;
import com.swan.minio.exception.SwanMinioException;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.messages.Tags;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/** 对象操作工具类
 * @author zongf
 * @since 2021-07-29
 */
@Slf4j
public final class MinioObjectUtil {

    /** 保存或更新对象信息, bucket 不存在时抛出异常
     * @param minioClient
     * @param bucketName 桶名称, 当为 $dir/../$file 时，会自动创建目录
     * @param objectName 对象名
     * @param userData 用户自定义数据
     * @param is 对象流
     * @throws SwanMinioException
     * @author zongf
     * @since 2021-08-14
     */
    public static ObjectWriteResponse putObject(MinioClient minioClient, String bucketName, String objectName, InputStream is, Map<String,String > userData) {

        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .stream(is, is.available(), -1)
                    .object(objectName)
                    .userMetadata(userData)
                    .build();
            return minioClient.putObject(args);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }else {
                throw new SwanMinioException(MinioConstant.MSG_OBJECT_NOT_EXIST, ex);
            }
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 获取对象流
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws SwanMinioException
     * @author zongf
     * @since 2021-08-14
     */
    public static InputStream getObject(MinioClient minioClient, String bucketName, String objectName) {
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
           return minioClient.getObject(args);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }else if(MinioConstant.CODE_NO_SUCH_KEY.equals(ex.errorResponse().code())) {
                return null;
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 删除对象. 桶不存在时抛出异常
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws SwanMinioException
     * @return byte[]
     * @author zongf
     * @since 2021-07-30
     */
    public static void remove(MinioClient minioClient, String bucketName, String objectName) {
        try {

            RemoveObjectArgs args = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            minioClient.removeObject(args);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 获取对象字节数组, 对象或桶不存在时, 返回 null
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws SwanMinioException
     * @return byte[]
     * @author zongf
     * @since 2021-07-30
     */
    public static byte[] getObjectBytes(MinioClient minioClient, String bucketName, String objectName) {
        try {
            InputStream is = getObject(minioClient, bucketName, objectName);

            if(is == null) return null;

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int readLength = -1;
            while (( readLength = is.read(array)) > 0) {
                bos.write(array, 0, readLength);
            }
            bos.flush();
            return bos.toByteArray();
        }catch (Exception e) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, e);
        }
    }

    /** 获取对象状态, 桶不存在或文件不存在时，返回null
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @throws SwanMinioException
     * @return byte[]
     * @author zongf
     * @since 2021-07-30
     */
    public static StatObjectResponse stat(MinioClient minioClient, String bucketName, String objectName) {
        try {
            StatObjectArgs args = StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            StatObjectResponse response = minioClient.statObject(args);
            return response;
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                return null;
            }else if(MinioConstant.CODE_NO_SUCH_KEY.equals(ex.errorResponse().code())) {
                return null;
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 获取对象标签
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @throws SwanMinioException
     * @return byte[]
     * @author zongf
     * @since 2021-07-30
     */
    public static Map<String, String> getTags(MinioClient minioClient, String bucketName, String objectName) {
        try {
            GetObjectTagsArgs args = GetObjectTagsArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            Tags objectTags = minioClient.getObjectTags(args);

            return objectTags !=null ? objectTags.get() : null;

        } catch (Exception e) {
            if (e.getCause() instanceof ErrorResponseException) {
                ErrorResponseException ex = (ErrorResponseException) e.getCause();
                if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code()) || MinioConstant.CODE_NO_SUCH_KEY.equals(ex.errorResponse().code())) {
                    throw new SwanMinioException(MinioConstant.MSG_OBJECT_NOT_EXIST, ex);
                }
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, e);
        }
    }

    /** 为对象设置标签
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param tags 标签键值对
     * @throws SwanMinioException
     * @return byte[]
     * @author zongf
     * @since 2021-07-30
     */
    public static void setTags(MinioClient minioClient, String bucketName, String objectName, Map<String, String> tags) {
        try {
            SetObjectTagsArgs args = SetObjectTagsArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .tags(tags)
                    .build();
            minioClient.setObjectTags(args);
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code()) || MinioConstant.CODE_NO_SUCH_KEY.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_OBJECT_NOT_EXIST, ex);
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

    /** 为对象添加标签, 文件不存在或桶不存在, 抛出异常
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param newTags 标签键值对
     * @throws SwanMinioException
     * @return byte[]
     * @author zongf
     * @since 2021-07-30
     */
    public static void addTags(MinioClient minioClient, String bucketName, String objectName, Map<String, String> newTags) {
        try {
            Map<String, String> tags = getTags(minioClient, bucketName, objectName);
            if(tags == null) tags = new HashMap<>();
            newTags.putAll(tags);

            SetObjectTagsArgs args = SetObjectTagsArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .tags(newTags)
                    .build();
            minioClient.setObjectTags(args);
        } catch (Exception e) {
            if (e.getCause() instanceof ErrorResponseException) {
                ErrorResponseException ex = (ErrorResponseException) e.getCause();
                if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code()) || MinioConstant.CODE_NO_SUCH_KEY.equals(ex.errorResponse().code())) {
                    throw new SwanMinioException(MinioConstant.MSG_OBJECT_NOT_EXIST, ex);
                }
            }
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, e);
        }
    }

    /** 判断对象是否存在, 存在返回 true, 不存在返回 false
     * @param minioClient
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @throws SwanMinioException
     * @return byte[]
     * @author zongf
     * @since 2021-07-30
     */
    public static boolean exists(MinioClient minioClient, String bucketName, String objectName) {
        try {
            StatObjectArgs args = StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            StatObjectResponse stat = minioClient.statObject(args);
            return stat != null;
        } catch (ErrorResponseException ex) {
            if (MinioConstant.CODE_NO_SUCH_BUCKET.equals(ex.errorResponse().code())) {
                throw new SwanMinioException(MinioConstant.MSG_BUCKET_NOT_EXIST, ex);
            }else if(MinioConstant.CODE_NO_SUCH_KEY.equals(ex.errorResponse().code())){
                return false;
            }else {
                throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
            }
        } catch (Exception ex) {
            throw new SwanMinioException(MinioConstant.MSG_UNKNOWN, ex);
        }
    }

}
