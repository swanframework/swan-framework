package com.swan.kafka.anno;

import com.swan.kafka.factory.KafkaPusherRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用声明式消息推送
 *
 * @author zongf
 * @since 2020-11-19
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(KafkaPusherRegistrar.class)
public @interface EnableKafkaPusher {

    /**
     * @KafkaPusher 扫描包
     */
    String[] basePackages() default {};

}
