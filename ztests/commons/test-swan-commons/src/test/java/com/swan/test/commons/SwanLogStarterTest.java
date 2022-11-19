package com.swan.test.commons;

import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ClassUtils;

import java.net.URL;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class SwanLogStarterTest {

    @Test
    void contextLoads() {

        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String xml = "logback-spring.xml";
        URL resource = cl.getResource(xml);
        System.out.println(cl.getResource("logback-spring.xml").toString());
    }

    @Test
    public void log() {
        ILoggerFactory loggerFactory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        LoggerContext context = (LoggerContext) loggerFactory;
        int a = 1;
        double b = 2;
        float c = 3;
        log.info("info hello,{},{},{}", "0123456789", "abcdefdewewewe",  "0123456789");
        log.warn("warn hello,{}", "logback");
        log.error("error hello,{}", "logback");
    }

}