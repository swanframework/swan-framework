package com.swan.rabbitmq.config;

import com.swan.rabbitmq.cb.DefaultRabbitConfirmCallBack;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zongf
 * @since 2020-11-19
 */
@Configuration
@ConditionalOnClass(RabbitTemplate.class)
public class SwanRabbitMqAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 开启spring.rabbitmq.publisher-returns 之后, ConfirmCallback 才会有作用
    @Bean
    @ConditionalOnProperty("spring.rabbitmq.publisher-returns")
    @ConditionalOnMissingBean
    RabbitTemplate.ConfirmCallback confirmCallback(RabbitTemplate rabbitTemplate){
        DefaultRabbitConfirmCallBack defaultRabbitConfirmCallBack = new DefaultRabbitConfirmCallBack();
        rabbitTemplate.setConfirmCallback(defaultRabbitConfirmCallBack);
        return defaultRabbitConfirmCallBack;
    }

}
