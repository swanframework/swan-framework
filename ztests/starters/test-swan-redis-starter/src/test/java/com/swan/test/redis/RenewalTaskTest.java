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
class RenewalTaskTest {


    @Autowired
    private ILocker redisLocker;

    @Autowired
    private ILocker redisRenewalLocker;

    // 测试手动释放锁
    @Test
    public void testHandleUnlock() {
        String lockName = "lock-handle";

        boolean lockSuccess = redisRenewalLocker.tryLock(lockName, 15, 5);
        Assertions.assertTrue(lockSuccess);


        ThreadUtil.sleep(50);

        // 主动释放锁
        redisRenewalLocker.unlock(lockName);

        ThreadUtil.sleep(Integer.MAX_VALUE);

    }

    // 测试死锁, 5次续约之后, 自动释放锁
    @Test
    public void testDeadLock() {

        boolean lockSuccess = redisRenewalLocker.tryLock("lock-7", 7, 9);
        Assertions.assertTrue(lockSuccess);

        lockSuccess = redisRenewalLocker.tryLock("lock-11", 11, 6);
        Assertions.assertTrue(lockSuccess);

        lockSuccess = redisRenewalLocker.tryLock("lock-15", 15, 5);
        Assertions.assertTrue(lockSuccess);


        ThreadUtil.sleep(Integer.MAX_VALUE);
    }

}
