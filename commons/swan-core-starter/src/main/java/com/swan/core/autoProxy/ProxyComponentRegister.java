package com.swan.core.autoProxy;

import com.swan.core.scanner.EnableBaseBeanDefinitionRegistrar;
import lombok.extern.slf4j.Slf4j;

/** @EnableXXX 组件扫描器, @EnableXXX 注解需要有: String[] basePackages() 属性
 * @author zongf
 * @since 2020-11-26
 */
@Slf4j
public class ProxyComponentRegister extends EnableBaseBeanDefinitionRegistrar {

    public ProxyComponentRegister() {
        super(EnableAutoProxy.class, ProxyComponent.class, ProxyComponentFactoryBean.class);
    }

}
