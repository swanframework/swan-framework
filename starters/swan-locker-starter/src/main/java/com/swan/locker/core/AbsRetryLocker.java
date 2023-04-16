package com.swan.locker.core;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zongf
 * @since 2023-03-01
 **/
@Slf4j
public abstract class AbsRetryLocker implements ILocker{

    @Override
    public boolean tryLock(String lockKey, String lockValue, int lockTime, int retryTimes, int retryInterval) {
        // 当前重试次数
        int tryTimes = 0;

        // 尝试加锁
        boolean lockSuccess = doLock(lockKey, lockValue, lockTime, tryTimes);

        while (!lockSuccess && tryTimes++ < retryTimes) {
            //休眠
            ThreadUtil.sleep(retryInterval);

            // 尝试加锁
            lockSuccess = doLock(lockKey, lockValue, lockTime, tryTimes);

            log.info("尝试获取分布式锁, lockKey:{}, lockValue:{}, lockTime:{}，尝试次数:{}", lockKey, lockValue, lockTime, tryTimes);
        }

        return lockSuccess;
    }

    /** 尝试加锁
     * @param lockKey 锁key
     * @param lockValue 锁value
     * @param lockTime 毫秒
     * @param tryTimes 尝试次数
     * @return boolean
     * @author zongf
     */
    protected abstract Boolean doLock(String lockKey, String lockValue, int lockTime, int tryTimes);
}
