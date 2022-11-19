package com.swan.core.components;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author zongf
 * @since 2021-11-26
 */
public class BeanFactoryHolder implements BeanFactoryAware {

    protected DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof DefaultListableBeanFactory) {
            this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        }
    }

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
