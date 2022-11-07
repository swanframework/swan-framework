package com.swan.log.core;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/** 动态添加环境配置
 * @author zongf
 * @since 2022-11-07
 **/
public class SwanLogEnvInitializer implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        Map<String, Object> map = new HashMap<>();
        map.put("logging.config", "classpath:logback-spring.xml");

        MapPropertySource propertySource = new MapPropertySource("inside.swan.log.env", map);

        // 使用 addLast 方法，可保证优先使用用户自定义 logging.config
        event.getEnvironment().getPropertySources().addLast(propertySource);
    }
}
