package com.swan.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@EnableConfigurationProperties({Knife4jProperties.class})
@ConditionalOnProperty(name = "knife4j.enable", matchIfMissing = false)
public class Knife4jAutoConfig implements BeanFactoryAware{

    private ConfigurableBeanFactory beanFactory;

    @Autowired
    private Knife4jProperties knife4jProperties;

    // 参数配置
    private List<Parameter> parameterConfigs = new ArrayList<>();
    // 标识参数是否已解析
    private AtomicBoolean parameterHasParsed = new AtomicBoolean(false);

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    /** 定义全部api*/
    @Bean
    public GroupedOpenApi globalGroup() {
        return buildGroupedOpenApi(knife4jProperties.getGlobal());
    }

    @Bean
    public void groupedOpenApis(){

        if (knife4jProperties.getGroups() == null) {
            return;
        }

        // 解析分组配置
        List<GroupedOpenApi> list = knife4jProperties.getGroups().stream()
                .map(group -> buildGroupedOpenApi(group))
                .collect(Collectors.toList());

        // 注册 bean
        for (GroupedOpenApi groupedOpenApi : list) {
            this.beanFactory.registerSingleton(groupedOpenApi.getGroup(), groupedOpenApi);
        }

    }

    // 解析参数配置
    private synchronized void parseParameterConfigs() {
        if (knife4jProperties.getParameterConfigs() != null) {
            for (Knife4jProperties.ParameterConfig parameterConfig : knife4jProperties.getParameterConfigs()) {
                Parameter parameter = new Parameter()
                        .in(parameterConfig.getLocation().getLocation())
                        .required(parameterConfig.isRequired())
                        .name(parameterConfig.getName())
                        .description(parameterConfig.getDescription())
                        .allowEmptyValue(parameterConfig.isAllowEmpty())
                        ;
                this.parameterConfigs.add(parameter);
            }
        }

        // 标志已解析
        parameterHasParsed.set(true);
    }


    private List<Parameter> getParameters(List<String> commonParameters) {
        // 如果参数未解析，则先解析参数
        if (!parameterHasParsed.get()) {
            parseParameterConfigs();
        }
        if (commonParameters == null) {
            return Collections.emptyList();
        }
        return parameterConfigs.stream()
                .filter(parameter -> commonParameters.contains(parameter.getName()))
                .collect(Collectors.toList());

    }

    /** 构造分组配置 */
    public GroupedOpenApi buildGroupedOpenApi(Knife4jProperties.Group group) {

        GroupedOpenApi.Builder builder = GroupedOpenApi.builder();
        builder.group(group.getName());
        builder.displayName(group.getDisplayName());

        Knife4jProperties.Filter filter = group.getFilter();
        if (filter != null) {
            if (filter.getPackagesToScan() != null) {
                builder.packagesToScan(filter.getPackagesToScan());
            }
            if (filter.getPackagesToExclude() != null) {
                builder.packagesToExclude(filter.getPackagesToExclude());
            }
            if (filter.getPathsToMatch() != null) {
                builder.pathsToMatch(filter.getPathsToMatch());
            }
            if (filter.getPathsToExclude() != null) {
                builder.pathsToExclude(filter.getPathsToExclude());
            }
            if (filter.getHeadersToMatch() != null) {
                builder.headersToMatch(filter.getHeadersToMatch());
            }
        }

        // 设置参数
        List<Parameter> parameters = getParameters(group.getCommonParameters());
        builder.addOperationCustomizer((operation, handlerMethod) ->{
            for (Parameter parameter : parameters) {
                operation.addParametersItem(parameter);
            }

            return operation;
        });

        return builder.build();
    }


    // 配置主页信息
    @Bean
    public OpenAPI homeInfo() {

        Contact contact = new Contact();
        contact.setName(knife4jProperties.getAuthor());

        Info info = new Info()
                .title(knife4jProperties.getTitle())
                .description(knife4jProperties.getDescription())
                .contact(contact)
                .version(knife4jProperties.getVersion())
                .termsOfService(knife4jProperties.getServiceUrl());

        return new OpenAPI().info(info);
    }


}