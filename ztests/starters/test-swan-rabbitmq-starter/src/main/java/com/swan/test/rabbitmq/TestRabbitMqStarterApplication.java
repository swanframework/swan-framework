package com.swan.test.rabbitmq;


import com.swan.rabbitmq.anno.EnableRabbitPusher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestRabbitMqStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestRabbitMqStarterApplication.class, args);
    }

}
