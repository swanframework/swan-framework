package com.swan.minio.constant;

/**
 * @author zongf
 * @date 2021-11-03
 */
public class MinioConstant {

    /** 没有此对象 */
    public static final String CODE_NO_SUCH_KEY = "NoSuchKey";

    /** 没有此 bucket*/
    public static final String CODE_NO_SUCH_BUCKET = "NoSuchBucket";

    /** 没有此 bucket*/
    public static final String BUCKET_ALREADY_OWNED_BY_YOU = "BucketAlreadyOwnedByYou";

    /** 桶不为空 */
    public static final String BUCKET_NOT_EMPTY = "BucketNotEmpty";



    /** 异常提示: 桶不存在 */
    public static final String MSG_BUCKET_NOT_EXIST = "桶不存在";

    /** 异常提示: 文件不存在 */
    public static final String MSG_OBJECT_NOT_EXIST = "文件不存在";

    /** 异常提示: 文件系统未知异常 */
    public static final String MSG_UNKNOWN = "文件系统未知异常";

    /** 桶已经存在 */
    public static final String MSG_BUCKET_ALREADY_EXISTS = "桶已经存在";

    /** 桶不为空 */
    public static final String MSG_BUCKET_NOT_EMPTY = "桶不为空";

}
