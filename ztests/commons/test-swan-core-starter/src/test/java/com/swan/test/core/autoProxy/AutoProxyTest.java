package com.swan.test.core.autoProxy;

import com.swan.core.autoProxy.EnableAutoProxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@EnableAutoProxy
@ActiveProfiles("dev")
public class AutoProxyTest {

    @Autowired
    private IAutoProxyService autoProxyService;

    @Test
    public void add() {
        Integer count = autoProxyService.add(1, 2);
        Assertions.assertEquals(3, count);
    }



}
