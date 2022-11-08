package com.swan.env.config;

import com.swan.env.core.SwanEnvironmentFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/** 自动化配置
 * @author zongf
 * @since 2022-11-07
 **/
@Configuration(proxyBeanMethods = false)
@Import(SwanEnvironmentFactoryBean.class)
public class SwanEnvAutoConfig {


}
