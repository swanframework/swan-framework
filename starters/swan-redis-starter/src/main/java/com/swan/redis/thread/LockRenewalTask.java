package com.swan.redis.thread;

import com.swan.core.utils.ThreadUtil;
import com.swan.redis.constant.LuaScriptConstant;
import com.swan.redis.locker.LockInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/** 锁自动续约线程
 * @author zongf
 * @date 2021-05-14
 */
@Getter
@Slf4j
public class LockRenewalTask implements Runnable {

    private String taskName;

    private RedisTemplate redisTemplate;

    private ConcurrentHashMap<String, LockInfo> lockInfoMap;

    // 检测时间间隔
    private Integer checkTime = 10;

    public LockRenewalTask(String taskName, RedisTemplate redisTemplate, ConcurrentHashMap<String, LockInfo> lockInfos) {
        this.taskName = taskName;
        this.redisTemplate = redisTemplate;
        this.lockInfoMap = lockInfos;
    }

    @Override
    public void run() {

        while (true) {
            if (lockInfoMap.size() > 0) {
                log.info("[redis {}-续约] 当前分布式锁数量:{}", taskName, lockInfoMap.size());
            }

            try {

                Iterator<LockInfo> iterator = lockInfoMap.values().iterator();

                while (iterator.hasNext()) {

                    LockInfo lockInfo = iterator.next();

                    try {

                        // 预防死锁
                        if (lockInfo.getRenewalTimes().get() >= lockInfo.getMaxRenewal()) {
                            iterator.remove();
                            log.info("[redis {}-续约] 已达到最大续约次数, 自动删除锁:{}", taskName, lockInfo.getName());
                            continue;
                        }

                        Long result = (Long) redisTemplate.execute(LuaScriptConstant.RENEWAL_LOCK,
                                Collections.singletonList(lockInfo.getName()), lockInfo.getId(), lockInfo.getLockTime(), checkTime);

                        log.info("[redis {}-续约] 锁:{}, 续约结果:{}", taskName, lockInfo.getName(), result);

                        if (result != null && result > 0) {
                            int renewalTimes = lockInfo.getRenewalTimes().incrementAndGet();
                            log.info("[redis {}-续约] 续约成功, 锁:{}, 续约次数:{}", taskName, lockInfo.getName(), renewalTimes);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        log.error("[redis {}-续约] lua脚本执行异常, 锁:{}, 异常原因:{}", taskName, lockInfo.getName(), ex.getMessage());
                    }

                }
            } catch (Exception ex) {
                // Do Nothing. 防止任务终端
            }

            ThreadUtil.sleep(3);
        }
    }

}
