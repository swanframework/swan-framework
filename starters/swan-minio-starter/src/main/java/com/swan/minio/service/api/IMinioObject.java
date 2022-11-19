package com.swan.minio.service.api;

import com.swan.minio.beans.ObjectPutResult;
import com.swan.minio.beans.ObjectState;
import io.minio.errors.MinioException;

import java.io.InputStream;
import java.util.Map;

/** minio 对象操作
 *      1. 命名规约:
 *          1) bucketName: 不能包含 / 等特殊字符, 使用连字符 - 拼接
 *          2) objectName: 必须以 . 结尾, 以此区分 目录 和 文件
 *
 * @author zongf
 * @since 2021-08-14
 */
public interface IMinioObject {

    /** 保存或更新对象。 当 bucket 不存时, 会抛出异常
     * @param bucketName 桶名称, 名称不能包含 / 等特殊字符, 可以包含连字符 -
     * @param objectName 对象名, 当对象名为 xx/xxx/xx.xx 时, 会在 bucket 下自动创建子目录
     * @param bytes 对象字节数组
     * @param userData 用户自定义数据
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public ObjectPutResult put(String bucketName, String objectName, byte[] bytes, Map<String, String> userData);

    /** 保存或更新对象。 当 bucket 不存时, 会抛出异常
     * @param bucketName 桶名称, 名称不能包含 / 等特殊字符, 可以包含连字符 -
     * @param objectName 对象名, 当对象名为 xx/xxx/xx.xx 时, 会在 bucket 下自动创建子目录
     * @param bytes 对象字节数组
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public ObjectPutResult put(String bucketName, String objectName, byte[] bytes);

    /** 保存或更新对象。 当 bucket 不存时, 会抛出异常
     * @param bucketName 桶名称, 名称不能包含 / 等特殊字符, 可以包含连字符 -
     * @param objectName 对象名, 当对象名为 xx/xxx/xx.xx 时, 会在 bucket 下自动创建子目录
     * @param is 对象输入流
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public ObjectPutResult put(String bucketName, String objectName, InputStream is);

    /** 保存或更新对象。 当 bucket 不存时, 会抛出异常
     * @param bucketName 桶名称, 名称不能包含 / 等特殊字符, 可以包含连字符 -
     * @param objectName 对象名, 当对象名为 xx/xxx/xx.xx 时, 会在 bucket 下自动创建子目录
     * @param is 对象输入流
     * @param userData 用户自定义数据
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public ObjectPutResult put(String bucketName, String objectName, InputStream is, Map<String, String> userData);

    /** 获取对象信息
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public InputStream get(String bucketName, String objectName);

    /** 获取对象字节数组
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public byte[] getBytes(String bucketName, String objectName);

    /** 获取对象字节数组
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public String getString(String bucketName, String objectName);

    /** 删除对象. 桶不存在时, 抛出异常. 不能删除非空目录
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public void remove(String bucketName, String objectName);

    /** 判断对象是否存在: 文件存在: true, 文件不存在: false, 桶不存在: 抛出异常
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public boolean exists(String bucketName, String objectName);

    /** 获取对象状态, 文件不存在时, 返回 null
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public ObjectState stat(String bucketName, String objectName);

    /** 获取对象标签列表
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public Map<String, String> getTags(String bucketName, String objectName);

    /** 给对象打标签,全部替换. 文件或桶不存在时, 抛出异常
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public void setTags(String bucketName, String objectName, Map<String, String> tags);

    /** 给对象添加标签,追加操作. 文件或桶不存在时, 抛出异常
     * @param bucketName 桶名称
     * @param objectName 对象名
     * @param addTags 新标签
     * @throws MinioException
     * @author zongf
     * @since 2021-08-14
     */
    public void addTags(String bucketName, String objectName, Map<String, String> addTags);

}
