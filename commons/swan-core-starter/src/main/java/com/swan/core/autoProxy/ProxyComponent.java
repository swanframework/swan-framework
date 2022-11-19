package com.swan.core.autoProxy;

import java.lang.annotation.*;

/** 启用自动代理
 * @author zongf
 * @since 2020-11-26
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyComponent {

    Class<? extends ProxyComponentDefaultHandler> invokerClass() default ProxyComponentDefaultHandler.class;

}