package com.swan.mybatis.core;

import com.swan.core.scanner.EnableAnnotationScanner;
import com.swan.core.utils.ClassUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

import java.util.*;

/** 扫描使用 @Mapper 修饰的类
 * @author zongf
 * @since 2022-11-26
 **/
public class MapperScanner {

    private DefaultListableBeanFactory beanFactory;

    private ResourceLoader resourceLoader;

    public MapperScanner(DefaultListableBeanFactory beanFactory, ResourceLoader resourceLoader) {
        this.beanFactory = beanFactory;
        this.resourceLoader = resourceLoader;

    }

    public List<Class> doScan() {
        // 1. 扫描 @ProxyComponent 组件
        EnableAnnotationScanner scanner = new EnableAnnotationScanner(this.resourceLoader);
        scanner.addIncludeAnnotationType(Mapper.class);

        // 2. 获取扫描包
        Set<String> scanPackages = this.getSpringBootApplicationPackage(beanFactory);
        Set<BeanDefinition> beanDefinitions = scanner.doScanPackages(scanPackages);


        // 3. 注册bean定义
        List<Class> mapperList = new ArrayList<>();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            Class beanClass = ClassUtil.forName(beanDefinition.getBeanClassName(), beanFactory.getBeanClassLoader());
            if (beanClass.isInterface()) {
                mapperList.add(beanClass);
            }
        }
        return mapperList;
    }

    protected Set<String> getSpringBootApplicationPackage(ConfigurableListableBeanFactory beanFactory){
        Set<String> packages = new HashSet<>();
        // 加载SpringBootApplication, 如果没有则表示非springboot 环境

        for (String beanName : beanFactory.getBeanNamesForAnnotation(SpringBootApplication.class)) {
            String beanClassName = beanFactory.getBeanDefinition(beanName).getBeanClassName();

            // 加载启动类
            Class<?> beanClass =  ClassUtil.forName(beanClassName, beanFactory.getBeanClassLoader());
            SpringBootApplication annotation = beanClass.getAnnotation(SpringBootApplication.class);

            // 如果指定了扫描包，则添加指定的扫描包，否则添加启动类所在包路径
            if (annotation.scanBasePackages() != null && annotation.scanBasePackages().length > 0) {
                packages.addAll(Arrays.asList(annotation.scanBasePackages()));
            }else {
                packages.add(ClassUtils.getPackageName(beanClassName));
            }
        }

        return packages;
    }

}
