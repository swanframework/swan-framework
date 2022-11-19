package com.swan.test.log;

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
@ActiveProfiles("dev")
class SwanLogStarterTest {

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
