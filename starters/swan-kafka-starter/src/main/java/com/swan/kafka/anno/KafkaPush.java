package com.swan.kafka.anno;


import java.lang.annotation.*;

/**
 * 修饰 method
 *
 * @author zongf
 * @since 2020-09-07
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KafkaPush {

    /**
     * topic 名称
     */
    String topic() default "";

    /**
     * topic 结尾，支持使用 spel 表达式获取参数值
     */
    String topicSuffix() default "";

    /**
     * 链接 topic 和 topicSuffix 的符号
     */
    String separator() default "-";

    String value() default "";

    /**
     *
     */
    boolean wrapped() default true;

}
