package com.swan.test.redis;

import com.swan.core.utils.ThreadUtil;
import com.swan.redis.locker.ILocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
class ReentrantLockTest {


    @Autowired
    private ILocker redisReentrantLocker;


    // 测试手动释放锁
    @Test
    public void testSuccess() {
        String lockName = "lock-handle";

        boolean lockSuccess = redisReentrantLocker.tryLock(lockName, 1500);
        Assertions.assertTrue(lockSuccess);

        ThreadUtil.sleep(3);

        new Thread(() -> {
            // 子线程申请锁失败
            boolean lockFail = redisReentrantLocker.tryLock(lockName, 1500);
            Assertions.assertFalse(lockFail);
        }).start();

        ThreadUtil.sleep(3);

        // 主线程申请成功
        lockSuccess = redisReentrantLocker.tryLock(lockName, 1500);
        Assertions.assertTrue(lockSuccess);

        lockSuccess = redisReentrantLocker.tryLock(lockName, 1500);
        Assertions.assertTrue(lockSuccess);

        // 主动释放锁
        redisReentrantLocker.unlock(lockName);
        redisReentrantLocker.unlock(lockName);
        redisReentrantLocker.unlock(lockName);
        redisReentrantLocker.unlock(lockName);

    }




}
