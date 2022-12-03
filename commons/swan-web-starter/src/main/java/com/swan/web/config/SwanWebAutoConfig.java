package com.swan.web.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.swan.web.core.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import java.math.BigDecimal;
import java.math.BigInteger;

/** Sboot 核心框架自动化配置
 * @author zongf
 * @since 2020-12-11
 */
@Configuration(proxyBeanMethods = false)
@Import({GlobalExceptionHandler.class})
@EnableConfigurationProperties(SwanWebProperties.class)
public class SwanWebAutoConfig {

    @Autowired
    private WebProperties webProperties;

    @Autowired
    private WebMvcProperties webMvcProperties;

    @Autowired
    private SwanWebProperties swanWebProperties;

    @Autowired
    private DispatcherServlet dispatcherServlet;

    // 全局异常捕获处理
    public GlobalExceptionHandler defaultGlobalExceptionHandler() {
        // 处理404异常
        if (swanWebProperties.getException().isConvert404()) {
            webProperties.getResources().setAddMappings(false);
            webMvcProperties.setThrowExceptionIfNoHandlerFound(true);
            dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        }
        return new GlobalExceptionHandler();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // 自动将 Long、BigInteger、BigDecimal 类型转换为 String 类型，解决 js 精度丢失问题
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, ToStringSerializer.instance);
        };
    }

}
