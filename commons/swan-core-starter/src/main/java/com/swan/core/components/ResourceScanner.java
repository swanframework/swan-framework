package com.swan.core.components;

import com.swan.core.enums.ExceptionCodeEnum;
import com.swan.core.exception.SwanBaseException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** 资源扫描器
 * @author zongf
 * @since 2019-12-05
 */
public class ResourceScanner implements ResourceLoaderAware, ApplicationContextAware {

    public static final String RESOURCE_PATTERN_CLASS = "**/*.class";

    private MetadataReaderFactory metadataReaderFactory;

    private ResourcePatternResolver resourcePatternResolver;

    private ApplicationContext applicationContext;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /** 扫描包下使用指定注解修饰的类文件
     *  1) 递归扫描包及其子包
     *  2) 扫描结果为空时, 返回空的LinkedHashSet, 而非null
     *  3) 类文件包含接口和类
     * @param basePackage 扫描包路径, 格式: xxx.xxx.xxx
     * @param annotation 筛选的注解
     * @return LinkedHashSet
     * @author zongf
     * @since 2021-01-08
     */
    public Set<Class> scannerClassWithAnnotation(String basePackage, Class<? extends Annotation> annotation) {
        return this.scannerClassWithAnnotations(Arrays.asList(basePackage), Arrays.asList(annotation));
    }

    /** 扫描多个包下使用指定注解修饰的类文件
     *  1) 递归扫描包及其子包
     *  2) 扫描结果为空时, 返回空的LinkedHashSet, 而非null
     *  3) 类文件包含接口和类
     * @param basePackages 扫描包路径, 格式: xxx.xxx.xxx
     * @param annotation 筛选的注解
     * @return LinkedHashSet
     * @author zongf
     * @since 2021-01-08
     */
    public Set<Class> scannerClassWithAnnotation(List<String> basePackages, Class<? extends Annotation> annotation) {
        return this.scannerClassWithAnnotations(basePackages, Arrays.asList(annotation));
    }

    /** 扫描多个包下使用指定注解修饰的类文件
     *  1) 递归扫描包及其子包
     *  2) 扫描结果为空时, 返回空的LinkedHashSet, 而非null
     *  3) 类文件包含接口和类
     * @param basePackages 扫描包路径, 格式: xxx.xxx.xxx
     * @param annotations 多个注解
     * @return LinkedHashSet
     * @author zongf
     * @since 2021-01-08
     */
    public Set<Class> scannerClassWithAnnotations(List<String> basePackages, List<Class<? extends Annotation>> annotations) {

        Set<Class> classSet = this.scannerClass(basePackages);

        // 筛选是否使用了注解修饰
        return classSet.stream().filter(clz -> {
            for (Class<? extends Annotation> annotation : annotations) {
                if (clz.isAnnotationPresent(annotation)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toSet());
    }


    /** 扫描多个包下所有的类文件
     *  1) 递归扫描包及其子包
     *  2) 扫描结果为空时, 返回空的LinkedHashSet, 而非null
     *  3) 类文件包含接口和类
     * @param basePackages 扫描包路径, 格式: xxx.xxx.xxx
     * @return LinkedHashSet
     * @author zongf
     * @since 2021-01-08
     */
    public Set<Class> scannerClass(List<String> basePackages) {
        Set<Class> classSet = new LinkedHashSet<>();

        for (String basePackage : basePackages) {
            Set<Class> set = scannerClass(basePackage);
            if (set != null && set.size() > 0) {
                classSet.addAll(set);
            }
        }

        return classSet;
    }

    /** 扫描包下所有的类文件
     *  1) 递归扫描包及其子包
     *  2) 扫描结果为空时, 返回空的LinkedHashSet, 而非null
     *  3) 类文件包含接口和类
     * @param basePackage 扫描包路径, 格式: xxx.xxx.xxx
     * @return LinkedHashSet
     * @author zongf
     * @since 2021-01-08
     */
    public Set<Class> scannerClass(String basePackage) {
        Set<Class> classSet = new LinkedHashSet<>();

        // 扫描包下的资源文件
        Resource[] resources = scannerResource(basePackage, RESOURCE_PATTERN_CLASS);

        try {
            for (Resource resource : resources) {
                // 忽略不可读资源
                if(!resource.isReadable())  continue;

                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                Class clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                classSet.add(clazz);
            }
        } catch (IOException ex) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "资源扫描异常", ex);
        } catch (ClassNotFoundException ex) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "加载class文件异常", ex);
        }
        return classSet;
    }

    /** 递归扫描指定包及其子包下的资源
     * @param basePackage 包及其子包
     * @param resourcePattern 资源名称匹配模式, 如扫描类: **\/*.class
     * @return 如果扫描到的资源为空, 则返回空的数组, 而非null
     * @author zongf
     * @since 2021-01-08
     */
    public Resource[] scannerResource(String basePackage, String resourcePattern) {
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolvePackagePath(basePackage) + '/' + resourcePattern;
            return this.resourcePatternResolver.getResources(packageSearchPath);
        } catch (IOException ex) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "资源扫描异常", ex);
        }
    }

    /**
     * 将包名称转换为包路径
     * @param basePackage xxx.xxx
     * @return String xxx/xxx
     * @author zongf
     * @since 2021-01-08
     */
    protected String resolvePackagePath(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(this.applicationContext.getEnvironment().resolveRequiredPlaceholders(basePackage));
    }

}
