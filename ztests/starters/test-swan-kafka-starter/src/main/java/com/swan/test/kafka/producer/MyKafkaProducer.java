package com.swan.test.kafka.producer;

import com.swan.core.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author zongf
 * @date 2021-10-25
 */
@Component
public class MyKafkaProducer {

    @Autowired
    private  KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(result -> {
            System.out.println("消息发送成功：" + JacksonUtil.toString(result));
        }, ex -> {
            System.out.println("消息发送失败：" + JacksonUtil.toString(ex));
        });

    }



}
