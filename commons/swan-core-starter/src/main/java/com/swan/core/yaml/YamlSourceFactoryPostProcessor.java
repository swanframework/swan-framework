package com.swan.core.yaml;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.parsing.FailFastProblemReporter;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/** 解析 yaml 格式的配置信息 <br/>
 *  实现参考: ConfigurationClassParser, ConfigurationClassPostProcessor <br/>
 *  执行方式: invokeBeanFactoryProcessor 时执行，此时 ConfigurationClassPostProcessor 已完成类扫描
 * @author zongf
 * @since 2021-12-01
 */
@Slf4j
@Component
public class YamlSourceFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // Do Nothing
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        List<AnnotationAttributes> attributesList = new ArrayList<>();

        // 先从容器中筛选出使用 @YmlSource 的注解
        for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.isSingleton() && StringUtils.hasText(beanDefinition.getBeanClassName())) {
                Class clz = forName(beanDefinition.getBeanClassName());
                if (clz.isAnnotationPresent(YamlSource.class)) {
                    YamlSource source = (YamlSource) clz.getAnnotation(YamlSource.class);
                    AnnotationAttributes annotationAttributes = new AnnotationAttributes();
                    annotationAttributes.put("value", source.value());
                    annotationAttributes.put("ignoreResourceNotFound", source.ignoreResourceNotFound());
                    annotationAttributes.put("name", source.name());
                    annotationAttributes.put("encoding", source.encoding());
                    annotationAttributes.put("factory", YamlSourceFactory.class);
                    annotationAttributes.put("beanClass", beanDefinition.getBeanClassName());
                    attributesList.add(annotationAttributes);
                }
            }
        }

        // 如果 @YamlSource 不为空，则开始处理
        if (!attributesList.isEmpty()) {
            Object configurationClassParser = null;
            Method processPropertySourceMethod = null;
            try {

                Class<?> configurationClassParserClss = forName("org.springframework.context.annotation.ConfigurationClassParser");
                Constructor<?> constructor = configurationClassParserClss.getConstructor(MetadataReaderFactory.class, ProblemReporter.class, Environment.class, ResourceLoader.class, BeanNameGenerator.class, BeanDefinitionRegistry.class);
                if (constructor == null) {
                    throw new RuntimeException("[YmlSource 解析失败] 未找到构造方法 org.springframework.context.annotation.ConfigurationClassParser(MetadataReaderFactory.class, ProblemReporter.class, Environment.class, ResourceLoader.class, BeanNameGenerator.class, BeanDefinitionRegistry.class)");
                }

                // 打开访问权限
                constructor.setAccessible(true);

                // 参考 org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions
                CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
                ProblemReporter problemReporter = new FailFastProblemReporter();
                DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
                AnnotationBeanNameGenerator instance = AnnotationBeanNameGenerator.INSTANCE;

                // 创建实例
                configurationClassParser = constructor.newInstance(cachingMetadataReaderFactory, problemReporter, this.environment, defaultResourceLoader, instance, registry);

                processPropertySourceMethod = configurationClassParserClss.getDeclaredMethod("processPropertySource", AnnotationAttributes.class);
                processPropertySourceMethod.setAccessible(true);
            } catch (Exception ex) {
                throw new RuntimeException("[YmlSource 解析失败] 初始化 ConfigurationClassParser ", ex);
            }

            // 解析文件
            if (configurationClassParser != null && processPropertySourceMethod != null) {
                for (AnnotationAttributes annotationAttributes : attributesList) {
                    try {
                        processPropertySourceMethod.invoke(configurationClassParser, annotationAttributes);
                    } catch (Exception e) {
                        throw new RuntimeException("[YmlSource 解析失败] @YamlSource 配置错误，配置类: " + annotationAttributes.getString("beanClass"), e);
                    }
                }
            }
        }
    }

    private Class forName(String className) {
        if (!StringUtils.hasText(className)) {
            return null;
        }
        try {
            return ClassUtils.forName(className, ClassUtils.getDefaultClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("@YamlSource 解析失败", e);
        }
    }

}
