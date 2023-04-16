package com.swan.locker.core;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author zongf
 * @since 2023-03-01
 **/
@Slf4j
public class RedisLocker extends AbsRetryLocker{

    private RedisTemplate redisTemplate;

    public RedisLocker(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean releaseLock(String lockKey, String lockValue) {
        // 预留接口
        throw new UnsupportedOperationException("预留接口, 暂不支持");
    }

    @Override
    public boolean deleteLock(String lockKey) {
        return this.redisTemplate.delete(lockKey);
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
            RedisCallback<Boolean> callback = (redisConnection -> redisConnection.
                    set(lockKey.getBytes(StandardCharsets.UTF_8), String.valueOf(lockValue).getBytes(StandardCharsets.UTF_8),
                            Expiration.milliseconds(TimeUnit.SECONDS.toMillis(lockTime)), RedisStringCommands.SetOption.SET_IF_ABSENT));

            lockSuccess = (Boolean) redisTemplate.execute(callback);

        } catch (Exception ex) {
            log.warn("分布式锁获取异常, lockKey:{}, lockValue:{}, lockTime:{}，尝试次数:{}", lockKey, lockValue, lockTime, tryTimes, ex);
        }

        return lockSuccess;
    }

}
