package com.swan.core.components;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;
import java.util.Map;

/** 容器上下文工具
 * @author zongf
 * @since 2020-11-25
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
        ApplicationContextHolder.environment = applicationContext.getEnvironment();
    }

    /** 根据类型获取bean
     * @param type 类型
     * @return T bean
     * @author zongf
     * @since 2020-12-03
     */
    public <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    /** 根据名称获取bean
     * @param beanName 名称
     * @return Object bean
     * @author zongf
     * @since 2020-12-03
     */
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /** 根据类型和名称获取bean
     * @param beanName bean 名称
     * @param type 类型
     * @return T bean
     * @author zongf
     * @since 2020-12-03
     */
    public <T> T getBean(String beanName, Class<T> type) {
        return applicationContext.getBean(beanName, type);
    }

    /** 根据类型获取bean 列表
     * @param type 类型
     * @return Map<String, T>
     * @author zongf
     * @since 2020-12-03
     */
    public <T> Map<String, T> getBeans(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /** 根据类型获取bean 的名称列表
     * @param type 类型
     * @return String[]
     * @author zongf
     * @since 2020-12-03
     */
    public <T> String[] getBeanNames(Class<T> type) {
        return applicationContext.getBeanNamesForType(type);
    }

    /** 获取指定注解修饰的bean
     * @param annoType 类型
     * @return Map<String, Object>
     * @author zongf
     * @since 2020-12-03
     */
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annoType) {
        return applicationContext.getBeansWithAnnotation(annoType);
    }

    /** 获取所有的BeanDefinition的名称列表
     * @return String[]
     * @author zongf
     * @since 2020-12-03
     */
    public String[] getBeanDefinitionNames() {
        return applicationContext.getBeanDefinitionNames();
    }

    /** 获取激活的 profile 列表
     * @return String[]
     * @author zongf
     * @since 2020-12-03
     */
    public String[] getActiveProfiles() {
        return environment.getActiveProfiles();
    }

    /** 获取默认的 profile 列表
     * @return String[]
     * @author zongf
     * @since 2020-12-03
     */
    public String[] getDefaultProfiles() {
        return environment.getDefaultProfiles();
    }

    /** 获取属性值
     * @param propName 属性名
     * @return String
     * @author zongf
     * @since 2020-12-03
     */
    public String getProperty(String propName) {
        return environment.getProperty(propName);
    }

    /** 获取属性值
     * @param propName 属性名
     * @param type 属性类型
     * @return String
     * @author zongf
     * @since 2020-12-03
     */
    public <T> T getProperty(String propName, Class<T> type) {
        return environment.getProperty(propName, type);
    }

}
