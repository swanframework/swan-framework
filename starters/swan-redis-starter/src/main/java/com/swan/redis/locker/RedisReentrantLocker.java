package com.swan.redis.locker;

import com.swan.core.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

/** 可重入锁
 * @author zongf
 * @date 2021-05-14
 */
@Slf4j
public class RedisReentrantLocker extends RedisLocker {

    public RedisReentrantLocker(RedisTemplate jsonRedisTemplate) {
        super(jsonRedisTemplate);
    }

    @Override
    public boolean tryLock(String lockName, long lockTime) {

        LockInfo lockInfo = lockInfoMap.get(lockName);

        // 如果 lockInfo 不为空，且被当前线程持有，则进行重入
        if (lockInfo != null && ThreadUtil.isCurrentThread(lockInfo.getThreadId())) {
            int lockTimes = lockInfo.getLockTimes().incrementAndGet();
            log.info("[redis 重入锁-加锁] 锁名称:{}, 重入次数:{}", lockInfo.getName(), lockTimes);
            return true;
        }

        return super.tryLock(lockName, lockTime);
    }

    @Override
    public boolean unlock(String lockName) {

        LockInfo lockInfo = lockInfoMap.get(lockName);

        if (lockInfo == null) {
            log.info("[redis 重入锁-解锁] 锁已失效, 锁名称:{}", lockName);
            return true;
        }

        if (ThreadUtil.isCurrentThread(lockInfo.getThreadId())) {
            // 如果不是最后一层，则返回
            int lockTimes = lockInfo.getLockTimes().decrementAndGet();
            if (lockTimes > 0) {
                log.info("[redis 重入锁-解锁] 锁名称:{}, 重入次数:{}", lockInfo.getName(), lockTimes);
                return true;
            }
        }

        return super.unlock(lockName);
    }




}
