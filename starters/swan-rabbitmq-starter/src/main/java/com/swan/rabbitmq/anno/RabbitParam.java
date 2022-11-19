package com.swan.rabbitmq.anno;

import java.lang.annotation.*;

/** Rabbit 消息推送参数，需配合@RabbitPush 使用
 * @author zongf
 * @since 2020-09-06
 */
@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RabbitParam {

    /** 消息体(json格式)中的key */
    String value();

}