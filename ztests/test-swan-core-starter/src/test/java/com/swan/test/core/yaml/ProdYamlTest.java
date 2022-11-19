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
public class ProdYamlTest {

    static {
        // 单元测试，动态设置环境变量
        System.setProperty("env", "prod");
    }

    @Autowired
    private JdbcYaml jdbcYaml;

    @Test
    public void dev() {
        Assertions.assertEquals("prod-root", jdbcYaml.getUsername());
        Assertions.assertEquals("prod-123456", jdbcYaml.getPassword());
    }

}
