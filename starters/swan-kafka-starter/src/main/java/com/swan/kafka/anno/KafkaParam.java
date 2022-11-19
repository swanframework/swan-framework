package com.swan.kafka.anno;

import java.lang.annotation.*;

/**
 * 推送 map 类型参数时，指定map 中的key
 *
 * @author zongf
 * @since 2020-09-06
 */
@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface KafkaParam {

    /**
     * 消息体(json格式)中的key
     */
    String value() default "";

}
