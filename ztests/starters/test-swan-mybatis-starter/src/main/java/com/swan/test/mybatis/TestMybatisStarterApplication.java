package com.swan.test.mybatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(excludeName = "hello")
public class TestMybatisStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMybatisStarterApplication.class, args);
    }

}
