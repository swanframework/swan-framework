package com.swan.core.threadPool;

import com.swan.core.listener.ApplicationRefreshListener;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author zongf
 * @since 2021-11-26
 */
public class ThreadPoolRegister implements ApplicationRefreshListener {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void onRefresh(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;

        ThreadPoolsProperties threadPoolsProperties = beanFactory.getBean(ThreadPoolsProperties.class);

        if (threadPoolsProperties.isEnable()) {

            Map<String, ThreadPoolsProperties.ThreadPoolConfig> poolMap = threadPoolsProperties.getPoolConfigs();

            if (poolMap != null && !poolMap.isEmpty()) {
                poolMap.forEach((beanName, config) ->{
                    // 合并配置参数
                    mergeConfig(threadPoolsProperties.getDefaultConfig(), config);
                    // 创建线程池
                    ThreadPoolTaskExecutor threadPoolTaskExecutor = create(threadPoolsProperties.getDefaultConfig(), config);
                    // 注册bean
                    beanFactory.registerSingleton(beanName, threadPoolTaskExecutor);
                });
            }
        }
    }


    // 合并配置: 如果未指定自定义配置，则使用默认配置
    private void mergeConfig(ThreadPoolsProperties.ThreadPoolConfig defaultConfig, ThreadPoolsProperties.ThreadPoolConfig config) {

        if (config.getCorePoolSize() == null) {
            config.setCorePoolSize(defaultConfig.getCorePoolSize());
        }
        if (config.getMaxPoolSize() == null) {
            config.setMaxPoolSize(defaultConfig.getMaxPoolSize());
        }
        if (config.getKeepAliveSeconds() == null) {
            config.setKeepAliveSeconds(defaultConfig.getKeepAliveSeconds());
        }
        if (config.getAllowCoreThreadTimeOut() == null) {
            config.setAllowCoreThreadTimeOut(defaultConfig.getAllowCoreThreadTimeOut());
        }
        if (config.getQueueCapacity() == null) {
            config.setQueueCapacity(defaultConfig.getQueueCapacity());
        }
        if (config.getThreadGroupName() == null) {
            config.setThreadGroupName(defaultConfig.getThreadGroupName());
        }
        if (config.getThreadNamePrefix() == null) {
            config.setThreadNamePrefix(defaultConfig.getThreadNamePrefix());
        }
        if (config.getDaemon() == null) {
            config.setDaemon(defaultConfig.getDaemon());
        }
        if (config.getDaemon() == null) {
            config.setDaemon(defaultConfig.getDaemon());
        }
        if (config.getAwaitTerminationSeconds() == null) {
            config.setAwaitTerminationSeconds(defaultConfig.getAwaitTerminationSeconds());
        }
        if (config.getRejectedHandlerBean() == null) {
            config.setRejectedHandlerBean(defaultConfig.getRejectedHandlerBean());
        }
    }


    // 创建线程池
    private ThreadPoolTaskExecutor create(ThreadPoolsProperties.ThreadPoolConfig defaultConfig, ThreadPoolsProperties.ThreadPoolConfig config) {

        ThreadPoolTaskExecutor taskPool = new ThreadPoolTaskExecutor();

        if (config.getCorePoolSize() != null) {
            taskPool.setCorePoolSize(config.getCorePoolSize());
        }
        if (config.getMaxPoolSize() != null) {
            taskPool.setMaxPoolSize(config.getMaxPoolSize());
        }

        if (config.getKeepAliveSeconds() != null) {
            taskPool.setKeepAliveSeconds(config.getKeepAliveSeconds());
        }
        if (config.getAllowCoreThreadTimeOut() != null) {
            taskPool.setAllowCoreThreadTimeOut(config.getAllowCoreThreadTimeOut());
        }

        if (config.getQueueCapacity() != null) {
            taskPool.setQueueCapacity(config.getQueueCapacity());
        }
        if (config.getThreadGroupName() != null) {
            taskPool.setThreadGroupName(config.getThreadGroupName());
        }
        if (config.getThreadNamePrefix() != null) {
            taskPool.setThreadNamePrefix(config.getThreadNamePrefix());
        }
        if (config.getDaemon() != null) {
            taskPool.setDaemon(config.getDaemon());
        }
        if (config.getAwaitTerminationSeconds() != null) {
            taskPool.setAwaitTerminationSeconds(config.getAwaitTerminationSeconds());
        }
        if (config.getRejectedHandlerBean() != null) {
            RejectedExecutionHandler rejectedExecutionHandler = beanFactory.getBean(config.getRejectedHandlerBean(), RejectedExecutionHandler.class);
            if (rejectedExecutionHandler == null) {
                throw new RuntimeException("bean 不存在:" + config.getRejectedHandlerBean());
            }
            taskPool.setRejectedExecutionHandler(rejectedExecutionHandler);
        }
        return taskPool;
    }


}
