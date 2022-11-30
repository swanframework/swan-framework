package com.swan.test.mail.listener;

import com.swan.core.listener.IEventListener;
import com.swan.core.utils.JacksonUtil;
import com.swan.mail.listener.MailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** 邮件监听器
 * @author zongf
 * @since 2022-11-30
 **/
@Slf4j
@Service
public class MailEventListener implements IEventListener<MailEvent> {

    @Override
    public Class<MailEvent> eventType() {
        return MailEvent.class;
    }

    @Override
    public void invoke(MailEvent event) {
        log.info("==========================");
        log.info("监听到邮件发送, 邮件标题:{}, 发送状态:{}", event.getTitle(), event.getSendStatus());
        log.info("事件详情:{}", JacksonUtil.toString(event));
        log.info("**************************");
    }

}
