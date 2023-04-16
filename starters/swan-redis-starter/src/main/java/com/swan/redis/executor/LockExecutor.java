package com.swan.redis.executor;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.redis.exception.LockException;
import com.swan.redis.locker.ILocker;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;

/** 方法加锁执行器
 * @author zongf
 * @date 2021-05-14
 */
@Slf4j
public class LockExecutor {

    private ILocker locker;

    public LockExecutor(ILocker locker) {
        this.locker = locker;
    }

    /** 加锁执行有返回值方法
     * @param function 目标方法
     * @param param 目标方法参数
     * @param lockName 锁名称
     * @param lockTime 锁定时间, 单位毫秒, 过期自动释放锁
     * @return R 方法返回值
     * @author zongf
     * @date 2021-05-14
     */
    public <T, R> R execute(String lockName, int lockTime, Function<T, R> function, T param) {

        try {

            // 尝试获取锁
            boolean lockSuccess = locker.tryLock(lockName, lockTime);

            if (!lockSuccess) {
                throw new LockException(ExceptionCodeEnum.LOCK.code(), "分布式锁获取异常");
            }

            // 执行业务逻辑
            return function.apply(param);
        } catch (Exception ex) {
            throw ex;
        } finally {
            // 释放锁
            try {
                locker.unlock(lockName);
            } catch (Exception ex) {
                // Do Nothing
            }
        }
    }

    /** 加锁执行有返回值方法
     * @param consumer 目标方法
     * @param param 目标方法参数
     * @param lockName 锁名称
     * @param lockTime 锁定时间, 单位毫秒, 过期自动释放锁
     * @author zongf
     * @date 2021-05-14
     */
    public <T> void execute(String lockName, int lockTime, Consumer<T> consumer, T param) {

        try {

            // 尝试获取锁
            boolean lockSuccess = locker.tryLock(lockName, lockTime);

            if (!lockSuccess) {
                throw new LockException(ExceptionCodeEnum.LOCK.code(), "分布式锁获取异常");
            }

            // 执行业务逻辑
            consumer.accept(param);
        } catch (Exception ex) {
            throw ex;
        } finally {
            // 释放锁
            try {
                locker.unlock(lockName);
            } catch (Exception ex) {
                // Do Nothing
            }
        }
    }

}
