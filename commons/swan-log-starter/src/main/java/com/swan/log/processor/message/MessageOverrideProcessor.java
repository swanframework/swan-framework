package com.swan.log.processor.message;

import com.swan.log.config.SwanLogProperty;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

/** 日志信息超长处理器
 * @author zongf
 * @since 2022-11-07
 **/
public class MessageOverrideProcessor implements IMessageProcessor, InitializingBean, Ordered {

    @Autowired
    private SwanLogProperty swanLogProperty;

    private Integer maxLength;

    private String overrideSuffix;

    @Override
    public String process(String formattedMessage) {
        if (maxLength != null && formattedMessage.length() > maxLength) {
            formattedMessage = formattedMessage.substring(0, maxLength) + overrideSuffix;
        }

        return formattedMessage;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.maxLength = swanLogProperty.getMaxLength();
        this.overrideSuffix = swanLogProperty.getOverrideSuffix();
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
