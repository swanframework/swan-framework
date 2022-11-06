package com.swan.log.converter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.swan.log.processor.args.IArgProcessor;
import com.swan.log.processor.message.IMessageProcessor;
import org.slf4j.helpers.MessageFormatter;

import java.util.Collections;
import java.util.List;

/** logMsg 转换
 * @author zongf
 * @since 2022-11-06
 **/
public class LogMessageConverter extends MessageConverter {

    private static List<IArgProcessor> argProcessors = Collections.emptyList();

    private static List<IMessageProcessor> messageProcessors = Collections.emptyList();

    public static void setArgProcessors(List<IArgProcessor> processors) {
        if (processors != null) {
            argProcessors = processors;
        }
    }

    public static void setMessageProcessors(List<IMessageProcessor> processors) {
        if (processors != null) {
            messageProcessors = processors;
        }
    }

    @Override
    public String convert(ILoggingEvent event) {
        // 处理参数
        if (!argProcessors.isEmpty()) {
            // 获取log.xxx() 方法的参数列表
            Object[] argArray = event.getArgumentArray();

            // 如果参数列表不为空，则对参数进行处理
            if (argArray != null && argArray.length != 0) {
                int lastIdx = argArray.length;

                // 如果最后一个参数是异常类型，则不处理最后一个参数, 如: log.error("出现异常, id:{}", 1234, ex)
                if (argArray[argArray.length - 1] instanceof Throwable) {
                    // 如果最后一个参数是 Throwable
                    lastIdx = argArray.length -1;
                }

                // 处理参数，并更新
                for (int i = 0; i < lastIdx; i++) {
                    Object arg = argArray[i];
                    for (IArgProcessor processor : argProcessors) {
                        arg = processor.process(arg);
                    }
                    argArray[i] = arg;
                }
            }
        }


        // 重新格式化日志
        String formattedMessage = MessageFormatter.arrayFormat(event.getMessage(), event.getArgumentArray()).getMessage();

        // 处理全部日志
        for (IMessageProcessor messageProcessor : messageProcessors) {
            formattedMessage = messageProcessor.process(formattedMessage);
        }

        return formattedMessage;
    }

}
