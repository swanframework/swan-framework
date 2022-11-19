package com.swan.redis.cache;

/** 默认key生成器
 * @author zongf
 * @date 2021-05-17
 */
public class DefaultCacheKeyGenerator implements ICacheKeyGenerator {

    @Override
    public String getKey(String key) {
        return key;
    }

}
