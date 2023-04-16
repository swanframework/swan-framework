package com.swan.locker.core;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/** 单机locker
 * @author zongf
 * @since 2023-04-16
 **/
@Slf4j
public class JdkLocker extends AbsRetryLocker{

    private Map<String, String> lockCache = new ConcurrentHashMap<>();

    private Map<String, Long> timeCache= new ConcurrentHashMap<>();

    private ScheduledExecutorService executorService ;

    public JdkLocker() {
        // 默认1秒检测一次
        this(1,1, TimeUnit.SECONDS);
    }

    public JdkLocker(int threads, int period, TimeUnit timeUnit) {

        executorService = Executors.newScheduledThreadPool(threads);

        executorService.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            Iterator<String> iterator = timeCache.keySet().iterator();
            while (iterator.hasNext()) {
                String cacheKey = iterator.next();
                Long expireTime = timeCache.get(cacheKey);
                if (expireTime > now) {
                    deleteLock(cacheKey);
                }
            }

        }, 0L, period, timeUnit);

    }


    /** 尝试加锁
     * @param lockKey 锁key
     * @param lockValue 锁value
     * @param lockTime 毫秒
     * @param tryTimes 尝试次数
     * @return boolean
     * @author zongf
     */
    protected Boolean doLock(String lockKey, String lockValue, int lockTime, int tryTimes) {
        Boolean lockSuccess = false;

        try {
            String value = lockCache.putIfAbsent(lockKey, lockValue);

            if (Objects.nonNull(value)) {
                lockSuccess = true;
                timeCache.put(lockKey, System.currentTimeMillis() + lockTime * 1000);
            }

        } catch (Exception ex) {
            log.warn("分布式锁获取异常, lockKey:{}, lockValue:{}, lockTime:{}，尝试次数:{}", lockKey, lockValue, lockTime, tryTimes, ex);
        }

        return lockSuccess;
    }

    @Override
    public boolean releaseLock(String lockKey, String lockValue) {
        synchronized (lockKey) {
            if (!lockKey.equals(lockCache.get(lockKey))) {
                return false;
            }
        }
        lockCache.remove(lockKey);
        timeCache.remove(lockKey);
        return true;
    }

    @Override
    public boolean deleteLock(String lockKey) {
        lockCache.remove(lockKey);
        timeCache.remove(lockKey);
        return true;
    }

}
