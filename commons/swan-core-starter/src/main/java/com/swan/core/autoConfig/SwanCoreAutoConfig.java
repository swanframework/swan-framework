package com.swan.core.autoConfig;

import com.swan.core.components.ApplicationContextHolder;
import com.swan.core.components.BeanFactoryHolder;
import com.swan.core.components.ResourceScanner;
import com.swan.core.threadPool.ThreadPoolRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 自动化配置
 * @author zongf
 * @since 2022-11-07
 **/
@Configuration(proxyBeanMethods = false)
public class SwanCoreAutoConfig {

    @Bean
    public ApplicationContextHolder applicationContextHolder(){
        return new ApplicationContextHolder();
    }

    @Bean
    public ResourceScanner resourceScanner(){
        return new ResourceScanner();
    }

    @Bean
    public BeanFactoryHolder beanFactoryHolder() {
        return new BeanFactoryHolder();
    }

    @Bean
    public ThreadPoolRegister threadPoolRegister() {
        return new ThreadPoolRegister();
    }

}
