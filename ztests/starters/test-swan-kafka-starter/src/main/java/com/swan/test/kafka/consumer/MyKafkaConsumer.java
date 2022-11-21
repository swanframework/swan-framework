package com.swan.test.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zongf
 * @date 2021-10-25
 */
@Component
public class MyKafkaConsumer {

    @KafkaListener(topics = "topic2",groupId = "test-sboot-starter-kafka")
    public void listen(String message) {
        System.out.println("接收到消息:" + message);
    }

    @KafkaListener(topics = "topic2",groupId = "test-sboot-starter-kafka-2")
    public void listen2(ProducerRecord<String, String> message) {
        System.out.println("接收到消息:" + JSONObject.toJSONString(message));
    }

}
