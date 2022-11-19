package com.swan.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;

/** 缓存基类
 * @author zongf
 * @date 2021-05-17
 */
public abstract class AbsBaseCache {

    @Autowired
    private ICacheKeyGenerator cacheKeyGenerator;

    protected String getKey(String key) {
        return this.cacheKeyGenerator.getKey(key);
    }

}

