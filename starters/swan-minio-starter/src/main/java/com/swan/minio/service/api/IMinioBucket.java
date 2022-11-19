package com.swan.minio.service.api;

import java.util.List;

/** MinIo 桶操作
 *  1) minio 桶相当于数据库一样, 不建议通过 api 直接创建管理, 建议在浏览器创建管理.
 *  2) minio 删除桶时, 桶内元素必须为空
 *
 * @author zongf
 * @since 2021-07-30
 */
public interface IMinioBucket {

    /** 创建 bucket
     * @param bucketName bucket 名称，支持_-, 尽量不要使用其它特殊字符
     * @author zongf
     * @since 2021-07-30
     */
    public void make(String bucketName);

    /** 判断 bucket 是否存在
     * @param bucketName
     * @return Boolean
     * @author zongf
     * @since 2021-07-30
     */
    public Boolean exists(String bucketName);

    /** 删除空的 bucket
     * @param bucketName
     * @author zongf
     * @since 2021-07-30
     */
    public void remove(String bucketName);

    /** 获取所有的 bucket 名称
     * @return List<String>
     * @author zongf
     * @since 2021-07-30
     */
    public List<String> listBuckets();

    /** 获取 bucket 对象名称列表
     * @param bucketName 桶名称
     * @param objectNamePrefix 对象名称前缀
     * @return List<String>
     * @author zongf
     * @since 2021-07-30
     */
    public List<String> listObjects(String bucketName, String objectNamePrefix);

    /** 设置 bucket 访问策略
     * @param bucketName 桶名称
     * @param policyConfig 策略
     * @author zongf
     * @since 2021-07-30
     */
    public void setPolicy(String bucketName, String policyConfig);

    /** 获取 bucket 访问策略
     * @param bucketName 桶名称
     * @return String
     * @author zongf
     * @since 2021-07-30
     */
    public String getPolicy(String bucketName);

    /** 删除 bucket 访问策略
     * @param bucketName 桶名称
     * @return String
     * @author zongf
     * @since 2021-07-30
     */
    public void deletePolicy(String bucketName);

}
