package com.swan.redis.cache;

public interface ICacheKey {

    /** key 名称 */
    public String key();

    /** 过期时间 */
    public Long expireTime();

}
