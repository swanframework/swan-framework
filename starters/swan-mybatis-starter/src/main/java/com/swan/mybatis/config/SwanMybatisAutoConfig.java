package com.swan.mybatis.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zongf
 * @date 2021-07-13
 */
@EnableConfigurationProperties({SbootMybatisProperties.class})
@Configuration(proxyBeanMethods = false)
public class SwanMybatisAutoConfig {

}
