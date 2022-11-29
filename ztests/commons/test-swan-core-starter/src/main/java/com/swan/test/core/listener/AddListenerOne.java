package com.swan.test.core.listener;

import com.swan.core.listener.IEventListener;
import com.swan.core.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zongf
 * @since 2022-11-29
 **/
@Slf4j
@Component
public class AddListenerOne implements IEventListener<AddEvent> {
    @Override
    public Class<AddEvent> eventType() {
        return AddEvent.class;
    }

    @Override
    public void invoke(AddEvent event) {
        log.info("{} 接收到事件:{}", this.getClass(), JacksonUtil.toString(event));
    }

}
