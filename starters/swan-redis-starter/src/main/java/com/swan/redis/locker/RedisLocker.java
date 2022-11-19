package com.swan.redis.locker;

import com.swan.redis.constant.LuaScriptConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/** redis 锁
 * @author zongf
 * @date 2021-05-14
 */
@Slf4j
public class RedisLocker implements ILocker {

    private RedisTemplate jsonRedisTemplate;

    protected volatile ConcurrentHashMap<String, LockInfo> lockInfoMap = new ConcurrentHashMap<>();

    public RedisLocker(RedisTemplate jsonRedisTemplate) {
        this.jsonRedisTemplate = jsonRedisTemplate;
    }

    @Override
    public boolean tryLock(String lockName, long lockTime) {
        return tryLock(lockName, lockTime, null);
    }

    @Override
    public boolean tryLock(String lockName, long lockTime, Integer maxRenewalTimes) {

        ValueOperations valOps = jsonRedisTemplate.opsForValue();

        LockInfo lockInfo = LockInfo.newInstance(lockName, lockTime, Thread.currentThread().getId(), maxRenewalTimes);

        boolean lockSuccess = valOps.setIfAbsent(lockName, lockInfo.getId(), lockTime, TimeUnit.SECONDS).booleanValue();

        log.info("[redis锁-获取] 锁名称:{}, 获取结果:{}", lockName, lockSuccess);

        if (lockSuccess) {
            // 储存锁信息
            lockInfoMap.put(lockName, lockInfo);
        }

        return lockSuccess;
    }

    @Override
    public boolean unlock(String lockName) {

        LockInfo lockInfo = lockInfoMap.remove(lockName);

        if (lockInfo == null) {
            return true;
        }

        // 锁以自动释放
        if (System.currentTimeMillis() - lockInfo.getStartTime() > lockInfo.getLockTime() * 1000) {
            log.info("[redis-释放] 已超时，自动解锁!", lockName);
            return true;
        }

        // 执行lua 脚本
        Long result = (Long) jsonRedisTemplate.execute(LuaScriptConstant.UN_LOCK, Collections.singletonList(lockName), lockInfo.getId());
        log.info("[redis锁-释放] 锁名称:{}, 释放结果:{}", lockName, result);

        return result !=null && result > 0;
    }

}
