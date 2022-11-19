package com.swan.test.core.autoProxy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("threadPool")
public class AutoProxyTest {

    @Autowired
    private ThreadPoolTaskExecutor jobPool;

    @Autowired
    private ThreadPoolTaskExecutor taskPool;

    @Test
    public void env() {
        Assertions.assertEquals(5, jobPool.getCorePoolSize());
    }

    @Test
    public void taskPool() {
        Assertions.assertEquals(10, taskPool.getCorePoolSize());
    }

}
