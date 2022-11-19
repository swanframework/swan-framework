package com.swan.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author zongf
 * @date 2021-05-17
 */
public class ValueBaseCache extends AbsBaseCache{

    // 空值模式
    private static final String EMPTY_VALUE = "{}";

    @Autowired
    private RedisTemplate redisTemplate;

    /** 从缓存中查询或从数据库加载数据，采用空值模式解决缓存穿透问题
     * @param redisKey  redis 缓存key
     * @param expireTime 正常过期时间, 单位秒
     * @param emptyExpireTime 空值过期时间, 单位秒
     * @param dbFunction 数据库执行方法
     * @param dbMethodParam 数据库查询方法参数
     * @return R
     * @author zongf
     * @date 2021-05-12
     */
    public <T, R> R cacheOrLoad(String redisKey, Integer expireTime, Function<T, R> dbFunction, T dbMethodParam, Integer emptyExpireTime) {

        ValueOperations valueOps = redisTemplate.opsForValue();

        // 1. 从缓存中获取
        Object result = valueOps.get(redisKey);

        if (result == null) {
            // 1) 缓存不存在, 需要加载缓存
            synchronized (redisKey) {
                // 二次确认
                result =  (R) valueOps.get(redisKey);
                if (result == null) {
                    // 从数据库查询
                    R cacheValue = dbFunction.apply(dbMethodParam);

                    if (cacheValue != null) {
                        // 如果查询结果不为空，则设置缓存，并指定正常过期时间
                        valueOps.set(redisKey, cacheValue);
                        redisTemplate.expire(redisKey, expireTime, TimeUnit.SECONDS);
                    }else{
                        // 缓存穿透，设置空值过期时间
                        valueOps.set(redisKey, EMPTY_VALUE);
                        redisTemplate.expire(redisKey, emptyExpireTime, TimeUnit.SECONDS);
                    }
                    return cacheValue;

                }else if(EMPTY_VALUE.equals(result)){
                    // 2) 如果是空值模式，直接返回 null
                    return null;
                }else {
                    return (R) result;
                }
            }
        } else if(EMPTY_VALUE.equals(result)){
            // 2) 如果是空值模式，直接返回 null
            return null;
        } else {
            // 3) 返回缓存
            return (R) result;
        }
    }


}
