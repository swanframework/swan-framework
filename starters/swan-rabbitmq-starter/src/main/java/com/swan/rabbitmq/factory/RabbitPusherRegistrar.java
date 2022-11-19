package com.swan.rabbitmq.factory;

import com.swan.core.scanner.EnableAnnotationScanner;
import com.swan.core.scanner.EnableBaseBeanDefinitionRegistrar;
import com.swan.rabbitmq.anno.EnableRabbitPusher;
import com.swan.rabbitmq.anno.RabbitPusher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

@Slf4j
public class RabbitPusherRegistrar extends EnableBaseBeanDefinitionRegistrar {

	public RabbitPusherRegistrar() {
		super(EnableRabbitPusher.class, RabbitPusher.class, RabbitPusherBeanFactory.class);
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		// 1. 扫描 @ProxyComponent 组件
		EnableAnnotationScanner scanner = new EnableAnnotationScanner(this.resourceLoader);
		scanner.addIncludeAnnotationType(RabbitPusher.class);

		// 2. 获取扫描包
		Set<String> scanPackages = this.getBasePackages(registry, importingClassMetadata, EnableRabbitPusher.class);
		Set<BeanDefinition> beanDefinitions = scanner.doScanPackages(scanPackages);

		// 3. 注册bean定义
		for (BeanDefinition beanDefinition : beanDefinitions) {
			String beanClassName = beanDefinition.getBeanClassName();

			BeanDefinition genericBeanDefinition = BeanDefinitionBuilder
					.genericBeanDefinition(RabbitPusherBeanFactory.class)
					.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE)
					.addPropertyValue("type", beanClassName)
					.getBeanDefinition();

			// 向spring 容器注册bean
			registry.registerBeanDefinition(beanClassName, genericBeanDefinition);
		}
	}

}