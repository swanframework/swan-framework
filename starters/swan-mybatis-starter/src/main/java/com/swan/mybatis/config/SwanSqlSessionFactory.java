package com.swan.mybatis.config;

import com.github.pagehelper.PageInterceptor;
import com.swan.core.exception.SwanBaseException;
import com.swan.mybatis.core.AutoMapperStatementRegister;
import com.swan.mybatis.core.EntityFieldInterceptor;
import com.swan.mybatis.core.EntityFieldParser;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.beans.FeatureDescriptor;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zongf
 * @since 2022-11-25
 **/
public class SwanSqlSessionFactory extends SqlSessionFactoryBean {
    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private final MybatisProperties properties;
    private final Interceptor[] interceptors;
    private final TypeHandler[] typeHandlers;
    private final LanguageDriver[] languageDrivers;
    private final ResourceLoader resourceLoader;
    private final DatabaseIdProvider databaseIdProvider;
    private final List<ConfigurationCustomizer> configurationCustomizers;
    private final DataSource dataSource;
    private Configuration customConfiguration;

    public SwanSqlSessionFactory(DefaultListableBeanFactory beanFactory,
                                 MybatisProperties properties,
                                 ObjectProvider<Interceptor[]> interceptorsProvider,
                                 ObjectProvider<TypeHandler[]> typeHandlersProvider,
                                 ObjectProvider<LanguageDriver[]> languageDriversProvider,
                                 ResourceLoader resourceLoader,
                                 ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                 ObjectProvider<List<ConfigurationCustomizer>>
                                 configurationCustomizersProvider,
                                 DataSource dataSource) {

        this.properties = properties;
        this.interceptors = (Interceptor[])interceptorsProvider.getIfAvailable();
        this.typeHandlers = (TypeHandler[])typeHandlersProvider.getIfAvailable();
        this.languageDrivers = (LanguageDriver[])languageDriversProvider.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = (DatabaseIdProvider)databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = (List)configurationCustomizersProvider.getIfAvailable();
        this.dataSource = dataSource;

        // 初始化 sqlSessionFactory, 与 MybatisAutoConfiguration.sqlSessionFactory() 方法逻辑保持一致
        this.initSqlSessionFactory();

        // [自定义扩展] 自动生成 mapper
        Set<Class> entityClasses = new AutoMapperStatementRegister(this.customConfiguration, beanFactory, resourceLoader).process();

        // [自定义扩展] 解析 @AutoField 等属性
        new EntityFieldParser().parseFields(entityClasses);
    }

    // 与 MybatisAutoConfiguration.sqlSessionFactory() 方法逻辑保持一致
    private void initSqlSessionFactory() {

        // 设置数据源
        this.setDataSource(dataSource);
        // 设置虚拟文件系统
        this.setVfs(SpringBootVFS.class);

        // 初始化 Configuration 配置类
        this.customConfiguration = applyConfiguration();
        this.setConfiguration(customConfiguration);

        // 设置 Configuration 属性
        if (this.properties.getConfigurationProperties() != null) {
            this.setConfigurationProperties(this.properties.getConfigurationProperties());
        }

        // [自定义扩展1]: 扩展自定义插件
        this.initPlugins();

        if (this.databaseIdProvider != null) {
            this.setDatabaseIdProvider(this.databaseIdProvider);
        }

        // [自定义扩展2]: 自定义包路径，支持模糊匹配
        this.setTypeAliasesPackage(getTypeAliasesPackages(properties.getTypeAliasesPackage()));

        // 设置 typeHandlers
        if (this.properties.getTypeAliasesSuperType() != null) {
            this.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
            this.setTypeHandlersPackage(properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.typeHandlers)) {
            this.setTypeHandlers(this.typeHandlers);
        }

        // [自定义扩展3]: 处理mapper.xml, 支持二级目录
        this.setMapperLocations(resolveMapperLocations(properties.getMapperLocations()));

        Set<String> factoryPropertyNames = (Set) Stream.of((new BeanWrapperImpl(SqlSessionFactoryBean.class)).getPropertyDescriptors()).map(FeatureDescriptor::getName).collect(Collectors.toSet());
        Class<? extends LanguageDriver> defaultLanguageDriver = this.properties.getDefaultScriptingLanguageDriver();
        if (factoryPropertyNames.contains("scriptingLanguageDrivers") && !ObjectUtils.isEmpty(this.languageDrivers)) {
            this.setScriptingLanguageDrivers(this.languageDrivers);
            if (defaultLanguageDriver == null && this.languageDrivers.length == 1) {
                defaultLanguageDriver = this.languageDrivers[0].getClass();
            }
        }

        if (factoryPropertyNames.contains("defaultScriptingLanguageDriver")) {
            this.setDefaultScriptingLanguageDriver(defaultLanguageDriver);
        }
    }


    private void initPlugins() {

        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new EntityFieldInterceptor());
        interceptors.add(new PageInterceptor());

        // 自定义插件
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            interceptors.addAll(Arrays.asList(this.interceptors));
        }
        // 转换为数组
        Interceptor[] plugins = new Interceptor[interceptors.size()];
        for (int i = 0; i < interceptors.size(); i++) {
            plugins[i] = interceptors.get(i);
        }
        this.setPlugins(plugins);
    }


    private Configuration applyConfiguration() {
        Configuration configuration = this.properties.getConfiguration();
        if (configuration == null) {
            configuration = new Configuration();
        }

        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
            Iterator customizerIterator = this.configurationCustomizers.iterator();

            while(customizerIterator.hasNext()) {
                ConfigurationCustomizer customizer = (ConfigurationCustomizer)customizerIterator.next();
                customizer.customize(configuration);
            }
        }
        return configuration;
    }

    public static String getTypeAliasesPackages(String typeAliasesPackage) {
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        List<String> allResult = new ArrayList<String>();
        try {
            for (String aliasesPackage : typeAliasesPackage.split(",")) {
                List<String> result = new ArrayList<String>();
                aliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
                Resource[] resources = resolver.getResources(aliasesPackage);
                if (resources != null && resources.length > 0) {
                    MetadataReader metadataReader = null;
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
                            try {
                                result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                            }
                            catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (result.size() > 0) {
                    HashSet<String> hashResult = new HashSet<String>(result);
                    allResult.addAll(hashResult);
                }
            }
            if (allResult.size() > 0) {
                typeAliasesPackage = String.join(",", (String[]) allResult.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return typeAliasesPackage;
    }

    public Resource[] resolveMapperLocations(String[] mapperLocations) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        if (mapperLocations != null) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    throw new SwanBaseException("mapperLocations 初始化异常", e);
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return super.getObject();
    }

    @Override
    public Class<? extends SqlSessionFactory> getObjectType() {
        return super.getObjectType();
    }
}
