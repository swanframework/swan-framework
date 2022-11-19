package com.swan.redis.locker;

import com.swan.core.utils.UUIDUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

/** 锁信息
 * @author zongf
 * @date 2021-05-14
 */
@Setter @Getter
public class LockInfo {

    /** 唯一id */
    private String id;

    /** 锁的资源名称 */
    private String name;

    /** 开始时间 */
    private Long startTime;

    /** 锁定时间 */
    private Long lockTime;

    /** 锁重入次数 */
    private AtomicInteger lockTimes = new AtomicInteger(0);

    /** 已续约次数 */
    private AtomicInteger renewalTimes = new AtomicInteger(0);

    /** 最大续约次数 */
    private Integer maxRenewal;

    /** 线程id */
    private Long threadId;

    /** 默认最大续约次数 */
    private static final Integer DEFAULT_MAX_RENEWAL_TIMES = 10;

    private LockInfo() {
    }

    public static LockInfo newInstance(String name, Long lockTime, long threadId) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setId(UUIDUtil.random());
        lockInfo.setName(name);
        lockInfo.setStartTime(System.currentTimeMillis());
        lockInfo.setLockTime(lockTime);
        lockInfo.getLockTimes().incrementAndGet();
        lockInfo.setThreadId(Long.valueOf(threadId));
        return lockInfo;
    }

    public static LockInfo newInstance(String name, Long expireTime, long threadId, Integer maxRenewal) {
        LockInfo lockInfo = newInstance(name, expireTime, threadId);
        lockInfo.setMaxRenewal(maxRenewal != null && maxRenewal > 0 ? maxRenewal : DEFAULT_MAX_RENEWAL_TIMES);
        return lockInfo;
    }

}
