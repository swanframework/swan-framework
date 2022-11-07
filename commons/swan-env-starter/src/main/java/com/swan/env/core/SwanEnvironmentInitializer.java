package com.swan.env.core;

import lombok.Getter;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** 环境
 * @author zongf
 * @since 2022-11-07
 **/
@Getter
public class SwanEnvironmentInitializer implements SwanEnvironment, ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private String applicationName;

    private List<String> activeProfiles;

    private Integer cpuCores;

    private Integer serverPort;

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment env = event.getEnvironment();

        applicationName = env.getProperty("spring.application.name");
        activeProfiles = env.getActiveProfiles() != null ? Arrays.asList(env.getActiveProfiles()) : Collections.emptyList();
        serverPort = env.getProperty("server.port",Integer.class);
        cpuCores = Runtime.getRuntime().availableProcessors();
    }


}
