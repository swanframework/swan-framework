package com.swan.env.core;

import org.springframework.beans.factory.FactoryBean;

/** 将单实例 bean 也注册到 spring 容器中
 * @author zongf
 * @since 2022-11-08
 **/
public class SwanEnvironmentFactoryBean implements FactoryBean<SwanEnvironment> {

    @Override
    public SwanEnvironment getObject() throws Exception {
        return SwanEnvironment.getInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return ISwanEnvironment.class;
    }

}
