package com.swan.redis.autoConfig;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.swan.redis.cache.DefaultCacheKeyGenerator;
import com.swan.redis.cache.ICacheKeyGenerator;
import com.swan.redis.executor.LockExecutor;
import com.swan.redis.locker.*;
import com.swan.redis.serializer.GzipFastJsonRedisSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/** Redis 自动化配置
 * @author zongf
 * @date 2021-05-14
 */
@Configuration
public class SwanRedisAutoConfig {

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public FastJsonRedisSerializer fastJsonRedisSerializer() {
        return new FastJsonRedisSerializer<>(String.class);
    }

    @Bean
    public GzipFastJsonRedisSerializer gzipFastJsonRedisSerializer() {
        return new GzipFastJsonRedisSerializer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "jsonRedisTemplate")
    public RedisTemplate<String, Object> jsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringRedisSerializer());
        template.setHashKeySerializer(stringRedisSerializer());
        template.setValueSerializer(fastJsonRedisSerializer());
        template.setHashValueSerializer(fastJsonRedisSerializer());
        return template;
    }

    @Bean
    @ConditionalOnMissingBean(name = "gzipRedisTemplate")
    public RedisTemplate<String, Object> gzipRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringRedisSerializer());
        template.setHashKeySerializer(stringRedisSerializer());
        template.setValueSerializer(gzipFastJsonRedisSerializer());
        template.setHashValueSerializer(gzipFastJsonRedisSerializer());
        return template;
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisLocker")
    public ILocker redisLocker(RedisTemplate jsonRedisTemplate) {
        return new RedisLocker(jsonRedisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisReentrantLocker")
    public ILocker redisReentrantLocker(RedisTemplate jsonRedisTemplate) {
        return new RedisReentrantLocker(jsonRedisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisRenewalLocker")
    public ILocker redisRenewalLocker(RedisTemplate jsonRedisTemplate) {
        return new RedisRenewalLocker(jsonRedisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisRenewalReentrantLocker")
    public ILocker redisRenewalReentrantLocker(RedisTemplate jsonRedisTemplate) {
        return new RedisRenewalReentrantLocker(jsonRedisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisLockExecutor")
    public LockExecutor redisLockExecutor(ILocker redisLocker) {
        return new LockExecutor(redisLocker);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisReentrantLockExecutor")
    public LockExecutor redisReentrantLockExecutor(ILocker redisReentrantLocker) {
        return new LockExecutor(redisReentrantLocker);
    }

    @Bean
    @ConditionalOnMissingBean(ICacheKeyGenerator.class)
    public ICacheKeyGenerator cacheKeyGenerator() {
        return new DefaultCacheKeyGenerator();
    }

}
