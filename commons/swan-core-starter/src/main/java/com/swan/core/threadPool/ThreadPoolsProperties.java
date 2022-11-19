package com.swan.core.threadPool;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/** 线程池配置
 * @author zongf
 * @since 2021-11-26
 */
@Setter @Getter
@ConfigurationProperties(prefix = "sboot.thread-pools")
public class ThreadPoolsProperties {

    /** 是否启用 声明式线程池配置 */
    private boolean enable;

    /** 默认参数 */
    private ThreadPoolConfig defaultConfig;

    /** 线程池配置 */
    private Map<String, ThreadPoolConfig> poolConfigs;

    @Setter @Getter
    public static class ThreadPoolConfig {

        /** 核心线程数 */
        private Integer corePoolSize;

        /** 最大线程数 */
        private Integer maxPoolSize;

        /** 空闲连接时间 */
        private Integer keepAliveSeconds;

        /** 队列容量 */
        private Integer queueCapacity;

        private Integer awaitTerminationSeconds;

        /** 是否是守护线程 */
        private Boolean daemon;

        /** 允许核心线程timeout */
        private Boolean allowCoreThreadTimeOut;

        /** 线程组名称 */
        private String threadGroupName;

        /** 线程名前缀 */
        private String threadNamePrefix;

        /** 队里满以后处理策略 */
        private String rejectedHandlerBean;

    }






}
