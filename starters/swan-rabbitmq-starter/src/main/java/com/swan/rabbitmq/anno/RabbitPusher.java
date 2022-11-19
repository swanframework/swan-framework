package com.swan.rabbitmq.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/** Rabbit 消息推送接口。修饰接口，接口无需提供实现类
 * @author zongf
 * @since 2020-09-07
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RabbitPusher {

}