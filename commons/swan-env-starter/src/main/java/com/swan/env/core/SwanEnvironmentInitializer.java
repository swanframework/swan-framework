package com.swan.env.core;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/** 初始化时机：Environment 初始化完成，Spring 容器启动之前。
 *  实现方式: 监听  ApplicationEnvironmentPreparedEvent 事件，设置顺序为 MAX_VALUE，确保在其它监听器之后修改完环境变量后执行。
 * @author zongf
 * @since 2022-11-08
 **/
public class SwanEnvironmentInitializer implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        // 初始化 SwanEnvironment
        SwanEnvironment.init(event.getEnvironment());

    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
