package com.swan.test.core.yaml;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("dev")
public class DevYamlTest {

    @Autowired
    private JdbcYaml jdbcYaml;

    static {
        // 单元测试，动态设置环境变量
        System.setProperty("env", "dev");
    }

    @Test
    public void dev() {
        Assertions.assertEquals("dev-root", jdbcYaml.getUsername());
        Assertions.assertEquals("dev-123456", jdbcYaml.getPassword());
    }

}
