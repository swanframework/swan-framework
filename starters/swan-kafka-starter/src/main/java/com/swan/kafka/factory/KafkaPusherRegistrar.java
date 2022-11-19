package com.swan.kafka.factory;

import com.swan.core.scanner.EnableAnnotationScanner;
import com.swan.core.scanner.EnableBaseBeanDefinitionRegistrar;
import com.swan.kafka.anno.EnableKafkaPusher;
import com.swan.kafka.anno.KafkaPusher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

@Slf4j
public class KafkaPusherRegistrar extends EnableBaseBeanDefinitionRegistrar {

    public KafkaPusherRegistrar() {
        super(EnableKafkaPusher.class, KafkaPusher.class, KafkaPusherBeanFactory.class);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 1. 扫描 @ProxyComponent 组件
        EnableAnnotationScanner scanner = new EnableAnnotationScanner(this.resourceLoader);
        scanner.addIncludeAnnotationType(KafkaPusher.class);

        // 2. 获取扫描包
        Set<String> scanPackages = this.getBasePackages(registry, importingClassMetadata, EnableKafkaPusher.class);
        Set<BeanDefinition> beanDefinitions = scanner.doScanPackages(scanPackages);

        // 3. 注册bean定义
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanClassName = beanDefinition.getBeanClassName();

            BeanDefinition genericBeanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(KafkaPusherBeanFactory.class)
                    .setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE)
                    .addPropertyValue("type", beanClassName)
                    .getBeanDefinition();

            // 向spring 容器注册bean
            registry.registerBeanDefinition(beanClassName, genericBeanDefinition);
        }
    }

}
