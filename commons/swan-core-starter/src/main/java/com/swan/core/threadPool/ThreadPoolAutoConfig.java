package com.swan.core.threadPool;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**线程池自动配置
 * @author zongf
 * @since 2021-11-26
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ThreadPoolsProperties.class)
public class ThreadPoolAutoConfig {



}
