package com.swan.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;

@EnableConfigurationProperties({Knife4jProperties.class})
@ConditionalOnProperty(name = "knife4j.enable", matchIfMissing = false)
public class Knife4jAutoConfig implements BeanFactoryAware{

    @Autowired
    private Knife4jProperties knife4jProperties;

    private ConfigurableBeanFactory beanFactory;



    @Bean
    public List<GroupedOpenApi> groupedOpenApis(){

        parseParameters();

        List<GroupedOpenApi> list = new ArrayList<>();
        for (Knife4jProperties.Group groupConfig : knife4jProperties.getGroups()) {
            List<Parameter> parameters = getParameters(groupConfig.getCommonParameters());

            GroupedOpenApi.Builder builder = GroupedOpenApi.builder();
            builder.group(groupConfig.getName());
            builder.displayName(groupConfig.getDisplayName());
            if (groupConfig.getPackagesToScan() != null) {
                builder.packagesToScan(groupConfig.getPackagesToScan());
            }
            if (groupConfig.getPackagesToExclude() != null) {
                builder.packagesToExclude(groupConfig.getPackagesToExclude());
            }
            if (groupConfig.getPathsToMatch() != null) {
                builder.pathsToMatch(groupConfig.getPathsToMatch());
            }
            if (groupConfig.getPathsToExclude() != null) {
                builder.pathsToExclude(groupConfig.getPathsToExclude());
            }
            builder.addOperationCustomizer((operation, handlerMethod) ->{
                for (Parameter parameter : parameters) {
                    operation.addParametersItem(parameter);
                }

                return operation;
            });

            GroupedOpenApi groupedOpenApi = builder.build();
            list.add(groupedOpenApi);
        }

        for (GroupedOpenApi groupedOpenApi : list) {
            this.beanFactory.registerSingleton(groupedOpenApi.getGroup(), groupedOpenApi);
        }

        return list;
    }

    private void parseParameters() {
        for (Knife4jProperties.Parameter parameterConfig : knife4jProperties.getParameters()) {
            Parameter parameter = new Parameter()
                    .in(parameterConfig.getLocation().getLocation())
                    .required(parameterConfig.isRequired())
                    .name(parameterConfig.getName())
                    .description(parameterConfig.getDescription())
                    .allowEmptyValue(parameterConfig.isAllowEmpty())
                    ;
            parameters.add(parameter);
        }
    }

    private List<Parameter> parameters = new ArrayList<>();

    private List<Parameter> getParameters(List<String> commonParameters) {
        if (commonParameters == null) {
            return Collections.emptyList();
        }
        return parameters.stream()
                .filter(parameter -> commonParameters.contains(parameter.getName()))
                .collect(Collectors.toList());

    }

    @Bean
    public GroupedOpenApi all() {
        return GroupedOpenApi.builder()
                .group("0.all")
                .displayName("全部")
                .pathsToMatch("/**")
//                .addOperationCustomizer(operationCustomizer())
                .build();
    }

    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> operation.addParametersItem(
                new Parameter()
                        .in("header")
                        .required(true)
                        .description("token 验2证")
                        .name("token2"));
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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }
}
