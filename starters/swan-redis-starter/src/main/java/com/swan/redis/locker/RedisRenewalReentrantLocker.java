package com.swan.redis.locker;

import com.swan.redis.thread.LockRenewalTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** redis 可重入锁, 自动续约
 * @author zongf
 * @date 2021-05-14
 */
@Slf4j
public class RedisRenewalReentrantLocker extends RedisReentrantLocker{

    protected ExecutorService executorService = Executors.newSingleThreadExecutor();

    public RedisRenewalReentrantLocker(RedisTemplate jsonRedisTemplate) {
        super(jsonRedisTemplate);
        executorService.submit(new LockRenewalTask("可重入锁", jsonRedisTemplate, lockInfoMap));
    }

}
