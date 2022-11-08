package com.swan.env.core;

import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

/** Environment 初始化完成后，容器创建之前可通过 getInstance() 直接来访问。
 *  容器启动过程中，可通过从容器中获取 bean 方式访问
 * @author zongf
 * @since 2022-11-08
 **/
public class SwanEnvironment implements ISwanEnvironment{

    private static final SwanEnvironment SWAN_ENVIRONMENT = new SwanEnvironment();

    private static String applicationName;
    private static List<String> activeProfiles;
    private static Integer serverPort;
    private static Integer cpuCores;

    public static final SwanEnvironment getInstance() {
        return SWAN_ENVIRONMENT;
    }

    static void init(Environment environment) {
        applicationName = environment.getProperty("spring.application.name");
        serverPort = environment.getProperty("server.port", Integer.class);
        activeProfiles = Arrays.asList(environment.getActiveProfiles());
        cpuCores = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public String applicationName() {
        return applicationName;
    }

    @Override
    public List<String> activeProfiles() {
        return activeProfiles;
    }

    @Override
    public Integer serverPort() {
        return serverPort;
    }

    @Override
    public Integer cpuCores() {
        return cpuCores;
    }
}
