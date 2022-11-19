package com.swan.rabbitmq.anno;

import com.swan.rabbitmq.factory.RabbitPusherRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/** 启用声明式消息推送
 * @author zongf
 * @since 2020-11-19
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(RabbitPusherRegistrar.class)
public @interface EnableRabbitPusher {

    String value() default "";

    String[] basePackages() default {};

}