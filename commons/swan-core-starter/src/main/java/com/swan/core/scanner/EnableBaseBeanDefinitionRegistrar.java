package com.swan.core.scanner;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/** BeanDefinition 自定义注册器
 *  1) 不能实现ApplicationContextAware接口, 不能回调. 因为ImportBeanDefinitionRegistrar 的执行时机为: refresh -> invokeBeanFactoryPostProcessors 中, 在bean 初始化之前
 *  2) @Enabelxxx 注解需要有 basePackages 属性
 * @author zongf
 * @since 2020-11-26
 */
public abstract class EnableBaseBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    protected ResourceLoader resourceLoader;

    // 开启功能注解: @Enablexxx
    protected Class<? extends Annotation> enableAnnotationClass;

    // 标识功能注解, 如: @ProxyComponent, @RabbitPusher
    protected Class<? extends Annotation> componentAnnotationClass;

    // 创建功能注解bean的工厂类
    protected Class<? extends BaseFactoryBean> beanFactoryClass;

    public EnableBaseBeanDefinitionRegistrar(Class<? extends Annotation> enableAnnotationClass,
                                             Class<? extends Annotation> componentAnnotationClass,
                                             Class<? extends BaseFactoryBean> beanFactoryClass) {
        this.enableAnnotationClass = enableAnnotationClass;
        this.beanFactoryClass = beanFactoryClass;
        this.componentAnnotationClass = componentAnnotationClass;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){

        // 1. 扫描 @ProxyComponent 组件
        EnableAnnotationScanner scanner = new EnableAnnotationScanner(this.resourceLoader);
        scanner.addIncludeAnnotationType(this.componentAnnotationClass);

        // 2. 获取扫描包
        Set<String> scanPackages = this.getBasePackages(registry, importingClassMetadata, this.enableAnnotationClass);

        // 3. 扫描组件
        Set<BeanDefinition> beanDefinitions = scanner.doScanPackages(scanPackages);

        // 3. 注册bean定义
        for (BeanDefinition beanDefinition : beanDefinitions) {
            // 如果未定义, 则注册bean定义信息, 否则忽略. 解决重复使用@Enablexxx注解问题
            if (!registry.containsBeanDefinition(beanDefinition.getBeanClassName())) {
                doRegisterBeanDefinition(registry, beanDefinition);
            }
        }
    }



    /** 获取扫描包
     * @param registry
     * @param importingMetadata
     * @param clz 注解
     * @return Set
     * @author zongf
     * @since 2020-11-26
     */
    protected Set<String> getBasePackages(BeanDefinitionRegistry registry, AnnotationMetadata importingMetadata, Class clz) {

        Set<String> basePackages = new LinkedHashSet<>();

        // 添加注解定义的包
        Map<String, Object> attributes = importingMetadata .getAnnotationAttributes(clz.getCanonicalName());
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }

        // 如果注解未指定扫描包, 则默认添加启动类所在的包
        if (basePackages.isEmpty()) {
            // 添加@Enablexxx 注解修饰的类所在的包
            basePackages.add(ClassUtils.getPackageName(importingMetadata.getClassName()));      // 注解所在的包

            // 添加启动类所在包
            if (registry instanceof ConfigurableListableBeanFactory) {
                basePackages.addAll(getSpringBootApplicationPackage((ConfigurableListableBeanFactory) registry));
            }
        }

        return basePackages;
    }

    /** 获取springboot启动类所在包
     * @param beanFactory
     * @return Set<String>
     * @author zongf
     * @since 2020-12-11
     */
    protected Set<String> getSpringBootApplicationPackage(ConfigurableListableBeanFactory beanFactory){
        Set<String> packages = new HashSet<>();
        Class clz = null;
        try {
            // 加载SpringBootApplication, 如果没有则表示非springboot 环境
            clz = Class.forName("org.springframework.boot.autoconfigure.SpringBootApplication");

            for (String beanName : beanFactory.getBeanNamesForAnnotation(clz)) {
                String beanClassName = beanFactory.getBeanDefinition(beanName).getBeanClassName();
                packages.add(ClassUtils.getPackageName(beanClassName));
            }

        } catch (ClassNotFoundException e) {
            // do nothing
        }
        return packages;
    }



    /** 注册bean定义信息, 子类可重写
     * @param registry
     * @param beanDefinition
     * @author zongf
     * @since 2020-12-11
     */
    protected void doRegisterBeanDefinition(BeanDefinitionRegistry registry, BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();

        BeanDefinition genericBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(this.beanFactoryClass)
                .setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE)
                .addPropertyValue("type", beanClassName)
                .getBeanDefinition();

        // 向spring 容器注册bean
        registry.registerBeanDefinition(beanClassName, genericBeanDefinition);
    }

}
