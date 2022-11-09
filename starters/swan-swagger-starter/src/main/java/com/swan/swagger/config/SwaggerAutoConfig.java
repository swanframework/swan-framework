package com.swan.swagger.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.swan.swagger.plugins.ModelPropertySortByDeclarePlugin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@EnableConfigurationProperties({SwaggerProperties.class})
@ConditionalOnProperty(name = "sboot.swagger.enable", matchIfMissing = false)
public class SwaggerAutoConfig implements BeanFactoryAware {

    @Autowired
    private SwaggerProperties swaggerProperties;

    private ConfigurableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    @Bean
    public ModelPropertySortByDeclarePlugin modelBuilderPlugin() {
        return new ModelPropertySortByDeclarePlugin();
    }

    //配置 Swagger 主页信息
    private ApiInfo parseApiInfo() {
        SwaggerProperties.HomeInfo homeInfo = swaggerProperties.getInfo();
        if(homeInfo == null) homeInfo = new SwaggerProperties.HomeInfo();

        Contact contact = homeInfo == null ? null : new Contact(homeInfo.getAuthor(), null, null);
        return new ApiInfo(homeInfo.getTitle(),homeInfo.getDescription(),homeInfo.getVersion(),homeInfo.getServiceUrl(),contact,null,null,new ArrayList<>());
    }

    // 处理扫描包
    private List<Predicate<RequestHandler>> parseScanPackageList() {
        if (swaggerProperties.getScanPackages() == null || swaggerProperties.getScanPackages().length == 0) {
            return null;
        }

        List<Predicate<RequestHandler>> basePackageList = new ArrayList<>();
        for (String basePackage : swaggerProperties.getScanPackages()) {
            basePackageList.add(Predicates.or(RequestHandlerSelectors.basePackage(basePackage)));
        }
        return basePackageList;
    }

    // 处理额外参数
    private List<Parameter> parseParameters(SwaggerProperties.Group group){

        List<SwaggerProperties.Parameter> parameterList = new ArrayList<>();
        if (swaggerProperties.getParameters() != null) {
            parameterList.addAll(swaggerProperties.getParameters());
        }
        if (group.getParameters() != null) {
            parameterList.addAll(group.getParameters());
        }

        // 无须额外增加参数, 则直接返回
        if(parameterList.isEmpty()) return null;

        // 添加参数
        List<Parameter> parameters = new ArrayList<>();
        for (SwaggerProperties.Parameter parameter : parameterList) {
            Parameter pr = new ParameterBuilder()
                    .name(parameter.getName())
                    .parameterType(parameter.getType())
                    .modelRef(new ModelRef(parameter.getLocation()))
                    .description(parameter.getDescription())
                    .defaultValue(parameter.getDefaultValue())
                    .build();
            parameters.add(pr);
        }
        return parameters;
    }

    @Bean
    public List<Docket> registerDockets() {

        List<Docket> docketList = new ArrayList<>();
        // 未指定分组时, 添加默认分组
        if (swaggerProperties.getGroups() == null) {
            SwaggerProperties.Group group = new SwaggerProperties.Group();
            group.setName("default");
            group.setPathPattern("/.*");
            swaggerProperties.setGroups(new ArrayList<>());
            swaggerProperties.getGroups().add(group);
        }

        for (SwaggerProperties.Group group : swaggerProperties.getGroups()) {

            // 接口扫描包
            List<Predicate<RequestHandler>> basePackageList = parseScanPackageList();
            Predicate predicate = basePackageList == null ? Predicates.alwaysTrue() : Predicates.or(basePackageList) ;


            // 设置添加参数
            List<Parameter> parameters = parseParameters(group);

            // 生成配置, 每组一个
            Docket docket = new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(parseApiInfo())
                    .pathMapping(swaggerProperties.getPathPrefix())  // 统一路径前缀
                    .select()
                    .apis(predicate)  //全局扫描路径
                    .paths(PathSelectors.regex(group.getPathPattern()))
                    .build()
                    .groupName(group.getName())
                    .globalOperationParameters(parameters);

            docketList.add(docket);
        }

        // 注册
        for (int i = 0; i < docketList.size(); i++) {
            beanFactory.registerSingleton("docket_"+i, docketList.get(i));
        }

        return docketList;
    }


}
