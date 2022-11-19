package com.swan.core.scanner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 基础工厂Bean, 内置属性和应用上下文
 * @author zongf
 * @since 2020-11-26
 */
public abstract class BaseFactoryBean implements FactoryBean<Object>, ApplicationContextAware {

    // 代理的接口类型
    protected Class<Object> type;

    // 应用上下文
    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setType(Class<Object> type) {
        this.type = type;
    }

    @Override
    public Class<Object> getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<Object> getType() {
        return type;
    }

}
