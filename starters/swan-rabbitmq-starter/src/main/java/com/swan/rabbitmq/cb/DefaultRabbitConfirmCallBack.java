package com.swan.rabbitmq.cb;

import com.swan.core.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/** Rabbit 默认消息回调
 *  1) 需开启: spring.rabbitmq.publisher-returns=true
 *  2) 应用中可自定义RabbitTemplate.ConfirmCallback, 注入spring容器实现覆盖此默认实现
 *  3) 全局配置一次即可
 */
@Slf4j
public class DefaultRabbitConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.debug("消息发送成功!，correlationData:{} ,ack:{}, cause:{}", JacksonUtil.toString(correlationData), ack, cause);
        } else {
            log.debug("消息发送失败!，correlationData:{} ,ack:{}, cause:{}", JacksonUtil.toString(correlationData), ack, cause);
        }
    }

}