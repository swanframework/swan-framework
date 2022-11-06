package com.swan.log.processor.message;

import com.swan.log.processor.message.matcher.ISensitiveMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;

import java.util.List;

/** 敏感日志处理器
 * @author zongf
 * @since 2022-11-07
 **/
public class MessageSensitiveProcessor implements IMessageProcessor, Ordered {

    @Autowired
    private List<ISensitiveMatcher> matchers;

    @Override
    public String process(String formattedMessage) {
        // 如果配置了正则表达式，则进行替换
        if (matchers != null && matchers.size() > 0) {
            for (ISensitiveMatcher matcher : matchers) {
                formattedMessage = formattedMessage.replaceAll(matcher.regex(), matcher.replacement());
            }
        }

        return formattedMessage;
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
