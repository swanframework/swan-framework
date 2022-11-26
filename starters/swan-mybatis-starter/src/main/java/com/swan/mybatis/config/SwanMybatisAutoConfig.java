package com.swan.mybatis.config;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zongf
 * @since 2021-07-13
 */
@Import(SwanSqlSessionFactory.class)
@EnableConfigurationProperties({SwanMybatisProperties.class})
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(MybatisAutoConfiguration.class)
public class SwanMybatisAutoConfig {



}
