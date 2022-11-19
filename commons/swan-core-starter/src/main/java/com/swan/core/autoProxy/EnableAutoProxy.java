package com.swan.core.autoProxy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/** 启动自动代理
 * @author zongf
 * @since 2020-11-19
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(ProxyComponentRegister.class)
public @interface EnableAutoProxy {

    /** 扫描包 */
    String[] basePackages() default {};

}