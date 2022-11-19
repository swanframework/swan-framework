package com.swan.redis.locker;

/** 应用锁
 * @author zongf
 * @date 2020-06-29
 */
public interface ILocker {

    /** 获取锁
     * @param lockName 锁名称
     * @param lockTime 锁定时间,超时后自动解锁. 单位秒
     * @return boolean
     * @author zongf
     * @date 2020-06-29
     */
    public boolean tryLock(String lockName, long lockTime);

    /** 获取锁
     * @param lockName 锁名称
     * @param lockTime 锁定时间,超时后自动解锁. 单位秒
     * @param maxRenewalTimes 最大续约次数, 默认10次
     * @return boolean
     * @author zongf
     * @date 2020-06-29
     */
    public boolean tryLock(String lockName, long lockTime, Integer maxRenewalTimes);

    /** 释放锁
     * @param lockName 锁名称
     * @return boolean
     * @author zongf
     * @date 2020-06-29
     */
    public boolean unlock(String lockName);

}
