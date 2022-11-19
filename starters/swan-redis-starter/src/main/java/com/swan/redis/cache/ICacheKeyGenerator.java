package com.swan.redis.cache;

/** 缓存key生成器
 * @author zongf
 * @date 2021-05-17
 */
public interface ICacheKeyGenerator {

    /** 缓存key生成器
     * @param key
     * @return String
     * @author zongf
     * @date 2021-05-17
     */
    public String getKey(String key);

}
