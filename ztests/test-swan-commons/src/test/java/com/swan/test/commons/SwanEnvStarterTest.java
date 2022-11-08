package com.swan.test.commons;

import com.swan.env.core.ISwanEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class SwanEnvStarterTest {

    @Autowired
    private ISwanEnvironment swanEnvironment;

    @Test
    public void env() {
        System.out.println(swanEnvironment.applicationName());
        System.out.println(swanEnvironment.serverPort());
        System.out.println(swanEnvironment.activeProfiles());
        System.out.println(swanEnvironment.cpuCores());
    }

}
