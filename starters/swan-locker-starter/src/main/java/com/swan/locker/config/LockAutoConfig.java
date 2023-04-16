package com.swan.locker.config;

import com.swan.locker.aspect.LockAspect;
import com.swan.locker.core.ILocker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 限流自动配置
 * @author zongf
 * @since  2022-06-16
 **/
@Configuration
public class LockAutoConfig {

    @Bean
    @ConditionalOnBean(ILocker.class)
    public LockAspect lockAspect() {
        return new LockAspect();
    }


}
