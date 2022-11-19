package com.swan.kafka.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 修饰接口，标识需要为该接口生成动态 bean
 *
 * @author zongf
 * @since 2020-09-07
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface KafkaPusher {

}
