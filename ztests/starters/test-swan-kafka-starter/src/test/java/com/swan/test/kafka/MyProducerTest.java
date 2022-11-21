package com.swan.test.kafka;

import com.swan.test.kafka.producer.MyKafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class MyProducerTest {

    @Autowired
    private MyKafkaProducer myKafkaProducer;

    @Test
    void sendMessage() {
        for (int i = 0; i < 10; i++) {
            myKafkaProducer.sendMessage("topic2","message-" + i);
        }
    }

}
