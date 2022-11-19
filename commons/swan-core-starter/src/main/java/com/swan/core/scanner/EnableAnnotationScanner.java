package com.swan.core.scanner;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.LinkedHashSet;
import java.util.Set;

/** @EnableXXX 组件扫描器
 * @author zongf
 * @since 2020-11-26
 */
public class EnableAnnotationScanner extends ClassPathScanningCandidateComponentProvider {

    private ResourceLoader resourceLoader;

    public EnableAnnotationScanner(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    public EnableAnnotationScanner(ResourceLoader resourceLoader, Class... annotationTypes) {
        this(resourceLoader);
        this.addIncludeAnnotationType(annotationTypes);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        boolean isCandidate = false;
        if (beanDefinition.getMetadata().isIndependent()) {
            if (!beanDefinition.getMetadata().isAnnotation()) {
                isCandidate = true;
            }
        }
        return isCandidate;
    }

    /** 添加需要扫描的注解类型
     * @param annotationTypes 需要扫描的注解类型
     * @auth zongf
     * @since 2020-11-26
     */
    public void addIncludeAnnotationType(Class... annotationTypes) {

        for (Class annotationType : annotationTypes) {
            if (!annotationType.isAnnotation()) {
                throw new UnsupportedOperationException(annotationType + " is not annotation!");
            }
            this.addIncludeFilter(new AnnotationTypeFilter(annotationType));
        }
    }

    /** 扫描包中符合条件的类
     * @param basePackages 需要扫描的包
     * @auth zongf
     * @since 2020-11-26
     */
    public Set<BeanDefinition> doScanPackages(Set<String> basePackages){
        Set<BeanDefinition> candidateComponents = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> components = this.findCandidateComponents(basePackage);
            candidateComponents.addAll(components);
        }
        return candidateComponents;
    }

}
