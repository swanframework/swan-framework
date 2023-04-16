package com.swan.locker.core;

/**  限流器, 控制限流访问次数，默认使用 redis 实现
 * @author zongf
 * @since 2022-06-16
 **/
public interface ILocker {

    /** 尝试加锁
     * @param lockKey 锁标识
     * @param lockValue 锁value，防误删除
     * @param lockTime 加锁时间
     * @param retryTimes 重试次数
     * @param retryInterval 重试间隔
     * @return 是否成功加锁
     * @author zongf
     * @since 2023-03-01
     */
    boolean tryLock(String lockKey, String lockValue, int lockTime, int retryTimes, int retryInterval);

    /** 释放锁
     * @param lockKey 锁key
     * @param lockValue 锁value，防误删除
     * @return 是否成功释放锁
     * @author zongf
     * @since 2023-03-01
     */
    boolean releaseLock(String lockKey, String lockValue);

    /** 强制删除锁
     * @param lockKey 锁key
     * @return 是否成功释放锁
     * @author zongf
     * @since 2023-03-01
     */
    boolean deleteLock(String lockKey);

}
