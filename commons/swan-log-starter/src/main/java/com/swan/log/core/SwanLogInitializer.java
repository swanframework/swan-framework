package com.swan.log.core;

import com.swan.log.processor.args.IArgProcessor;
import com.swan.log.processor.message.IMessageProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.OrderComparator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** 日志初始化器: 当容器刷新后, 才开始启用自定义日志处理器
 * @author zongf
 * @since 2022-11-06
 **/
public class SwanLogInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        this.applicationContext = event.getApplicationContext();

        // 初始化
        initMessageProcessors();

        // 初始化
        initArgProcessors();
    }

    // 初始化参数处理器
    private void initArgProcessors() {
        // 从容器中获取定义的 IArgProcessor
        Map<String, IArgProcessor> map = applicationContext.getBeansOfType(IArgProcessor.class);
        List<IArgProcessor> processors = map.values().stream().collect(Collectors.toList());
        // 按 order 进行排序
        processors.sort(OrderComparator.INSTANCE);
        // 设置到 LogConverter 中
        LogMessageConverter.setArgProcessors(processors);
    }

    // 初始化日志处理器
    private void initMessageProcessors() {
        Map<String, IMessageProcessor> map = applicationContext.getBeansOfType(IMessageProcessor.class);
        List<IMessageProcessor> processors = map.values().stream().collect(Collectors.toList());
        processors.sort(OrderComparator.INSTANCE);
        LogMessageConverter.setMessageProcessors(processors);
    }
}
