package com.swan.test.core.listener;

import com.swan.core.listener.EventMulticaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("dev")
public class ListenerTest {

    @Autowired
    private EventMulticaster eventMulticaster;


    @Test
    public void sync() {
        eventMulticaster.publish(new AddEvent(1001, "add a user"));
        eventMulticaster.publish(new DeleteEvent(1001, "delete a user"));
    }


    @Test
    public void async() {
        eventMulticaster.asyncPublish(new AddEvent(1001, "add a user"));
        eventMulticaster.asyncPublish(new DeleteEvent(1001, "delete a user"));
    }



}
