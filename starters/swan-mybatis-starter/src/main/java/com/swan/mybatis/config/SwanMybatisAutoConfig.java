package com.swan.mybatis.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zongf
 * @since 2021-07-13
 */
@EnableConfigurationProperties({SwanMybatisProperties.class})
@Configuration(proxyBeanMethods = false)
public class SwanMybatisAutoConfig {

}
