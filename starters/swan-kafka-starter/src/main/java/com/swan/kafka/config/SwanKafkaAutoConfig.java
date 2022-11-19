package com.swan.kafka.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author zongf
 * @since 2020-11-19
 */
@Configuration
@ConditionalOnClass(KafkaTemplate.class)
public class SwanKafkaAutoConfig {


}
