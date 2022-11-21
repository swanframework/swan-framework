package com.swan.test.redis;

import com.swan.core.utils.ThreadUtil;
import com.swan.redis.locker.ILocker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ActiveProfiles("dev")
@SpringBootTest
class LockerTest {

    @Autowired
    private RedisTemplate jsonRedisTemplate;

    @Autowired
    private ILocker redisLocker;

    @Autowired
    private ILocker redisReentrantLocker;

    // 测试手动释放锁
    @Test
    public void testHandleUnlock() {
        String lockName = "lock-handle";

        boolean lockSuccess = redisLocker.tryLock(lockName, 30 * 1000);
        Assertions.assertTrue(lockSuccess);

        boolean unlockSuccess = redisLocker.unlock(lockName);
        Assertions.assertTrue(unlockSuccess);
    }

    // 测试自动释放锁
    @Test
    public void testAutoUnlock() {
        String lockName = "lock-auto";

        boolean lockSuccess = redisLocker.tryLock(lockName, 5 * 1000);
        Assertions.assertTrue(lockSuccess);

        ThreadUtil.sleep(10);

        boolean unlockSuccess = redisLocker.unlock(lockName);
        Assertions.assertTrue(unlockSuccess);
    }

    // 测试手动释放锁
    @Test
    public void testError() {
        String lockName = "lock-error";

        boolean lockSuccess = redisLocker.tryLock(lockName, 60 * 1000);
        Assertions.assertTrue(lockSuccess);

        System.out.println("debug 暂停, 手工修改锁的值");

        boolean unlockSuccess = redisLocker.unlock(lockName);
        Assertions.assertTrue(unlockSuccess);
    }


    @Test
    public void test() {


        String lockName = "lock";

        Callable cb1 = () ->{

            boolean firstLock = redisLocker.tryLock(lockName, 5 * 1000);
            Assertions.assertTrue(firstLock);

            ThreadUtil.sleep(20);

            redisLocker.unlock(lockName);

           return null;
        } ;

        Callable cb2 = () ->{

            boolean firstLock = redisLocker.tryLock(lockName, 60 * 1000);
            Assertions.assertTrue(firstLock);

            ThreadUtil.sleep(20);
            redisLocker.unlock(lockName);

            return null;
        } ;

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(cb1);

        ThreadUtil.sleep(10);

        executorService.submit(cb2);

        ThreadUtil.sleep(Integer.MAX_VALUE);
    }


}
